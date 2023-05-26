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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
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
    private StackedAreaChart<?, ?> stackAreaChart;

    @FXML
    private TableColumn<Empleado, String> telefonoColumn;

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
        setList();

        // Bindings
        empleadosTableView.itemsProperty().bind(empleadosLP);

        updateTabla();
    }

    /*
     * GetView
     */
    public BorderPane getView() {
        return this.view;
    }

    /*
     * GetTableView
     */
    public TableView<Empleado> getEmpleadosTableView() {
        return this.empleadosTableView;
    }

    /*
     * Funciones
     */
    private void setList() {
        List<Document> docEmpleados = App.dbManager.getAllEmpleados();
        for (Document d : docEmpleados) {
            empleadosLP.add(EmpleadoConverter.convert(d));
        }
    }

    private void updateTabla() {
        nombreColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getNombre() + " " + data.getValue().getApellido()));
        telefonoColumn.setCellValueFactory(data -> data.getValue().telefonoProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().mailProperty());
    }

}
