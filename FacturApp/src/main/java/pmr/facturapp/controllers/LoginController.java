package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;
import org.kordamp.ikonli.javafx.FontIcon;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import pmr.facturapp.App;
import pmr.facturapp.classes.Usuario;
import pmr.facturapp.converters.UsuarioConverter;

public class LoginController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/loginView.fxml");

    /*
     * Variables alfanuméricas
     */
    private final String ALERT_TITLE = "Usuario no encontrado";
    private final String ALERT_CONTENT = "El usuario no está registrado o la contraseña introducida no es correcta.";

    /*
     * Model
     */
    private ObjectProperty<Usuario> usuarioOP = new SimpleObjectProperty<>();

    private List<Usuario> usuariosList = new ArrayList<>();

    /*
     * View
     */
    @FXML
    private JFXButton loginButton;

    @FXML
    private FontIcon passwordImage;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private FontIcon usernameIcon;

    @FXML
    private TextField usernameTextField;

    @FXML
    private BorderPane view;

    @FXML
    private Label welcomeLabel;

    /*
     * Constructor
     */
    public LoginController() {
        usuarioOP.set(new Usuario());

        try {
            FXMLLoader loader = new FXMLLoader(FICHERO_VIEW);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Se ha producido un error");
            alert.setContentText("No se ha podido cargar de forma correcta el archivo " + FICHERO_VIEW.toString());
        }

    }

    /*
     * Inicializador
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Bindings
        usuarioOP.get().usernameProperty().bind(usernameTextField.textProperty());
        usuarioOP.get().passwordProperty().bind(passwordTextField.textProperty());

    }

    /*
     * GetView
     */
    public BorderPane getView() {
        return this.view;
    }

    /*
     * Funciones de la View
     */
    @FXML
    void onLoginAction(ActionEvent event) {
        Boolean contenido = false;

        List<Document> docsUsuarios = App.dbManager.getAllUsuarios();

        for (Document d : docsUsuarios) {
            usuariosList.add(UsuarioConverter.convert(d));
        }

        int index = 0;
        while (index < usuariosList.size() && !contenido) {
            if (usuariosList.get(index).equals(usuarioOP.get())) {
                contenido = true;
                usuarioOP.set(usuariosList.get(index));

            }

            index++;
        }

        if (contenido) {
            App.USERNAME = usuarioOP.get().getNombre();

            App.loadApp();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(ALERT_TITLE);
            alert.setHeaderText(ALERT_TITLE);
            alert.setContentText(ALERT_CONTENT);
            alert.showAndWait();

        }

    }

}
