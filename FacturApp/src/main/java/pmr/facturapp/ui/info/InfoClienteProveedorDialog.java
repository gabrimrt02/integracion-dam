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
import pmr.facturapp.classes.Persona;

public class InfoClienteProveedorDialog extends Dialog<Persona> implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/popups/info/clienteProveedorInfoView.fxml");

    /*
     * Variables alfanuméricas
     */
    private final String DIALOG_TITULO = "Información";

    /*
     * Model
     */
    private Persona persona;

    /*
     * View
     */
    @FXML
    private TextField domicilioTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private GridPane view;

    /*
     * Constructor
     */
    public InfoClienteProveedorDialog(Persona persona) {
        this.persona = persona;

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
        setTitle(DIALOG_TITULO);
        getDialogPane().setContent(view);
        getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

        idTextField.setText(persona.getId().toString());
        nombreTextField.setText(persona.getNombre() + " " + persona.getApellido());
        domicilioTextField.setText(persona.getDomicilio().toString());
        telefonoTextField.setText(persona.getNTelefono());
        emailTextField.setText(persona.getMail());

    }

}
