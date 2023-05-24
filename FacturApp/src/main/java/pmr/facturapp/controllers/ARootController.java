package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ARootController implements Initializable {

    // Mode
    private Worker<Void> worker;
    private WebEngine webEngine;

    // URL de la view
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/view.fxml");

    // Strings alerta fallo carga view
    private final String ALERT_TITULO = "Se ha producido un error";
    private final String ALERT_CONTENT = "No se ha podido cargar de forma correcta el archivo ";

    // URLs de las interfaces
    private final URL URL_INICIO = getClass().getResource("/web/html/sections/inicio.html");
    private final URL URL_VENTAS = getClass().getResource("/web/html/sections/ventas.html");
    private final URL URL_COMPRAS = getClass().getResource("/web/html/sections/compras.html");
    private final URL URL_CLIENTES = getClass().getResource("/web/html/sections/clientes.html");
    private final URL URL_EMPLEADOS = getClass().getResource("/web/html/sections/empleados.html");

    // TODO Tratar de implementar con un ChoiceBox
    // Listeners de las cargas de la web
    private ChangeListener<State> listenerInicio;
    private ChangeListener<State> listenerVentas;
    private ChangeListener<State> listenerCompras;
    private ChangeListener<State> listenerClientes;
    private ChangeListener<State> listenerEmpleados;

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
    public ARootController() {

        try {
            FXMLLoader loader = new FXMLLoader(FICHERO_VIEW);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(ALERT_TITULO);
            alert.setContentText(ALERT_CONTENT + FICHERO_VIEW.toString());
        }

    }

    // Inicializador
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        worker = webView.getEngine().getLoadWorker();
        webEngine = webView.getEngine();

        loadWebPage(URL_INICIO);
        // inicarInicio();

        // Declaración de los listeners
        listenerInicio = new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    inicarInicio();
                }
            }
        };

        listenerVentas = new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                if (newValue == Worker.State.SUCCEEDED)
                    System.out.println("Ventas cargado");
            }
        };

        listenerCompras = new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                if (newValue == Worker.State.SUCCEEDED)
                    System.out.println("Compras cargado");
            }
        };

        listenerClientes = new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                if (newValue == Worker.State.SUCCEEDED)
                    System.out.println("Cliente cargado");
            }
        };
        
        listenerEmpleados = new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                if (newValue == Worker.State.SUCCEEDED)
                    System.out.println("Empleado cargado");
            }
        };

    }

    // Actions
    @FXML
    void onClickInicioAction(ActionEvent event) {
        removeWorkerListeners();
        
        loadWebPage(URL_INICIO);
        
        worker.stateProperty().addListener(listenerInicio);

        // TODO BORRAR
        Notifications.create()
                        .title("HolaMundo")
                        .text("Hola desde la alerta")
                        .showInformation();
    }

    @FXML
    void onClickVentasAction(ActionEvent event) {
        removeWorkerListeners();
        
        loadWebPage(URL_VENTAS);

        worker.stateProperty().addListener(listenerVentas);
    }

    @FXML
    void onClickComprasAction(ActionEvent event) {
        removeWorkerListeners();
        
        loadWebPage(URL_COMPRAS);

        worker.stateProperty().addListener(listenerCompras);
    }

    @FXML
    void onClickClientesAction(ActionEvent event) {
        removeWorkerListeners();
        
        loadWebPage(URL_CLIENTES);

        worker.stateProperty().addListener(listenerClientes);
    }

    @FXML
    void onClickEmpleadosAction(ActionEvent event) {
        removeWorkerListeners();
        
        loadWebPage(URL_EMPLEADOS);

        worker.stateProperty().addListener(listenerEmpleados);
    }

    public BorderPane getView() {
        return view;
    }

    private void loadWebPage(URL pageToLoad) {
        webEngine.load(pageToLoad.toString());
    }

    private void inicarInicio() {
        webEngine.executeScript("replaceFecha();");
        webEngine.executeScript("replaceUsuario('Gabriel');");
        webEngine.executeScript("replaceBalanceGlobal(10000);");
    }

    private void removeWorkerListeners() {
        worker.stateProperty().removeListener(listenerInicio);
        worker.stateProperty().removeListener(listenerVentas);
        worker.stateProperty().removeListener(listenerCompras);
        worker.stateProperty().removeListener(listenerClientes);
        worker.stateProperty().removeListener(listenerEmpleados);
    }

}
