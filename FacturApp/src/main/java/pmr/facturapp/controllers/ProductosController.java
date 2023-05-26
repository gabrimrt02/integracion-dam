package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import pmr.facturapp.App;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.statics.Unidad;
import pmr.facturapp.converters.ProductoConverter;
import pmr.facturapp.ui.info.InfoProductosDialog;

public class ProductosController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/productosView.fxml");

    /*
     * Variables alfanumericas
     */

    /*
     * Model
     */
    private ListProperty<Producto> productosLP = new SimpleListProperty<>(FXCollections.observableArrayList());

    /*
     * View
     */
    @FXML
    private TableColumn<Producto, String> nombreColumn;

    @FXML
    private TableColumn<Producto, Number> precioColumn;

    @FXML
    private TableView<Producto> productosTableView;

    @FXML
    private TableColumn<Producto, Number> stockColumn;

    @FXML
    private TableColumn<Producto, Unidad> unidadColumn;

    @FXML
    private MenuItem moreInfoMI;

    @FXML
    private BorderPane view;

    /*
     * Constructor
     */
    public ProductosController() {

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
        productosTableView.itemsProperty().bind(productosLP);

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
        InfoProductosDialog dialog = new InfoProductosDialog(getSelectedProducto());

        dialog.showAndWait();
    }

    /*
     * Funciones
     */
    private void setList() {
        List<Document> docProductos = App.dbManager.getAllProductos();
        for (Document d : docProductos) {
            productosLP.add(ProductoConverter.convert(d));
        }
    }

    private void updateTable() {
        nombreColumn.setCellValueFactory(data -> data.getValue().nombreProperty());
        precioColumn.setCellValueFactory(data -> data.getValue().precioProperty());
        stockColumn.setCellValueFactory(data -> data.getValue().stockProperty());
        unidadColumn.setCellValueFactory(data -> data.getValue().unidadProperty());
    }

    public TableView<Producto> getProductosTableView() {
        return this.productosTableView;
    }

    private Producto getSelectedProducto() {
        return productosTableView.getSelectionModel().getSelectedItem();
    }

}
