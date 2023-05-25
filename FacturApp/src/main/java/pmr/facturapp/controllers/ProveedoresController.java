package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import pmr.facturapp.App;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.converters.ProveedorConverter;

public class ProveedoresController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/proveedoresView.fxml");

    /*
     * Variables alfanumericas
     */

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
    private TableColumn<Proveedor, Number> indexColumn;

    @FXML
    private TableColumn<Proveedor, String> nombreColumn;

    @FXML
    private TableView<Proveedor> proveedoresTableView;

    @FXML
    private TableColumn<Proveedor, String> telefonoColumn;

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
        setList();

        // Bindings
        proveedoresTableView.itemsProperty().bind(proveedoresLP);

        updateTable();
    }

    /*
     * GetView
     */
    public BorderPane getView() {
        return this.view;
    }

    /*
     * Funciones
     */
    private void setList() {
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

}
