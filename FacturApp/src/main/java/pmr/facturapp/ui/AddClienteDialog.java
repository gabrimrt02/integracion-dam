package pmr.facturapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.ObjectProperty;
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
import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.statics.TipoCliente;

public class AddClienteDialog extends Dialog<Cliente> implements Initializable {

    // Variables URL
    private final URL FICHERO = getClass().getResource("/fxml/popups/add/clienteAddView.fxml");

    // Variables alfanuméricas
    private final String TITULO_DIALOG = "Añadir Cliente";

    // Model
    private StringProperty nombreSP = new SimpleStringProperty();
    private StringProperty apellidoSP = new SimpleStringProperty();
    private Domicilio domicilio = new Domicilio();
    private StringProperty telefonoSP = new SimpleStringProperty();
    private StringProperty mailSP = new SimpleStringProperty();
    private ObjectProperty<TipoCliente> tipoClienteOP = new SimpleObjectProperty<>();

    // View
    @FXML
    private JFXTextField apellidosTextField;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXComboBox<String> municipioComboBox;

    @FXML
    private JFXTextField nombreTextField;

    @FXML
    private JFXComboBox<String> provinciaComboBox;

    @FXML
    private JFXTextField telefonoTextField;

    @FXML
    private JFXComboBox<TipoCliente> tipoClienteComboBox;

    @FXML
    private GridPane view;

    // Constructor
    public AddClienteDialog() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(FICHERO);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Cliente onResultConverter(ButtonType button) {
        if (button.getButtonData() == ButtonData.OK_DONE) {
            Cliente cliente = new Cliente();
            cliente.setNombre(getNombre());
            cliente.setApellido(getApellidos());
            cliente.setDomicilio(domicilio);
            cliente.setNTelefono(getTelefono());
            cliente.setMail(getMail());
            cliente.setTipoCliente(getTipoCliente());
            return cliente;
            
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
        apellidoSP.bind(apellidosTextField.textProperty());
        provinciaComboBox.valueProperty().bindBidirectional(domicilio.provinciaProperty());
        municipioComboBox.valueProperty().bindBidirectional(domicilio.municipioProperty());
        telefonoSP.bind(telefonoTextField.textProperty());
        mailSP.bind(emailTextField.textProperty());
        tipoClienteOP.bind(tipoClienteComboBox.valueProperty());

        // Disable Añadir Button
        Button addButton = (Button) getDialogPane().lookupButton(addButtonType);
        addButton.disableProperty()
                .bind(nombreSP.isEmpty()
                        .or(apellidoSP.isEmpty().or(domicilio.provinciaProperty().isEmpty()
                                .or(domicilio.municipioProperty().isEmpty().or(telefonoSP.isEmpty()
                                        .or(mailSP.isEmpty().or(tipoClienteOP.isNull()))))))
                );

    }

    public String getNombre() {
        return nombreSP.get();
    }

    public String getApellidos() {
        return apellidoSP.get();
    }

    public String getProvincia() {
        return domicilio.getProvincia();
    }

    public String getMunicipio() {
        return domicilio.getMunicipio();
    }

    public String getTelefono() {
        return telefonoSP.get();
    }

    public String getMail() {
        return mailSP.get();
    }

    public TipoCliente getTipoCliente() {
        return tipoClienteOP.get();
    }

}
