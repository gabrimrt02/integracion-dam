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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pmr.facturapp.App;
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.converters.ProveedorConverter;

public class AddCompraDialog extends Dialog<Compra> implements Initializable {

    // Variables alfanumericas
    private static final String TITULO_DIALOG = "Añadir Compra";
    private static final String DEL_PRODUCTO_TITLE = "PRODUCTO ELIMINADO";
    private static final String DEL_PRODUCTO_CONTENT = "Se ha eliminado el producto";

    // Variables URL
    private final URL FICHERO = getClass().getResource("/fxml/popups/add/compraAddView.fxml");

    // Model
    private ObjectProperty<Proveedor> proveedorOP = new SimpleObjectProperty<>();
    private ListProperty<Producto> productoLP = new SimpleListProperty<>();
    private ObjectProperty<LocalDate> fechaOP = new SimpleObjectProperty<>();

    private List<Proveedor> proveedores = new ArrayList<>();

    // View
    @FXML
    private JFXButton addProductoButton;

    @FXML
    private DatePicker fechaDatePicker;

    @FXML
    private JFXListView<Producto> productosListView;

    @FXML
    private SearchableComboBox<Proveedor> proveedorComboBox;

    @FXML
    private JFXButton removeProductoButton;

    @FXML
    private GridPane view;

    // Constructor
    public AddCompraDialog() {
        super();

        List<Document> docs = App.dbManager.getAllProveedores();
        for(Document d : docs) {
            proveedores.add(ProveedorConverter.convert(d));
        }

        try {
            FXMLLoader loader = new FXMLLoader(FICHERO);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Compra onResultConverter(ButtonType button) {
        if (button.getButtonData() == ButtonData.OK_DONE) {
            return new Compra(getProveedor(), getProductos(), getFecha());
        }
        return null;

    }

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
        proveedorOP.bind(proveedorComboBox.valueProperty());
        productoLP.bind(productosListView.itemsProperty());
        fechaOP.bind(fechaDatePicker.valueProperty());

        proveedorComboBox.getItems().setAll(proveedores);

        // Disable add Button
        Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
        addButton.disableProperty().bind(proveedorOP.isNull().or(productoLP.isNull().or(fechaOP.isNull())));

    }

    public Proveedor getProveedor() {
        return proveedorOP.get();
    }

    public List<Producto> getProductos() {
        return productoLP.get();
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

            productoLP.setAll(productos);
            
        }

    }

    @FXML
    void onDelProductoAction(ActionEvent event) {
        Producto producto = productosListView.getSelectionModel().getSelectedItem();

        productosListView.getItems().remove(producto);

        Notifications.create().title(DEL_PRODUCTO_TITLE).text(DEL_PRODUCTO_CONTENT + producto.getNombre()).showConfirm();

    }

}
