package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.kordamp.ikonli.javafx.FontIcon;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class LoginController implements Initializable {

    @FXML
    private JFXButton loginButton;

    @FXML
    private Hyperlink noAccountLink;

    @FXML
    private FontIcon passwordImage;

    @FXML
    private JFXPasswordField passwordPassText;

    @FXML
    private JFXTextField passwordText;

    @FXML
    private JFXCheckBox showPasswordCheck;

    @FXML
    private FontIcon usernameIcon;

    @FXML
    private JFXTextField usernameText;

    @FXML
    private BorderPane view;

    @FXML
    private ImageView welcomeImage;

    @FXML
    private Label welcomeLabel;

    public LoginController() {

        URL ficheroView = getClass().getResource("/fxml/loginView.fxml");

        try {
            FXMLLoader loader = new FXMLLoader(ficheroView);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Se ha producido un error");
            alert.setContentText("No se ha podido cargar de forma correcta el archivo " + ficheroView.toString());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    public BorderPane getView() {
        return this.view;
    }

    @FXML
    void onLoginAction(ActionEvent event) {

    }

    @FXML
    void onTextClicked(MouseEvent event) {

    }

}
