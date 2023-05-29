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
import javafx.stage.Stage;
import pmr.facturapp.App;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Persona;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.converters.ProveedorConverter;
import pmr.facturapp.ui.info.InfoClienteProveedorDialog;

public class ProveedoresController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/proveedoresView.fxml");

    /*
     * Variables alfanumericas
     */
    // Alert borrado Proveedores
    private final String DEL_PROVEEDOR_TITLE = "Borrado Proveedor";
    private final String DEL_PROVEEDOR_HEAD = "¿Seguro que desea eliminar el siguiente proveedor?";
    
    // Notificaciones Borrado
    private final String DEL_NOTIFICATION_TITLE_SUCC = "BORRADO REALIZADO CON ÉXITO";
    private final String DEL_NOTIFICATION_TITLE_FAIL = "ERROR AL REALIZAR EL BORRADO";
    private final String DEL_NOTIFICATION_TITLE_CANCEL = "BORRADO CANCELADO";

    /*
     * Model
     */
    private ListProperty<Proveedor> proveedoresLP = new SimpleListProperty<>(FXCollections.observableArrayList());

    /*
     * View
     */
    @FXML
    private TableColumn<Proveedor, Domicilio> domicilioColumn;

    @FXML
    private TableColumn<Proveedor, String> emailColumn;

    @FXML
    private TableColumn<Proveedor, String> nombreColumn;

    @FXML
    private TableView<Proveedor> proveedoresTableView;

    @FXML
    private TableColumn<Proveedor, String> telefonoColumn;

    @FXML
    private MenuItem masInformacionMI;
    
    @FXML
    private MenuItem eliminarMI;

    @FXML
    private BorderPane view;

    /*
     * Constructor
     */
    public ProveedoresController() {

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
        // Bindings
        proveedoresTableView.itemsProperty().bind(proveedoresLP);
        
    }

    /*
     * GetView
     */
    public BorderPane getView() {
        return this.view;
    }

    /*
     * Funciones de la view
     */
    @FXML
    void onMoreInfoAction(ActionEvent event) {
        InfoClienteProveedorDialog dialog = new InfoClienteProveedorDialog(getSelectedProveedor());

        dialog.showAndWait();
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        Proveedor proveedor = proveedoresTableView.getSelectionModel().getSelectedItem();

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.LOGO);
        alert.setTitle(DEL_PROVEEDOR_TITLE);
        alert.setHeaderText(DEL_PROVEEDOR_HEAD);
        alert.setContentText(proveedor.toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            // Tarea de Borrado en BBDD
            App.tareaBorrado = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.deleteProveedor(proveedor);

                    return null;
                }
            };

            App.tareaBorrado.setOnSucceeded(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_SUCC).text(proveedor.toString()).show();
                updateView();
            });

            App.tareaBorrado.setOnCancelled(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_CANCEL).text(proveedor.toString()).showWarning();
                updateView();
            });

            App.tareaBorrado.setOnFailed(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_FAIL).text(proveedor.toString()).showError();
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
        proveedoresLP.clear();
        List<Document> docProveedores = App.dbManager.getAllProveedores();
        for (Document d : docProveedores) {
            proveedoresLP.add(ProveedorConverter.convert(d));
        }
    }

    private void updateTable() {
        nombreColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getNombre() + " " + data.getValue().getApellido()));
        domicilioColumn.setCellValueFactory(data -> data.getValue().domicilioProperty());
        telefonoColumn.setCellValueFactory(data -> data.getValue().telefonoProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().mailProperty());
    }

    public TableView<Proveedor> getProveedoresTableView() {
        return this.proveedoresTableView;
    }

    private Persona getSelectedProveedor() {
        return proveedoresTableView.getSelectionModel().getSelectedItem();
    }

}
