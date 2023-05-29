package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.bson.Document;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
    private LineChart<?, ?> weekLineChart;

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
        userLabel.textProperty().set(App.USERNAME);
        dateLabel.textProperty().set(LocalDate.now().toString());
        // balanceLabel.textProperty().set("1000€");

        setList();

        // Bindings
        movimientosTableView.itemsProperty().bind(transaccionesLP);

        updateTable();

        // Como crear una grafíca
        monthLineChart.setTitle("Comparativa de meses");

        updateMonthLineChart();

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
    private void setList() {
        List<Document> docCompras = App.dbManager.getAllCompras();
        for (Document d : docCompras) {
            transaccionesLP.add(CompraConverter.convert(d));
        }

        List<Document> docVentas = App.dbManager.getAllVentas();
        for (Document d : docVentas) {
            transaccionesLP.add(VentaConverter.convert(d));
        }

        transaccionesLP.setAll(transaccionesLP.sorted());
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
        Map<String, Integer> ventas = App.dbManager.getVentasUltimoMes();
        Map<String, Integer> compras = App.dbManager.getComprasUltimoMes();

        XYChart.Series<String, Number> seriesVentas = new XYChart.Series<>();
        seriesVentas.setName(LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));

        for (String clave : ventas.keySet()) {
            seriesVentas.getData().add(new XYChart.Data<String, Number>(clave, ventas.get(clave)));
        }

        XYChart.Series<String, Number> seriesCompras = new XYChart.Series<>();
        seriesCompras.setName(LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));

        for (String clave : compras.keySet()) {
            seriesCompras.getData().add(new XYChart.Data<String, Number>(clave, compras.get(clave)));
        }

        monthLineChart.getData().add(seriesVentas);
        monthLineChart.getData().add(seriesCompras);

    }

    // private void updateWeekLineChart() {
    // Map<String, Integer> ventas = new HashMap<>();
    // }

}
