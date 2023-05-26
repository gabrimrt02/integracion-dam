package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Empleado;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.classes.statics.TipoCliente;
import pmr.facturapp.classes.statics.Unidad;
import pmr.facturapp.ui.AddClienteDialog;
import pmr.facturapp.ui.AddCompraDialog;
import pmr.facturapp.ui.AddEmpleadoDialog;
import pmr.facturapp.ui.AddProductoDialog;
import pmr.facturapp.ui.AddProveedorDialog;

public class RootController implements Initializable {

    /*
     * Controladores de las secciones
     */
    private final InicioController C_INICIO = new InicioController();
    private final VentasController C_VENTAS = new VentasController();
    private final ComprasController C_COMPRAS = new ComprasController();
    private final ClientesController C_CLIENTES = new ClientesController();
    private final ProveedoresController C_PROVEEDORES = new ProveedoresController();
    private final EmpleadosController C_EMPLEADOS = new EmpleadosController();
    private final ProductosController C_PRODUCTOS = new ProductosController();

    /*
     * Variables URL
     */
    private final URL FICHERO_VIEW = getClass().getResource("/fxml/rootView.fxml");

    /*
     * Variables alfanuméricas
     */
    // Alert borrado Clientes
    private final String DEL_CLIENTES_TITLE = "Borrar Cliente";
    private final String DEL_CLIENTES_HEAD = "¿Seguro que desea eliminar el siguiente cliente?";
    
    // Alert borrado Compras
    private final String DEL_COMPRA_TITLE = "Borrar Compra";
    private final String DEL_COMPRA_HEAD = "¿Seguro que desea eliminar la siguiente compra?";

    // Alert borrardo Empleados
    private final String DEL_EMPLEADO_TITLE = "Borrar Empleado";
    private final String DEL_EMPLEADO_HEAD = "¿Seguro que desea eliminar el siguiente empleado?";

    // Alert borrado Productos
    private final String DEL_PRODUCTO_TITLE = "Borrar Producto";
    private final String DEL_PRODUCTO_HEAD = "¿Seguro que desea eliminar el siguiente producto?";

    // Alert borrado Proveedores
    private final String DEL_PROVEEDOR_TITLE = "Borrado Proveedor";
    private final String DEL_PROVEEDOR_HEAD = "¿Seguro que desea eliminar el siguiente proveedor?";

    /*
     * Model
     */

    /*
     * View
     */
    @FXML
    private MenuItem addClientesMI;

    @FXML
    private MenuItem addCompraMI;

    @FXML
    private MenuItem addEmpleadosMI;

    @FXML
    private MenuItem addProductosMI;

    @FXML
    private MenuItem addProveedoresMI;

    @FXML
    private MenuItem delClientesMI;

    @FXML
    private MenuItem delCompraMI;

    @FXML
    private MenuItem delEmpleadosMI;

    @FXML
    private MenuItem delProductosMI;

    @FXML
    private MenuItem delProveedoresMI;

    @FXML
    private MenuItem verClientesMI;

    @FXML
    private MenuItem verComprasMI;

    @FXML
    private MenuItem verEmpleadosMI;

    @FXML
    private MenuItem verInicioMI;

    @FXML
    private MenuItem verProductosMI;

    @FXML
    private MenuItem verProveedoresMI;

    @FXML
    private MenuItem verVentasMI;

    @FXML
    private BorderPane view;

    /*
     * Constructor
     */
    public RootController() {

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

        // Bindings disponibilidad opción borrado
        delClientesMI.disableProperty()
                .bind(C_CLIENTES.getClientesTableView().getSelectionModel().selectedIndexProperty().isEqualTo(-1));

        delCompraMI.disableProperty()
                .bind(C_COMPRAS.getComprasTableView().getSelectionModel().selectedIndexProperty().isEqualTo(-1));

        delEmpleadosMI.disableProperty()
                .bind(C_EMPLEADOS.getEmpleadosTableView().getSelectionModel().selectedIndexProperty().isEqualTo(-1));

        delProductosMI.disableProperty()
                .bind(C_PRODUCTOS.getProductosTableView().getSelectionModel().selectedIndexProperty().isEqualTo(-1));

        delProveedoresMI.disableProperty().bind(
                C_PROVEEDORES.getProveedoresTableView().getSelectionModel().selectedIndexProperty().isEqualTo(-1));

    }

    /*
     * GetView
     */
    public BorderPane getView() {
        return this.view;
    }

    /*
     * Funciones View
     */
    @FXML
    void onAddClientesAction(ActionEvent event) {
        AddClienteDialog clienteDialog = new AddClienteDialog();

        Optional<Cliente> resultado = clienteDialog.showAndWait();

        if (resultado.isPresent()) {
            String nombre = clienteDialog.getNombre();
            String apellidos = clienteDialog.getApellidos();
            Domicilio domicilio = new Domicilio(clienteDialog.getProvincia(), clienteDialog.getMunicipio());
            TipoCliente TipoCliente = clienteDialog.getTipoCliente();
            String telefono = clienteDialog.getTelefono();
            String email = clienteDialog.getMail();

            Cliente nuevoCliente = new Cliente(TipoCliente, nombre, apellidos, domicilio, telefono, email);

            System.out.println(nuevoCliente);
        }
    }

