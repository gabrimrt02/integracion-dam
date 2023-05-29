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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import pmr.facturapp.App;
import pmr.facturapp.classes.Empleado;
import pmr.facturapp.converters.EmpleadoConverter;

public class EmpleadosController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/empleadosView.fxml");

    /*
     * Varibales alfanumericas
     */
    // Alert borrardo Empleados
    private final String DEL_EMPLEADO_TITLE = "Borrar Empleado";
    private final String DEL_EMPLEADO_HEAD = "¿Seguro que desea eliminar el siguiente empleado?";

    // Notificaciones Borrado
    private final String DEL_NOTIFICATION_TITLE_SUCC = "BORRADO REALIZADO CON ÉXITO";
    private final String DEL_NOTIFICATION_TITLE_FAIL = "ERROR AL REALIZAR EL BORRADO";
    private final String DEL_NOTIFICATION_TITLE_CANCEL = "BORRADO CANCELADO";

    /*
     * Model
     */
    private ListProperty<Empleado> empleadosLP = new SimpleListProperty<>(FXCollections.observableArrayList());

    /*
     * View
     */
    @FXML
    private TableColumn<Empleado, String> emailColumn;

    @FXML
    private TableView<Empleado> empleadosTableView;

    @FXML
    private TableColumn<Empleado, String> nombreColumn;

    @FXML
    private PieChart pieChart;

    @FXML
    private StackedAreaChart<?, ?> stackAreaChart;

    @FXML
    private TableColumn<Empleado, String> telefonoColumn;

    @FXML
    private MenuItem eliminarMI;

    @FXML
    private BorderPane view;

    /*
     * Constructor
     */
    public EmpleadosController() {

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
        empleadosTableView.itemsProperty().bind(empleadosLP);

        updateTable();
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
    void onEliminarAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        Empleado empleado = empleadosTableView.getSelectionModel().getSelectedItem();

        alert.setTitle(DEL_EMPLEADO_TITLE);
        alert.setHeaderText(DEL_EMPLEADO_HEAD);
        alert.setContentText(empleado.toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            // Tarea de Borrado en BBDD
            App.tareaBorrado = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.deleteEmpleado(empleado);

                    return null;
                }
            };

            App.tareaBorrado.setOnSucceeded(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_SUCC).text(empleado.toString()).show();
                updateView();
            });

            App.tareaBorrado.setOnCancelled(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_CANCEL).text(empleado.toString()).showWarning();
                updateView();
            });

            App.tareaBorrado.setOnFailed(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_FAIL).text(empleado.toString()).showError();
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
        empleadosLP.clear();
        List<Document> docEmpleados = App.dbManager.getAllEmpleados();
        for (Document d : docEmpleados) {
            empleadosLP.add(EmpleadoConverter.convert(d));
        }
    }

    private void updateTable() {
        nombreColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getNombre() + " " + data.getValue().getApellido()));
        telefonoColumn.setCellValueFactory(data -> data.getValue().telefonoProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().mailProperty());
    }

    public TableView<Empleado> getEmpleadosTableView() {
        return this.empleadosTableView;
    }

}
