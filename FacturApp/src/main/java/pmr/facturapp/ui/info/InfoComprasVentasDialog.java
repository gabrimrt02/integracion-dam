package pmr.facturapp.ui.info;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pmr.facturapp.App;
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Transaccion;
import pmr.facturapp.classes.Venta;

public class InfoComprasVentasDialog extends Dialog<Transaccion> implements Initializable {
    
    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/popups/info/comprasVentasInfoView.fxml");

    /*
     * Variables alfanuméricas
     */
    private final String DIALOG_TITLE = "Información";
    private final String ENCARGADO_ADMIN = "Administrador";

    /*
     * Model
     */
    private Transaccion transaccion;

    /*
     * View
     */
    @FXML
    private TextField encargadoTextField;

    @FXML
    private TextField fechaTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private JFXListView<Producto> productosListView;

    @FXML
    private GridPane view;

    /*
     * Constructor
     */
    public InfoComprasVentasDialog(Transaccion transaccion) {
        this.transaccion = transaccion;

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
        // Config
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().addAll(App.LOGO);
        setTitle(DIALOG_TITLE);
        getDialogPane().setContent(view);
        getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

        idTextField.setText(transaccion.getId().toString());
        fechaTextField.setText(transaccion.getFecha().toString());
        productosListView.getItems().setAll(transaccion.getProductos());

        if (transaccion instanceof Venta) {
            Venta venta = (Venta) transaccion;

            nombreTextField.setText(venta.getCliente().toString());
            encargadoTextField.setText(venta.getEmpleado().toString());

        } else if (transaccion instanceof Compra) {
            Compra compra = (Compra) transaccion;

            nombreTextField.setText(compra.getProveedor().toString());
            encargadoTextField.setText(ENCARGADO_ADMIN);

        }

    }

}
