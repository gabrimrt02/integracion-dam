package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.bson.Document;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import pmr.facturapp.App;
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Transaccion;
import pmr.facturapp.classes.Venta;
import pmr.facturapp.converters.CompraConverter;
import pmr.facturapp.converters.VentaConverter;

public class InicioController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/inicioView.fxml");

    /*
     * Variables alfanuméricas
     */

    /*
     * Model
     */
    private ListProperty<Transaccion> transaccionesLP = new SimpleListProperty<>(FXCollections.observableArrayList());

    /*
     * View
     */
    @FXML
    private Label balanceLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TableColumn<Transaccion, LocalDate> fechaColumn;

    @FXML
    private LineChart<String, Number> monthLineChart;

    @FXML
    private TableView<Transaccion> movimientosTableView;

    @FXML
    private TableColumn<Transaccion, String> nombreColumn;

    @FXML
    private TableColumn<Transaccion, Number> totalColumn;

    @FXML
    private Label userLabel;

    @FXML
    private BorderPane view;

    @FXML
    private LineChart<String, Number> weekLineChart;

    /*
     * Constructor
     */
    public InicioController() {

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
        movimientosTableView.itemsProperty().bind(transaccionesLP);

    }

    /*
     * GetView
     */
    public BorderPane getView() {
        return this.view;
    }

    /**
     * Funciones
     */
    public void updateView() {
        userLabel.textProperty().set(App.getEmpleado().getNombre());
        dateLabel.textProperty().set(LocalDate.now().toString());
        monthLineChart.setTitle("Comparativa Mensual");
        weekLineChart.setTitle("Comparativa Semanal");

        Task<Void> balance = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Double totalVentas = App.dbManager.getTotalVentas();
                Double totalCompras = App.dbManager.getTotalCompras();

                Double balanceFinal = totalVentas - totalCompras;

                balanceLabel.textProperty().set(String.format("%.2f €", balanceFinal));

                return null;
            }
        };

        Task<Void> tabla = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                setList();
                updateTable();

                return null;
            }
        };

        Task<Void> graficaMes = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMonthLineChart();

                return null;
            }
        };

        Task<Void> graficaSemana = new Task<Void>() {
            @Override
            protected Void call() throws Exception {                
                updateWeekLineChart();

                return null;
            }
        };

        new Thread(tabla).start();

        Platform.runLater(balance);
        Platform.runLater(graficaMes);
        Platform.runLater(graficaSemana);
    }

    private void setList() {
        List<Document> docCompras = App.dbManager.getAllCompras();
        List<Document> docVentas = App.dbManager.getAllVentas();
        List<Compra> compras = new ArrayList<>();
        List<Venta> ventas = new ArrayList<>();
        List<Transaccion> transaccionesList = new ArrayList<>();

        for (Document d : docCompras) {
            compras.add(CompraConverter.convert(d));
        }

        for (Document d : docVentas) {
            ventas.add(VentaConverter.convert(d));
        }

        transaccionesList.addAll(compras);
        transaccionesList.addAll(ventas);

        transaccionesLP.get().setAll(transaccionesList);
    }

    private void updateTable() {
        nombreColumn.setCellValueFactory(data -> {
            Transaccion t = data.getValue();
            if (t instanceof Compra) {
                Compra c = (Compra) t;
                return new SimpleStringProperty(c.getProveedor().getNombre() + " " + c.getProveedor().getApellido());
            } else if (t instanceof Venta) {
                Venta v = (Venta) t;
                return new SimpleStringProperty(v.getCliente().getNombre() + " " + v.getCliente().getApellido());
            } else {
                return new SimpleStringProperty("");
            }
        });

        fechaColumn.setCellValueFactory(data -> data.getValue().fechaProperty());
        totalColumn.setCellValueFactory(data -> data.getValue().totalProperty());
    }

    private void updateMonthLineChart() {
        monthLineChart.getData().clear();

        Map<String, Integer> ventas = App.dbManager.getVentasUltimoMes();
        Map<String, Integer> compras = App.dbManager.getComprasUltimoMes();

        XYChart.Series<String, Number> seriesVentas = new XYChart.Series<>();
        seriesVentas.setName("Ventas");

        XYChart.Series<String, Number> seriesCompras = new XYChart.Series<>();
        seriesCompras.setName("Compras");

        for (String clave : ventas.keySet()) {
            seriesVentas.getData().add(new XYChart.Data<String, Number>(clave, ventas.get(clave)));
        }

        for (String clave : compras.keySet()) {
            seriesCompras.getData().add(new XYChart.Data<String, Number>(clave, compras.get(clave)));
        }

        monthLineChart.getData().add(seriesVentas);
        monthLineChart.getData().add(seriesCompras);

    }

    private void updateWeekLineChart() {
        weekLineChart.getData().clear();

        Map<String, Integer> ventas = App.dbManager.getVentasUltimaSemana();
        Map<String, Integer> compras = App.dbManager.getComprasUltimaSemana();

        XYChart.Series<String, Number> seriesVentas = new XYChart.Series<>();
        seriesVentas.setName("Ventas");

        XYChart.Series<String, Number> seriesCompras = new XYChart.Series<>();
        seriesCompras.setName("Compras");

        for (String clave : ventas.keySet()) {
            seriesVentas.getData().add(new XYChart.Data<String, Number>(clave, ventas.get(clave)));
        }

        for (String clave : compras.keySet()) {
            seriesCompras.getData().add(new XYChart.Data<String, Number>(clave, compras.get(clave)));
        }

        weekLineChart.getData().add(seriesVentas);
        weekLineChart.getData().add(seriesCompras);

    }

}
