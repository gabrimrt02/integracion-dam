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
    private LineChart<?, ?> lineChart;

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
        setList();

        // Bindings
        comprasTableView.itemsProperty().bind(comprasLP);

        updateTabla();
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

    /*
     * Funciones
     */
    private void setList() {
        List<Document> docCompras = App.dbManager.getAllCompras();
        for (Document d : docCompras) {
            comprasLP.add(CompraConverter.convert(d));
        }

    }

    private void updateTabla() {
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

}
