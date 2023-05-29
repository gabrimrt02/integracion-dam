package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;
import org.controlsfx.control.Notifications;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import pmr.facturapp.App;
import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Persona;
import pmr.facturapp.classes.statics.TipoCliente;
import pmr.facturapp.converters.ClienteConverter;
import pmr.facturapp.ui.info.InfoClienteProveedorDialog;

public class ClientesController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/clientesView.fxml");

    /*
     * Varibales alfanumericas
     */
    // Alert borrado Clientes
    private final String DEL_CLIENTES_TITLE = "Borrar Cliente";
    private final String DEL_CLIENTES_HEAD = "¿Seguro que desea eliminar el siguiente cliente?";

    // Notificaciones Borrado
    private final String DEL_NOTIFICATION_TITLE_SUCC = "BORRADO REALIZADO CON ÉXITO";
    private final String DEL_NOTIFICATION_TITLE_FAIL = "ERROR AL REALIZAR EL BORRADO";
    private final String DEL_NOTIFICATION_TITLE_CANCEL = "BORRADO CANCELADO";

    /*
     * Model
     */
    private ListProperty<Cliente> clientesLP = new SimpleListProperty<>(FXCollections.observableArrayList());

    /*
     * View
     */
    @FXML
    private TableView<Cliente> clientesTableView;

    @FXML
    private TableColumn<Cliente, Domicilio> domicilioColumn;

    @FXML
    private TableColumn<Cliente, String> emailColumn;

    @FXML
    private TableColumn<Cliente, String> nombreColumn;

    @FXML
    private TableColumn<Cliente, String> telefonoColumn;

    @FXML
    private TableColumn<Cliente, TipoCliente> tipoColumn;

    @FXML
    private MenuItem masInformacionMI;

    @FXML
    private MenuItem eliminarMI;

    @FXML
    private BorderPane view;

    /*
     * Constructor
     */
    public ClientesController() {

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
        setList();

        // Bindings
        clientesTableView.itemsProperty().bind(clientesLP);

        updateTable();
    }

    /*
     * GetView
     */
    public BorderPane getView() {
        return this.view;
    }

    /*
     * GetTableView
     */
    public TableView<Cliente> getClientesTableView() {
        return this.clientesTableView;
    }

    /*
     * Funciones de la view
     */
    @FXML
    void onMoreInfoAction(ActionEvent event) {
        InfoClienteProveedorDialog dialog = new InfoClienteProveedorDialog(getSelectedCliente());

        dialog.showAndWait();
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        Cliente cliente = clientesTableView.getSelectionModel().getSelectedItem();

        alert.setTitle(DEL_CLIENTES_TITLE);
        alert.setHeaderText(DEL_CLIENTES_HEAD);
        alert.setContentText(cliente.toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            // Tarea de Borrado en BBDD
            App.tareaBorrado = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.deleteCliente(cliente);

                    return null;
                }
            };

            App.tareaBorrado.setOnSucceeded(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_SUCC).text(cliente.toString()).show();
                updateView();
            });

            App.tareaBorrado.setOnCancelled(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_CANCEL).text(cliente.toString()).showWarning();
                updateView();
            });

            App.tareaBorrado.setOnFailed(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_FAIL).text(cliente.toString()).showError();
                updateView();
            });

            new Thread(App.tareaBorrado).start();

        }
    }

    /*
     * Funciones
     */
    public void updateView() {
        setList();

        updateTable();
    }

    private void setList() {
        clientesLP.clear();
        List<Document> docClientes = App.dbManager.getAllClientes();
        for (Document d : docClientes) {
            clientesLP.add(ClienteConverter.convert(d));
        }
    }

    private void updateTable() {
        nombreColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getNombre() + " " + data.getValue().getApellido()));
        tipoColumn.setCellValueFactory(data -> data.getValue().tipoClienteProperty());
        domicilioColumn.setCellValueFactory(data -> data.getValue().domicilioProperty());
        telefonoColumn.setCellValueFactory(data -> data.getValue().telefonoProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().mailProperty());
    }

    private Persona getSelectedCliente() {
        return clientesTableView.getSelectionModel().getSelectedItem();
    }

}
