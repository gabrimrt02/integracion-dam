package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;
import org.controlsfx.control.Notifications;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    // Alert borrado Productos
    private final String DEL_PRODUCTO_TITLE = "Borrar Producto";
    private final String DEL_PRODUCTO_HEAD = "¿Seguro que desea eliminar el siguiente producto?";

    // Notificaciones Borrado
    private final String DEL_NOTIFICATION_TITLE_SUCC = "BORRADO REALIZADO CON ÉXITO";
    private final String DEL_NOTIFICATION_TITLE_FAIL = "ERROR AL REALIZAR EL BORRADO";
    private final String DEL_NOTIFICATION_TITLE_CANCEL = "BORRADO CANCELADO";

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
        // Bindings
        productosTableView.itemsProperty().bind(productosLP);

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

    @FXML
    void onEliminarAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        Producto producto = productosTableView.getSelectionModel().getSelectedItem();

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.LOGO);
        alert.setTitle(DEL_PRODUCTO_TITLE);
        alert.setHeaderText(DEL_PRODUCTO_HEAD);
        alert.setContentText(producto.toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            // Tarea de Borrado en BBDD
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.deleteProducto(producto);

                    return null;
                }
            };

            task.setOnSucceeded(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_SUCC).text(producto.toString()).show();
                updateView();
            });

            task.setOnCancelled(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_CANCEL).text(producto.toString()).showWarning();
                updateView();
            });

            task.setOnFailed(e -> {
                Notifications.create().title(DEL_NOTIFICATION_TITLE_FAIL).text(producto.toString()).showError();
                updateView();
            });

            new Thread(task).start();

        }
    }

    /*
     * Funciones
     */
    public void updateView() {
        setList();

        updateTable();
    }

    private void setList() {
        productosLP.clear();
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
