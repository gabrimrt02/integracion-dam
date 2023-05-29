package pmr.facturapp.ui.add;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;
import org.controlsfx.control.SearchableComboBox;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import pmr.facturapp.App;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.converters.ProductoConverter;

public class AddCompraProducto extends Dialog<List<Producto>> implements Initializable {

    /*
     * Varibales URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/popups/add/compraAddProductoView.fxml");

    /*
     * Variables alfanuméricas
     */
    private final String TITULO_DIALOG = "Añadir producto";

    /*
     * Model
     */
    private IntegerProperty cantidadIP = new SimpleIntegerProperty();
    private ObjectProperty<Producto> productoOP = new SimpleObjectProperty<>();

    private List<Producto> productos = new ArrayList<>();

    /*
     * View
     */
    @FXML
    private TextField cantidadTextField;

    @FXML
    private SearchableComboBox<Producto> productosComboBox;

    @FXML
    private GridPane view;

    /*
     * Constructor
     */
    public AddCompraProducto() {
        super();

        List<Document> docs = App.dbManager.getAllProductos();
        for (Document d : docs) {
            productos.add(ProductoConverter.convert(d));
        }

        try {
            FXMLLoader loader = new FXMLLoader(FICHERO_VIEW);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Producto> onResultConverter(ButtonType button) {
        if (button.getButtonData() == ButtonData.OK_DONE) {
            ArrayList<Producto> productos = new ArrayList<>();
            for (int i = 0; i < getCantidad(); i++) {
                productos.add(getProducto());
            }

            return productos;
        }

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
        getDialogPane().setContent(getView());
        getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        setResultConverter(this::onResultConverter);
        
        // Bindings
        productoOP.bind(productosComboBox.getSelectionModel().selectedItemProperty());
        Bindings.bindBidirectional(cantidadTextField.textProperty(), cantidadIP, new NumberStringConverter());

        productosComboBox.getItems().setAll(productos);

    }

    /*
     * GetView
     */
    public GridPane getView() {
        return this.view;
    }

    /*
     * Funciones
     */
    public IntegerProperty cantidadProperty() {
        return cantidadIP;
    }
    
    public ObjectProperty<Producto> productoProperty() {
        return productoOP;
    }

    public Integer getCantidad() {
        return cantidadIP.get();
    }

    public Producto getProducto() {
        return productoOP.get();
    }
}
