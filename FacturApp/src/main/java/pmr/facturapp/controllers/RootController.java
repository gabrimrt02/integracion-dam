package pmr.facturapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import pmr.facturapp.App;
import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Empleado;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.classes.statics.TipoCliente;
import pmr.facturapp.classes.statics.Unidad;
import pmr.facturapp.ui.add.AddClienteDialog;
import pmr.facturapp.ui.add.AddCompraDialog;
import pmr.facturapp.ui.add.AddEmpleadoDialog;
import pmr.facturapp.ui.add.AddProductoDialog;
import pmr.facturapp.ui.add.AddProveedorDialog;

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
    // Notificaciones Insertado
    private final String ADD_NOTIFICATION_TITLE_SUCC = "INSERTADO REALIZADO CON ÉXITO";
    private final String ADD_NOTIFICATION_TITLE_FAIL = "ERROR AL REALIZAR EL INSERTADO";
    private final String ADD_NOTIFICATION_TITLE_CANCEL = "INSERTADO CANCELADO";

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

        onVerInicioAction(null);

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

            Cliente cliente = new Cliente(TipoCliente, nombre, apellidos, domicilio, telefono, email);

            // Tarea de Registro en BBDD
            App.tareaRegistro = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.insertCliente(cliente);

                    return null;
                }
            };

            App.tareaRegistro.setOnSucceeded(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_SUCC).text(cliente.toString()).show();
                C_CLIENTES.updateView();
            });

            App.tareaRegistro.setOnCancelled(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_CANCEL).text(cliente.toString()).showWarning();
                C_CLIENTES.updateView();
            });

            App.tareaRegistro.setOnFailed(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_FAIL).text(cliente.toString()).showError();
                C_CLIENTES.updateView();
            });

            new Thread(App.tareaRegistro).start();
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

            // Tarea de Registro en BBDD
            App.tareaRegistro = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.insertCompra(compra);

                    return null;
                }
            };

            App.tareaRegistro.setOnSucceeded(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_SUCC).text(compra.toString()).show();
                C_COMPRAS.updateView();
            });

            App.tareaRegistro.setOnCancelled(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_CANCEL).text(compra.toString()).showWarning();
                C_COMPRAS.updateView();
            });

            App.tareaRegistro.setOnFailed(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_FAIL).text(compra.toString()).showError();
                C_COMPRAS.updateView();
            });

            new Thread(App.tareaRegistro).start();
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

            // Tarea de Registro en BBDD
            App.tareaRegistro = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.insertEmpleado(empleado);

                    return null;
                }
            };

            App.tareaRegistro.setOnSucceeded(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_SUCC).text(empleado.toString()).show();
                C_EMPLEADOS.updateView();
            });

            App.tareaRegistro.setOnCancelled(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_CANCEL).text(empleado.toString()).showWarning();
                C_EMPLEADOS.updateView();
            });

            App.tareaRegistro.setOnFailed(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_FAIL).text(empleado.toString()).showError();
                C_EMPLEADOS.updateView();
            });

            new Thread(App.tareaRegistro).start();
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

            // Tarea de Registro en BBDD
            App.tareaRegistro = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.insertProducto(producto);

                    return null;
                }
            };

            App.tareaRegistro.setOnSucceeded(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_SUCC).text(producto.toString()).show();
                C_PRODUCTOS.updateView();
            });

            App.tareaRegistro.setOnCancelled(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_CANCEL).text(producto.toString()).showWarning();
                C_PRODUCTOS.updateView();
            });

            App.tareaRegistro.setOnFailed(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_FAIL).text(producto.toString()).showError();
                C_PRODUCTOS.updateView();
            });

            new Thread(App.tareaRegistro).start();

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

            // Tarea de Registro en BBDD
            App.tareaRegistro = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    App.dbManager.insertProveedor(proveedor);

                    return null;
                }
            };

            App.tareaRegistro.setOnSucceeded(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_SUCC).text(proveedor.toString()).show();
                C_PROVEEDORES.updateView();
            });

            App.tareaRegistro.setOnCancelled(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_CANCEL).text(proveedor.toString()).showWarning();
                C_PROVEEDORES.updateView();
            });

            App.tareaRegistro.setOnFailed(e -> {
                Notifications.create().title(ADD_NOTIFICATION_TITLE_FAIL).text(proveedor.toString()).showError();
                C_PROVEEDORES.updateView();
            });

            new Thread(App.tareaRegistro).start();

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
        C_PRODUCTOS.updateView();
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

    /*
     * Funciones
     */

}
