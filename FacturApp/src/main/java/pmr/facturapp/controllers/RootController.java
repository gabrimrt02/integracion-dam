package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class RootController implements Initializable {

    // View
    @FXML
    private MenuBar menuBar;

    @FXML
    private WebView webView;

    @FXML
    private BorderPane view;

    // Constructor
    public RootController() {

        URL fichero = getClass().getResource("/fxml/view.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(fichero);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Se ha producido un error");
            alert.setContentText("No se ha podido cargar de forma correcta el archivo " + fichero.toString());
        }

    }

    // Inicializador
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Implementar los binds necesarios
        loadWebPage();
    }

    public BorderPane getView() {
        return view;
    }

    private void loadWebPage() {
        URL pageToLoad = getClass().getResource("/web/html/main.html");
        webView.getEngine().load(pageToLoad.toString());
    }

}
