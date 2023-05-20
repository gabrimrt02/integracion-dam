package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class RootController implements Initializable {

    // URLs de las interfaces
    private final URL URL_INICIO = getClass().getResource("/web/html/sections/inicio.html");
    private final URL URL_VENTAS = getClass().getResource("/web/html/sections/ventas.html");
    private final URL URL_COMPRAS = getClass().getResource("/web/html/sections/compras.html");
    private final URL URL_CLIENTES = getClass().getResource("/web/html/sections/clientes.html");
    private final URL URL_EMPLEADOS = getClass().getResource("/web/html/sections/empleados.html");

    // View
    @FXML
    private JFXButton clientesJFXButton;

    @FXML
    private JFXButton comprasJFXButton;

    @FXML
    private JFXButton empleadosJFXButton;

    @FXML
    private JFXButton inicioJFXButton;

    @FXML
    private JFXButton ventasJFXButton;

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
        loadWebPage(URL_INICIO);
        // inicarInicio();
    }

    // Actions
    @FXML
    void onClickInicioAction(ActionEvent event) {
        loadWebPage(URL_INICIO);

        webView.getEngine().getLoadWorker().stateProperty().addListener((o, ov, nv) -> {
            if(nv == Worker.State.SUCCEEDED)
                inicarInicio();
        });
    }

    @FXML
    void onClickVentasAction(ActionEvent event) {
        loadWebPage(URL_VENTAS);

        webView.getEngine().getLoadWorker().stateProperty().addListener((o, ov, nv) -> {
            if(nv == Worker.State.SUCCEEDED)
                System.out.println("Ventas cargado");
        });
    }

    @FXML
    void onClickComprasAction(ActionEvent event) {
        loadWebPage(URL_COMPRAS);

        webView.getEngine().getLoadWorker().stateProperty().addListener((o, ov, nv) -> {
            if(nv == Worker.State.SUCCEEDED)
                System.out.println("Compras cargado");
        });
    }

    @FXML
    void onClickClientesAction(ActionEvent event) {
        loadWebPage(URL_CLIENTES);

        webView.getEngine().getLoadWorker().stateProperty().addListener((o, ov, nv) -> {
            if(nv == Worker.State.SUCCEEDED)
                System.out.println("Clientes cargado");
        });
    }

    @FXML
    void onClickEmpleadosAction(ActionEvent event) {
        loadWebPage(URL_EMPLEADOS);

        webView.getEngine().getLoadWorker().stateProperty().addListener((o, ov, nv) -> {
            if(nv == Worker.State.SUCCEEDED)
                System.out.println("Empleados cargado");
        });
    }

    public BorderPane getView() {
        return view;
    }

    private void loadWebPage(URL pageToLoad) {
        webView.getEngine().load(pageToLoad.toString());
    }

    private void inicarInicio() {
        // webView.getEngine().executeScript("replaceFecha();");
        webView.getEngine().executeScript("replaceUsuario('Gabriel');");
        webView.getEngine().executeScript("replaceBalanceGlobal(10000);");
    }

    private void removeWorkerListeners() {
        
    }

}
