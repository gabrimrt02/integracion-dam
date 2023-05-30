package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;
import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
    private AreaChart<String, Integer> areaChart;

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
        // Bindings
        empleadosTableView.itemsProperty().bind(empleadosLP);
        
        updatePieChart();
        updateAreaChart();
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
        
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.LOGO);
        alert.setTitle(DEL_EMPLEADO_TITLE);
        alert.setHeaderText(DEL_EMPLEADO_HEAD);
        alert.setContentText(empleado.toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            // Tarea de Borrado en BBDD
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.deleteEmpleado(empleado);

                    return null;
                }
            };

            task.setOnSucceeded(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_SUCC).text(empleado.toString()).show();
                updateView();
            });

            task.setOnCancelled(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_CANCEL).text(empleado.toString()).showWarning();
                updateView();
            });

            task.setOnFailed(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_FAIL).text(empleado.toString()).showError();
                updateView();
            });

            new Thread(task).start();

        }
    }

    /*
     * Funciones
     */
    public void updateView() {
        Task<Void> tablaTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                setList();
                updateTable();

                return null;
            }
        };

        Task<Void> graficaLineTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateAreaChart();
                
                return null;
            }
        };

        Task<Void> graficaPieTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateAreaChart();
                
                return null;
            }
        };

        Platform.runLater(tablaTask);
        Platform.runLater(graficaLineTask);
        Platform.runLater(graficaPieTask);
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

    private void updatePieChart() {
        pieChart.getData().clear();

        Map<String, Integer> empleados = App.dbManager.getEmpleadosUltimoMes();

        for (String clave : empleados.keySet()) {
            pieChart.getData().add(new PieChart.Data(clave, empleados.get(clave)));
        }

    }

    private void updateAreaChart() {
        areaChart.getData().clear();

        Map<String, Map<String, Integer>> datos = App.dbManager.getDesempeñoEmpleados();
        XYChart.Series<String, Integer> seriesDatos;

        for (String nombre : datos.keySet()) {
            seriesDatos = new XYChart.Series<>();
            seriesDatos.setName(nombre);
            Map<String, Integer> insideData = datos.get(nombre);

            for (String clave : insideData.keySet()) {
                seriesDatos.getData().add(new XYChart.Data<String, Integer>(clave, insideData.get(clave)));
            }

            areaChart.getData().add(seriesDatos);
        }
    }

}
