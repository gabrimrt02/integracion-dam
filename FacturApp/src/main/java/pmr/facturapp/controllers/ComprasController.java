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
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.converters.CompraConverter;
import pmr.facturapp.ui.info.InfoComprasVentasDialog;

public class ComprasController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/comprasView.fxml");

    /*
     * Variables alfanumericas
     */
    // Alert borrado Compras
    private final String DEL_COMPRA_TITLE = "Borrar Compra";
    private final String DEL_COMPRA_HEAD = "¿Seguro que desea eliminar la siguiente compra?";

    // Notificaciones Borrado
    private final String DEL_NOTIFICATION_TITLE_SUCC = "BORRADO REALIZADO CON ÉXITO";
    private final String DEL_NOTIFICATION_TITLE_FAIL = "ERROR AL REALIZAR EL BORRADO";
    private final String DEL_NOTIFICATION_TITLE_CANCEL = "BORRADO CANCELADO";

    /*
     * Model
     */
    private ListProperty<Compra> comprasLP = new SimpleListProperty<>(FXCollections.observableArrayList());

    /*
     * View
     */
    @FXML
    private TableView<Compra> comprasTableView;

    @FXML
    private TableColumn<Compra, LocalDate> fechaColumn;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableColumn<Compra, Proveedor> proveedorColumn;

    @FXML
    private TableColumn<Compra, Number> totalColumn;

    @FXML
    private MenuItem moreInfoMI;

    @FXML
    private BorderPane view;

    /*
     * Constructor
     */
    public ComprasController() {

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
        comprasTableView.itemsProperty().bind(comprasLP);

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
        InfoComprasVentasDialog dialog = new InfoComprasVentasDialog(getSelectedCompra());

        dialog.showAndWait();
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        Compra compra = comprasTableView.getSelectionModel().getSelectedItem();

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.LOGO);
        alert.setTitle(DEL_COMPRA_TITLE);
        alert.setHeaderText(DEL_COMPRA_HEAD);
        alert.setContentText(compra.toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            // Tarea de Borrado en BBDD
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.deleteCompra(compra);

                    return null;
                }
            };

            task.setOnSucceeded(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_SUCC).text(compra.toString()).show();
                updateView();
            });

            task.setOnCancelled(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_CANCEL).text(compra.toString()).showWarning();
                updateView();
            });

            task.setOnFailed(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_FAIL).text(compra.toString()).showError();
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
        comprasLP.clear();
        List<Document> docCompras = App.dbManager.getAllCompras();
        for (Document d : docCompras) {
            comprasLP.add(CompraConverter.convert(d));
        }

    }

    private void updateTable() {
        proveedorColumn.setCellValueFactory(data -> data.getValue().proveedorProperty());
        fechaColumn.setCellValueFactory(data -> data.getValue().fechaProperty());
        totalColumn.setCellValueFactory(data -> data.getValue().totalProperty());
    }

    public TableView<Compra> getComprasTableView() {
        return this.comprasTableView;
    }

    private Compra getSelectedCompra() {
        return comprasTableView.getSelectionModel().getSelectedItem();
    }

    private void updateLineChart() {
        lineChart.getData().clear();

        Map<Integer, Integer> compras = App.dbManager.getComprasUltimoMes();

        XYChart.Series<String, Integer> seriesCompras = new XYChart.Series<>();
        seriesCompras.setName("Compras");

        for (int clave : compras.keySet()) {
            seriesCompras.getData().add(new XYChart.Data<String, Integer>("" + clave, compras.get(clave)));
        }

        lineChart.getData().add(seriesCompras);
    }

    private void updatePieChart() {
        pieChart.getData().clear();

        Map<String, Integer> proveedores = App.dbManager.getProveedoresUltimoMes();

        for (String clave : proveedores.keySet()) {
            pieChart.getData().add(new PieChart.Data(clave, proveedores.get(clave)));            
        }

    }
}
