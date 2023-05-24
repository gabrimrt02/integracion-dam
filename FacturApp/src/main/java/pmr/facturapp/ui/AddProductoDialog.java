package pmr.facturapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.statics.Unidad;

public class AddProductoDialog extends Dialog<Producto> implements Initializable {

    // Variables URL
    private final URL FICHERO = getClass().getResource("/fxml/popups/add/productoAddView.fxml");

    // Varibales alfanumericas
    private final String TITULO_DIALOG = "Añadir Producto";

    // Model
    private StringProperty nombreSP = new SimpleStringProperty();
    private StringProperty descripcionSP = new SimpleStringProperty();
    private DoubleProperty precioDP = new SimpleDoubleProperty();
    private IntegerProperty stockIP = new SimpleIntegerProperty();
    private ObjectProperty<Unidad> unidadOP = new SimpleObjectProperty<>();

    // View
    @FXML
    private JFXTextArea descripciónTextArea;

    @FXML
    private JFXTextField nombreTextField;

    @FXML
    private JFXTextField precioTextField;

    @FXML
    private JFXTextField stockTextField;

    @FXML
    private JFXComboBox<Unidad> unidadComboBox;

    @FXML
    private GridPane view;

    // Constructor
    public AddProductoDialog() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(FICHERO);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Producto onResultConverter(ButtonType button) {
        if (button.getButtonData() == ButtonData.OK_DONE) {
            Producto producto = new Producto();
            producto.setNombre(getNombre());
            producto.setDescripcion(getDescripcion());
            producto.setPrecio(getPrecio());
            producto.setStock(getStock());
            producto.setUnidad(getUnidad());
            return producto;

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
        nombreSP.bind(nombreTextField.textProperty());
        descripcionSP.bind(descripciónTextArea.textProperty());
        Bindings.bindBidirectional(precioTextField.textProperty(), precioDP, new NumberStringConverter());
        Bindings.bindBidirectional(stockTextField.textProperty(), stockIP, new NumberStringConverter());
        unidadOP.bind(unidadComboBox.valueProperty());

        // Disable add Button
        Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
        addButton.disableProperty().bind(nombreSP.isEmpty().or(descripcionSP.isEmpty()
                .or(precioDP.lessThanOrEqualTo(0.0).or(stockIP.lessThan(0).or(unidadOP.isNull())))));

    }

    public String getNombre() {
        return nombreSP.get();
    }

    public String getDescripcion() {
        return descripcionSP.get();
    }

    public Double getPrecio() {
        return precioDP.get();
    }

    public int getStock() {
        return stockIP.get();
    }

    public Unidad getUnidad() {
        return unidadOP.get();
    }

}
