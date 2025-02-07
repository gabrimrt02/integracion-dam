package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;
import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Venta;
import pmr.facturapp.converters.VentaConverter;
import pmr.facturapp.ui.info.InfoComprasVentasDialog;

public class VentasController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/ventasView.fxml");

    /*
     * Variables alfanumericas
     */
    // Alert borrado Proveedores
    private final String DEL_VENTA_TITLE = "Borrado Proveedor";
    private final String DEL_VENTA_HEAD = "¿Seguro que desea eliminar el siguiente proveedor?";
    
    // Notificaciones Borrado
    private final String DEL_NOTIFICATION_TITLE_SUCC = "BORRADO REALIZADO CON ÉXITO";
    private final String DEL_NOTIFICATION_TITLE_FAIL = "ERROR AL REALIZAR EL BORRADO";
    private final String DEL_NOTIFICATION_TITLE_CANCEL = "BORRADO CANCELADO";

    /*
     * Model
     */
    private ListProperty<Venta> ventasLP = new SimpleListProperty<>(FXCollections.observableArrayList());

    /*
     * View
     */
    @FXML
    private TableColumn<Venta, Cliente> clienteColumn;

    @FXML
    private TableColumn<Venta, LocalDate> fechaColumn;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableColumn<Venta, Number> totalColumn;

    @FXML
    private TableView<Venta> ventasTableView;

    @FXML
    private MenuItem moreInfoMI;

    @FXML
    private MenuItem eliminarMI;

    @FXML
    private BorderPane view;

    /*
     * Constructor
     */
    public VentasController() {

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
        ventasTableView.itemsProperty().bind(ventasLP);

        updateLineChart();

        updatePieChart();
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
    void onMoreInfoAction(ActionEvent event) {
        InfoComprasVentasDialog dialog = new InfoComprasVentasDialog(getSelectedVenta());

        dialog.showAndWait();
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        Venta venta = ventasTableView.getSelectionModel().getSelectedItem();

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.LOGO);
        alert.setTitle(DEL_VENTA_TITLE);
        alert.setHeaderText(DEL_VENTA_HEAD);
        alert.setContentText(venta.toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            // Tarea de Borrado en BBDD
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.deleteVenta(venta);

                    return null;
                }
            };

            task.setOnSucceeded(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_SUCC).text(venta.toString()).show();
                updateView();
            });

            task.setOnCancelled(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_CANCEL).text(venta.toString()).showWarning();
                updateView();
            });

            task.setOnFailed(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_FAIL).text(venta.toString()).showError();
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

        Task<Void> graficaLineaTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateLineChart();
                
                return null;
            }
        };

        Task<Void> graficaPieTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updatePieChart();

                return null;
            }
        };

        Platform.runLater(tablaTask);
        Platform.runLater(graficaLineaTask);
        Platform.runLater(graficaPieTask);
    }

    private void setList() {
        ventasLP.clear();
        List<Document> docVentas = App.dbManager.getAllVentas();
        for (Document d : docVentas) {
            ventasLP.add(VentaConverter.convert(d));
        }
    }

    private void updateTable() {
        clienteColumn.setCellValueFactory(data -> data.getValue().clienteProperty());
        fechaColumn.setCellValueFactory(data -> data.getValue().fechaProperty());
        totalColumn.setCellValueFactory(data -> data.getValue().totalProperty());
    }

    public TableView<Venta> getVentasTableView() {
        return this.ventasTableView;
    }

    private Venta getSelectedVenta() {
        return ventasTableView.getSelectionModel().getSelectedItem();
    }

    private void updateLineChart() {
        lineChart.getData().clear();

        Map<Integer, Integer> ventas = App.dbManager.getVentasUltimoMes();
        
        XYChart.Series<String, Integer> seriesVentas = new XYChart.Series<>();
        seriesVentas.setName("Ventas");

        for (int clave : ventas.keySet()) {
            seriesVentas.getData().add(new XYChart.Data<String, Integer>("" + clave, ventas.get(clave)));
        }

        lineChart.getData().add(seriesVentas);
    }

    private void updatePieChart() {
        pieChart.getData().clear();

        Map<String, Integer> proveedores = App.dbManager.getClientesUltimoMes();

        for (String clave : proveedores.keySet()) {
            pieChart.getData().add(new PieChart.Data(clave, proveedores.get(clave)));            
        }
    }
}