    @FXML
    void onAddComprasAction(ActionEvent event) {
        AddCompraDialog dialog = new AddCompraDialog();

        Optional<Compra> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            Proveedor proveedor = dialog.getProveedor();
            List<Producto> productos = dialog.getProductos();
            LocalDate fecha = dialog.getFecha();

            Compra compra = new Compra(proveedor, productos, fecha);

            System.out.println(compra);
        }
    }

    @FXML
    void onAddEmpleadosAction(ActionEvent event) {
        AddEmpleadoDialog dialog = new AddEmpleadoDialog();

        Optional<Empleado> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            String nombre = dialog.getNombre();
            String apellido = dialog.getApellidos();
            Domicilio domicilio = new Domicilio(dialog.getProvincia(), dialog.getMunicipio());
            String telefono = dialog.getTelefono();
            String email = dialog.getMail();

            Empleado empleado = new Empleado(nombre, apellido, domicilio, telefono, email);

            System.out.println(empleado);
        }

    }

    @FXML
    void onAddProductosAction(ActionEvent event) {
        AddProductoDialog dialog = new AddProductoDialog();

        Optional<Producto> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            String nombre = dialog.getNombre();
            String descripcion = dialog.getDescripcion();
            Double precio = dialog.getPrecio();
            int stock = dialog.getStock();
            Unidad unidad = dialog.getUnidad();

            Producto producto = new Producto(nombre, descripcion, precio, stock, unidad);

            System.out.println(producto);
        }

    }

    @FXML
    void onAddProveedoresAction(ActionEvent event) {
        AddProveedorDialog dialog = new AddProveedorDialog();

        Optional<Proveedor> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            String nombre = dialog.getNombre();
            String apellidos = dialog.getApellidos();
            Domicilio domicilio = new Domicilio(dialog.getProvincia(), dialog.getMunicipio());
            String telefono = dialog.getTelefono();
            String email = dialog.getMail();

            Proveedor proveedor = new Proveedor(nombre, apellidos, domicilio, telefono, email);

            System.out.println(proveedor);
        }
    }

    @FXML
    void onDelClientesAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle(DEL_CLIENTES_TITLE);
        alert.setHeaderText(DEL_CLIENTES_HEAD);
        alert.setContentText(C_CLIENTES.getClientesTableView().getSelectionModel().getSelectedItem().toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            System.out.println("Eliminado");
        }

    }

    @FXML
    void onDelComprasAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle(DEL_COMPRA_TITLE);
        alert.setHeaderText(DEL_COMPRA_HEAD);
        alert.setContentText(C_COMPRAS.getComprasTableView().getSelectionModel().getSelectedItem().toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            System.out.println("Eliminado");
        }
        
    }

    @FXML
    void onDelEmpleadosAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle(DEL_EMPLEADO_TITLE);
        alert.setHeaderText(DEL_EMPLEADO_HEAD);
        alert.setContentText(C_EMPLEADOS.getEmpleadosTableView().getSelectionModel().getSelectedItem().toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            System.out.println("Eliminado");
        }
    }

    @FXML
    void onDelProductosAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle(DEL_PRODUCTO_TITLE);
        alert.setHeaderText(DEL_PRODUCTO_HEAD);
        alert.setContentText(C_PRODUCTOS.getProductosTableView().getSelectionModel().getSelectedItem().toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            System.out.println("Eliminado");
        }
    }

    @FXML
    void onDelProveedoresAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle(DEL_PROVEEDOR_TITLE);
        alert.setHeaderText(DEL_PROVEEDOR_HEAD);
        alert.setContentText(C_PROVEEDORES.getProveedoresTableView().getSelectionModel().getSelectedItem().toString());

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get().getButtonData() == ButtonData.OK_DONE) {
            System.out.println("Eliminado");
        }
    }

    @FXML
    void onVerClientesAction(ActionEvent event) {
        view.setCenter(C_CLIENTES.getView());
    }

    @FXML
    void onVerComprasAction(ActionEvent event) {
        view.setCenter(C_COMPRAS.getView());
    }

    @FXML
    void onVerEmpleadosAction(ActionEvent event) {
        view.setCenter(C_EMPLEADOS.getView());
    }

    @FXML
    void onVerInicioAction(ActionEvent event) {
        view.setCenter(C_INICIO.getView());
    }

    @FXML
    void onVerProductosAction(ActionEvent event) {
        view.setCenter(C_PRODUCTOS.getView());
    }

    @FXML
    void onVerProveedoresAction(ActionEvent event) {
        view.setCenter(C_PROVEEDORES.getView());
    }

    @FXML
    void onVerVentasAction(ActionEvent event) {
        view.setCenter(C_VENTAS.getView());
    }

}
