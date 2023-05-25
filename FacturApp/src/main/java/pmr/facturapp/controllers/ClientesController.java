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
import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.statics.TipoCliente;
import pmr.facturapp.converters.ClienteConverter;

public class ClientesController implements Initializable {

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/secciones/clientesView.fxml");

    /*
     * Varibales alfanumericas
     */
    

    /*
     * Model
     */
    private ListProperty<Cliente> clientesLP = new SimpleListProperty<>(FXCollections.observableArrayList());

    /*
     * View
     */
    @FXML
    private TableView<Cliente> clientesTableView;

    @FXML
    private TableColumn<Cliente, Domicilio> domicilioColumn;

    @FXML
    private TableColumn<Cliente, String> emailColumn;

    @FXML
    private TableColumn<Cliente, Integer> indexColumn;

    @FXML
    private TableColumn<Cliente, String> nombreColumn;

    @FXML
    private TableColumn<Cliente, String> telefonoColumn;

    @FXML
    private TableColumn<Cliente, TipoCliente> tipoColumn;

    @FXML
    private BorderPane view;

    /*
     * Constructor
     */
    public ClientesController() {

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
        clientesTableView.itemsProperty().bind(clientesLP);

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
        List<Document> docClientes = App.dbManager.getAllClientes();
        for (Document d : docClientes) {
            clientesLP.add(ClienteConverter.convert(d));
        }
    }

    private void updateTable() {
        nombreColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getNombre() + " " + data.getValue().getApellido()));
        tipoColumn.setCellValueFactory(data -> data.getValue().tipoClienteProperty());
        domicilioColumn.setCellValueFactory(data -> data.getValue().domicilioProperty());
        telefonoColumn.setCellValueFactory(data -> data.getValue().telefonoProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().mailProperty());
    }

}
