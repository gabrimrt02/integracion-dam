package pmr.facturapp.ui.info;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pmr.facturapp.App;
import pmr.facturapp.classes.Producto;

public class InfoProductosDialog extends Dialog<Producto> implements Initializable {
    
    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/popups/info/productosInfoView.fxml");

    /*
     * Variables alfanumericas
     */
    private final String DIALOG_TITLE = "Información";

    /*
     * Model
     */
    private Producto producto;

    /*
     * View
     */
    @FXML
    private TextField descripcionTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField precioTextField;

    @FXML
    private TextField unidadTextField;

    @FXML
    private GridPane view;

    /*
     * Constructor
     */
    public InfoProductosDialog(Producto producto) {
        this.producto = producto;

        try {
            FXMLLoader loader = new FXMLLoader(FICHERO_VIEW);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * Inicializador
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Config
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.LOGO);
        setTitle(DIALOG_TITLE);
        getDialogPane().setContent(view);
        getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

        idTextField.setText(producto.getId().toString());
        nombreTextField.setText(producto.getNombre());
        descripcionTextField.setText(producto.getDescripcion());
        precioTextField.setText("" + producto.getPrecio());
        unidadTextField.setText(producto.getUnidad().toString());

    }

}
