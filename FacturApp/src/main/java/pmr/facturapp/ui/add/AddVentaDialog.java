package pmr.facturapp.ui.add;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.SearchableComboBox;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pmr.facturapp.App;
import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Empleado;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Venta;
import pmr.facturapp.converters.ClienteConverter;
 
public class AddVentaDialog extends Dialog<Venta> implements Initializable {
    
    /*
     * Variables alfanuméricas
     */
    private static final String TITULO_DIALOG = "Añadir Venta";
    private static final String DEL_PRODUCTO_TITLE = "PRODUCTO ELIMINADO";
    private static final String DEL_PRODUCTO_CONTENT = "Se ha eliminado el producto";

    /*
     * Variables URL
     */
    private final URL FICHERO = getClass().getResource("/fxml/popups/add/ventaAddView.fxml");

    /*
     * Model
     */
    private ObjectProperty<Cliente> clienteOP = new SimpleObjectProperty<>();
    private ListProperty<Producto> productosLP = new SimpleListProperty<>();
    private ObjectProperty<LocalDate> fechaOP = new SimpleObjectProperty<>();
    private ObjectProperty<Empleado> empleadoOP = new SimpleObjectProperty<>();

    
    private List<Cliente> clientes = new ArrayList<>();

    /*
     * View
     */
    @FXML
    private JFXButton addProductoButton;

    @FXML
    private SearchableComboBox<Cliente> clienteComboBox;

    @FXML
    private TextField empleadoTextField;

    @FXML
    private TextField fechaTextField;

    @FXML
    private JFXListView<Producto> productosListView;

    @FXML
    private JFXButton removeProductoButton;

    @FXML
    private GridPane view;

    /*
     * Constructor
     */
    public AddVentaDialog() {
        super();

        List<Document> docs = App.dbManager.getAllClientes();
        for (Document d : docs) {
            clientes.add(ClienteConverter.convert(d));
        }

        try {
            FXMLLoader loader = new FXMLLoader(FICHERO);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Venta onResultConverter(ButtonType button) {
        if (button.getButtonData() == ButtonData.OK_DONE)
            return new Venta(getCliente(), getProductos(), getEmpleado(), getFecha());

        return null;
    }

    /*
     * Inicializador
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Config
        ButtonType addButtonType = new ButtonType("Añadir", ButtonData.OK_DONE);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.LOGO);
        setTitle(TITULO_DIALOG);
        getDialogPane().setContent(view);
        getDialogPane().getButtonTypes().setAll(addButtonType, ButtonType.CANCEL);

        setResultConverter(this::onResultConverter);

        // Bindings
        clienteOP.bind(clienteComboBox.valueProperty());
        productosLP.bind(productosListView.itemsProperty());

        fechaOP.set(LocalDate.now());
        // TODO Set empleado tras modificación de guardado de serial en la BD

        // TODO Disable add Button
        Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
        addButton.disableProperty().bind(clienteOP.isNull().or(productosLP.isNull()));

    }

    public Cliente getCliente() {
        return clienteOP.get();
    }

    public List<Producto> getProductos() {
        return productosLP.get();
    }

    public Empleado getEmpleado() {
        return empleadoOP.get();
    }

    public LocalDate getFecha() {
        return fechaOP.get();
    }

    /*
     * Funciones de la View
     */
    @FXML
    void onAddProductoAction(ActionEvent event) {
        AddCompraProducto dialog = new AddCompraProducto();

        Optional<List<Producto>> res = dialog.showAndWait();

        if (res.isPresent()) {
            List<Producto> productos = new ArrayList<>();
            for (int i = 0; i < dialog.getCantidad(); i++) {
                productos.add(dialog.getProducto());

            }

            productosLP.setAll(productos);
            
        }

    }

    @FXML
    void onDelProductoAction(ActionEvent event) {
        Producto producto = productosListView.getSelectionModel().getSelectedItem();

        productosListView.getItems().remove(producto);

        Notifications.create().title(DEL_PRODUCTO_TITLE).text(DEL_PRODUCTO_CONTENT + producto.getNombre()).showConfirm();

    }

}
