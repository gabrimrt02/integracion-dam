package pmr.facturapp.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Proveedor;

public class AddCompraDialog extends Dialog<Compra> implements Initializable {

    // Variables alfanumericas
    private static final String TITULO_DIALOG = "Añadir Compra";

    // Variables URL
    private final URL FICHERO = getClass().getResource("/fxml/popups/add/compraAddView.fxml");

    // Model
    private ObjectProperty<Proveedor> proveedorOP = new SimpleObjectProperty<>();
    private ListProperty<Producto> productoLP = new SimpleListProperty<>();
    private ObjectProperty<LocalDate> fechaOP = new SimpleObjectProperty<>();

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

        setTitle(TITULO_DIALOG);
        getDialogPane().setContent(view);
        getDialogPane().getButtonTypes().setAll(addButtonType, ButtonType.CANCEL);

        setResultConverter(this::onResultConverter);

        // Bindings
        proveedorOP.bind(proveedorComboBox.valueProperty());
        productoLP.bind(productosListView.itemsProperty());
        fechaOP.bind(fechaDatePicker.valueProperty());

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

}
