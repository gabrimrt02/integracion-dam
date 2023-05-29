package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
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
    private LineChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableColumn<Venta, Number> totalColumn;

    @FXML
    private TableView<Venta> ventasTableView;

    @FXML
    private MenuItem moreInfoMI;

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
        setList();

        // Bindings
        ventasTableView.itemsProperty().bind(ventasLP);

        updateTable();

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

    /*
     * Funciones
     */
    public void updateView() {
        setList();

        updateTable();
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

}
