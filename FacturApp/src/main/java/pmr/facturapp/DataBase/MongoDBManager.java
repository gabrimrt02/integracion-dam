package pmr.facturapp.database;

import java.io.IOException;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Empleado;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.classes.Venta;
import pmr.facturapp.converters.ClienteConverter;
import pmr.facturapp.converters.CompraConverter;
import pmr.facturapp.converters.EmpleadoConverter;
import pmr.facturapp.converters.ProductoConverter;
import pmr.facturapp.converters.ProveedorConverter;
import pmr.facturapp.converters.VentaConverter;

import static com.mongodb.client.model.Sorts.descending;

public class MongoDBManager {

    /**
     * Atributos de la clase MongoDBManager que almacenan los nombres de las
     * colecciones y la base de datos utilizados en la aplicación.
     */
    private final String BBDD_NAME = "FacturApp";
    private final String COL_CIENTES = "Clientes";
    private final String COL_EMPLEADOS = "Empleados";
    private final String COL_PRODUCTOS = "Productos";
    private final String COL_PROVEEDORES = "Proveedores";
    private final String COL_COMPRAS = "Compras";
    private final String COL_VENTAS = "Ventas";
    private final String COL_USUARIOS = "Usuarios";

    /**
     * Atributo de la clase MongoDBManager que almacena los datos de configuración
     * de la conexión con la base de datos de MongoDB.
     */
    private Properties properties;

    /**
     * Atributos de la clase MongoDBManager que almacenan los objetos y valores
     * necesarios para la conexión con la base de datos de MongoDB.
     */
    private String connString;
    private ConnectionString conn;
    private MongoClient client;
    private MongoClientSettings clientSettings;
    private MongoDatabase database;
    private MongoCollection<Document> clientes, empleados, productos, proveedores, compras, ventas, usuarios;

    /**
     * Constructor de la clase MongoDBManager, encargado de establecer la conexión
     * con la base de datos de MongoDB utilizando un archivo de propiedades.
     * Recupera las colecciones de datos de la base de datos y las almacena en
     * variables de clase para su posterior uso.
     *
     * @throws IOException si ocurre un error al leer el archivo de propiedades.
     */
    private MongoDBManager() throws IOException {
        properties = new Properties();

        // Apertura del fichero de properties y retorno de la clave de conexión
        properties.load(MongoDBManager.class.getResourceAsStream("/properties/mongoConn.properties"));
        connString = properties.getProperty("connString");

        // Conexión con la base de datos de MongoDB
        conn = new ConnectionString(connString);
        clientSettings = MongoClientSettings.builder().applyConnectionString(conn).build();

        // Creación del cliente
        client = MongoClients.create(clientSettings);

        // Recuperación de la BBDD
        database = client.getDatabase(BBDD_NAME);

        // Recuperación de las colecciones de datos
        clientes = database.getCollection(COL_CIENTES);
        empleados = database.getCollection(COL_EMPLEADOS);
        productos = database.getCollection(COL_PRODUCTOS);
        proveedores = database.getCollection(COL_PROVEEDORES);
        compras = database.getCollection(COL_COMPRAS);
        ventas = database.getCollection(COL_VENTAS);
        usuarios = database.getCollection(COL_USUARIOS);
    }

    /**
     * Intenta conectarse a una instancia de MongoDB utilizando la clase
     * MongoDBManager. Si la conexión es exitosa, devuelve true, de lo contrario,
     * devuelve false.
     *
     * @return manager si la conexión fue exitosa, null de lo contrario.
     */
    public static MongoDBManager connect() {
        try {
            MongoDBManager manager = new MongoDBManager();
            return manager;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        client.close();
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     * 
     * @param cliente el objeto Cliente a ser insertado
     */
    public void insertCliente(Cliente cliente) {
        Document documentoCliente = ClienteConverter.convert(cliente);
        clientes.insertOne(new Document("cliente", documentoCliente));
    }

    /**
     * Inserta un nuevo empleado en la base de datos.
     * 
     * @param empleado el objeto Empleado a ser insertado
     */
    public void insertEmpleado(Empleado empleado) {
        Document documentoEmpleado = EmpleadoConverter.convert(empleado);
        empleados.insertOne(new Document("empleado", documentoEmpleado));
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     * 
     * @param producto el objeto Producto a ser insertado
     */
    public void insertProducto(Producto producto) {
        Document documentoProducto = ProductoConverter.convert(producto);
        productos.insertOne(new Document("producto", documentoProducto));
    }

    /**
     * Inserta un nuevo proveedor en la base de datos.
     * 
     * @param proveedor el objeto Proveedor a ser insertado
     */
    public void insertProveedor(Proveedor proveedor) {
        Document documentoProveedor = ProveedorConverter.convert(proveedor);
        proveedores.insertOne(new Document("proveedor", documentoProveedor));
    }

    /**
     * Inserta un nuevo compra en la base de datos.
     * 
     * @param proveedor el objeto Compra a ser insertado
     */
    public void insertCompra(Compra compra) {
        Document documentoCompra = CompraConverter.convert(compra);
        compras.insertOne(new Document("compra", documentoCompra));
    }

    /**
     * Inserta un nuevo venta en la base de datos.
     * 
     * @param proveedor el objeto Venta a ser insertado
     */
    public void insertVenta(Venta venta) {
        Document documentoVenta = VentaConverter.convert(venta);
        ventas.insertOne(new Document("venta", documentoVenta));
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Clientes".
     *
     * @return Una lista de documentos de tipo "Document" con todos los clientes
     *         almacenados en la base de datos.
     */
    public List<Document> getAllClientes() {
        ArrayList<Document> documentosClientes = new ArrayList<>();
        MongoCursor<Document> cursor = clientes.find().cursor();
        while (cursor.hasNext()) {
            documentosClientes.add(cursor.next());
        }
        return documentosClientes;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Empleados".
     *
     * @return Una lista de documentos de tipo "Document" con todos los empleados
     *         almacenados en la base de datos.
     */
    public List<Document> getAllEmpleados() {
        ArrayList<Document> documentosEmpleados = new ArrayList<>();
        MongoCursor<Document> cursor = empleados.find().cursor();
        while (cursor.hasNext()) {
            documentosEmpleados.add(cursor.next());
        }
        return documentosEmpleados;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Productos".
     *
     * @return Una lista de documentos de tipo "Document" con todos los productos
     *         almacenados en la base de datos.
     */
    public List<Document> getAllProductos() {
        ArrayList<Document> documentosProductos = new ArrayList<>();
        MongoCursor<Document> cursor = productos.find().cursor();
        while (cursor.hasNext()) {
            documentosProductos.add(cursor.next());
        }
        return documentosProductos;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Proveedores".
     *
     * @return Una lista de documentos de tipo "Document" con todos los proveedores
     *         almacenados en la base de datos.
     */
    public List<Document> getAllProveedores() {
        ArrayList<Document> documentosProveedores = new ArrayList<>();
        MongoCursor<Document> cursor = proveedores.find().cursor();
        while (cursor.hasNext()) {
            documentosProveedores.add(cursor.next());
        }
        return documentosProveedores;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Compras".
     *
     * @return Una lista de documentos de tipo "Document" con todos las compras
     *         almacenados en la base de datos.
     */
    public List<Document> getAllCompras() {
        ArrayList<Document> documentosCompras = new ArrayList<>();
        MongoCursor<Document> cursor = compras.find().cursor();
        while (cursor.hasNext()) {
            documentosCompras.add(cursor.next());
        }
        return documentosCompras;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Ventas".
     *
     * @return Una lista de documentos de tipo "Document" con todos las ventas
     *         almacenados en la base de datos.
     */
    public List<Document> getAllVentas() {
        ArrayList<Document> documentosVentas = new ArrayList<>();
        MongoCursor<Document> cursor = ventas.find().cursor();
        while (cursor.hasNext()) {
            documentosVentas.add(cursor.next());
        }
        return documentosVentas;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Usuarios".
     *
     * @return Una lista de documentos de tipo "Document" con todos los usuarios
     *         almacenados en la base de datos.
     */
    public List<Document> getAllUsuarios() {
        ArrayList<Document> documentosUsuarios = new ArrayList<>();
        MongoCursor<Document> cursor = usuarios.find().cursor();
        while (cursor.hasNext()) {
            documentosUsuarios.add(cursor.next());
        }
        return documentosUsuarios;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Clientes" que coinciden con el nombre especificado.
     *
     * @param nombre El nombre a buscar en los documentos de la colección
     *               "Clientes".
     * @return Una lista de documentos de tipo "Document" con los clientes que
     *         coinciden con el nombre especificado.
     */
    public List<Document> getClientesByName(String nombre) {
        ArrayList<Document> documentosClientes = new ArrayList<>();
        Document query = new Document("nombre", nombre);
        MongoCursor<Document> cursor = clientes.find(query).cursor();
        while (cursor.hasNext()) {
            documentosClientes.add(cursor.next());
        }
        return documentosClientes;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Empleados" que coinciden con el nombre especificado.
     *
     * @param nombre El nombre a buscar en los documentos de la colección
     *               "Empleados".
     * @return Una lista de documentos de tipo "Document" con los clientes que
     *         coinciden con el nombre especificado.
     */
    public List<Document> getEmpleadosByName(String nombre) {
        ArrayList<Document> documentosEmpleados = new ArrayList<>();
        Document query = new Document("nombre", nombre);
        MongoCursor<Document> cursor = empleados.find(query).cursor();
        while (cursor.hasNext()) {
            documentosEmpleados.add(cursor.next());
        }
        return documentosEmpleados;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Productos" que coinciden con el nombre especificado.
     *
     * @param nombre El nombre a buscar en los documentos de la colección
     *               "Productos".
     * @return Una lista de documentos de tipo "Document" con los clientes que
     *         coinciden con el nombre especificado.
     */
    public List<Document> getProductosByName(String nombre) {
        ArrayList<Document> documentosProductos = new ArrayList<>();
        Document query = new Document("nombre", nombre);
        MongoCursor<Document> cursor = proveedores.find(query).cursor();
        while (cursor.hasNext()) {
            documentosProductos.add(cursor.next());
        }
        return documentosProductos;
    }

    /**
     * Método que devuelve una lista con todos los documentos de la colección
     * "Proveedores" que coinciden con el nombre especificado.
     *
     * @param nombre El nombre a buscar en los documentos de la colección
     *               "Proveedoes".
     * @return Una lista de documentos de tipo "Document" con los clientes que
     *         coinciden con el nombre especificado.
     */
    public List<Document> getProveedoresByName(String nombre) {
        ArrayList<Document> documentosProveedores = new ArrayList<>();
        Document query = new Document("nombre", nombre);
        MongoCursor<Document> cursor = proveedores.find(query).cursor();
        while (cursor.hasNext()) {
            documentosProveedores.add(cursor.next());
        }
        return documentosProveedores;
    }

    public Empleado getEmpleadoById(String id) {
        Document doc = empleados.find(new Document("_id", new ObjectId(id))).cursor().next();
        return EmpleadoConverter.convert(doc);
    }

    public void deleteCliente(Cliente cliente) {
        clientes.deleteOne(new BasicDBObject("_id", cliente.getId()));
    }

    public void deleteCompra(Compra compra) {
        compras.deleteOne(new BasicDBObject("_id", compra.getId()));
    }

    public void deleteEmpleado(Empleado empleado) {
        empleados.deleteOne(new BasicDBObject("_id", empleado.getId()));
    }

    public void deleteProducto(Producto producto) {
        productos.deleteOne(new BasicDBObject("_id", producto.getId()));
    }

    public void deleteProveedor(Proveedor proveedor) {
        proveedores.deleteOne(new BasicDBObject("_id", proveedor.getId()));
    }

    public void deleteVenta(Venta venta) {
        ventas.deleteOne(new BasicDBObject("_id", venta.getId()));
    }

    public Map<String, Integer> getVentasUltimoMes() {
        Map<String, Integer> lista = new HashMap<>();
        MongoCursor<Document> cursor = ventas.find().sort(new Document("venta.fecha", -1)).cursor();
        Venta leido;
        long fecha;
        int total, mes = -1, dia;
        boolean mismoMes = true;
        Bson query;

        while (cursor.hasNext() && mismoMes) {

            leido = VentaConverter.convert(cursor.next());
            fecha = leido.getFecha().toEpochDay();
            dia = leido.getFecha().getDayOfMonth();

            if (mes == -1)
                mes = leido.getFecha().getMonthValue();

            query = new BasicDBObject("venta.fecha", fecha);
            total = (int) ventas.countDocuments(query);

            lista.put("" + dia, total);

            if (leido.getFecha().getMonthValue() != mes) {
                mismoMes = false;
            }

        }

        return lista;
    }

    public Map<String, Integer> getComprasUltimoMes() {
        Map<String, Integer> lista = new HashMap<>();
        MongoCursor<Document> cursor = compras.find().sort(descending("compras.fecha")).cursor();
        Compra leido;
        long fecha;
        int total, mes = -1, dia;
        boolean mismoMes = true;
        Bson query;

        while (cursor.hasNext() && mismoMes) {

            leido = CompraConverter.convert(cursor.next());
            fecha = leido.getFecha().toEpochDay();
            dia = leido.getFecha().getDayOfMonth();

            if (mes == -1)
                mes = leido.getFecha().getMonthValue();

            query = new BasicDBObject("compra.fecha", fecha);
            total = (int) compras.countDocuments(query);

            lista.put("" + dia, total);

            if (leido.getFecha().getMonthValue() != mes) {
                mismoMes = false;
            }

        }

        return lista;
    }

    public Map<String, Integer> getVentasUltimaSemana() {
        Map<String, Integer> lista = new HashMap<>();
        MongoCursor<Document> cursor = ventas.find().sort(descending("venta.fecha")).cursor();
        Venta leido;
        long fecha;
        int total, semana = -1, dia;
        boolean mismaSemana = true;
        Bson query;

        while (cursor.hasNext() && mismaSemana) {

            leido = VentaConverter.convert(cursor.next());
            fecha = leido.getFecha().toEpochDay();
            dia = leido.getFecha().getDayOfWeek().getValue();

            if (semana == -1)
                semana = leido.getFecha().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

            query = new BasicDBObject("venta.fecha", fecha);
            total = (int) ventas.countDocuments(query);

            lista.put("" + dia, total);

            if (leido.getFecha().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()) != semana) {
                mismaSemana = false;
            }
        }

        return lista;
    }

    public Map<String, Integer> getComprasUltimaSemana() {
        Map<String, Integer> lista = new HashMap<>();
        MongoCursor<Document> cursor = compras.find().sort(descending("compra.fecha")).cursor();
        Compra leido;
        long fecha;
        int total, semana = -1, dia;
        boolean mismaSemana = true;
        Bson query;

        while (cursor.hasNext() && mismaSemana) {

            leido = CompraConverter.convert(cursor.next());
            fecha = leido.getFecha().toEpochDay();
            dia = leido.getFecha().getDayOfWeek().getValue();

            if (semana == -1)
                semana = leido.getFecha().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

            query = new BasicDBObject("compra.fecha", fecha);
            total = (int) compras.countDocuments(query);

            lista.put("" + dia, total);

            if (leido.getFecha().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()) != semana) {
                mismaSemana = false;
            }
        }

        return lista;
    }

    public Map<String, Integer> getClientesUltimoMes() {
        Map<String, Integer> lista = new HashMap<>();
        MongoCursor<Document> cursor = ventas.find().sort(descending("venta.fecha")).cursor();
        Venta leido;
        Cliente cliente;
        long fecha;
        int total = 0, mes = -1;
        boolean mismoMes = true;
        Document query;

        while (cursor.hasNext() && mismoMes) {

            leido = VentaConverter.convert(cursor.next());
            cliente = leido.getCliente();
            fecha = leido.getFecha().toEpochDay();

            if (mes == -1)
                mes = leido.getFecha().getMonthValue();

            query = new Document();
            query.append("venta.cliente.nombre", cliente.getNombre());
            query.append("venta.cliente.apellido", cliente.getApellido());
            query.append("venta.fecha", fecha);

            total += (int) ventas.countDocuments(query);

            lista.put(cliente.getNombreCompleto(), total);

            if (leido.getFecha().getMonthValue() != mes)
                mismoMes = false;

        }

        return lista;
    }


    public Map<String, Integer> getProveedoresUltimoMes() {
        Map<String, Integer> lista = new HashMap<>();
        MongoCursor<Document> cursor = compras.find().sort(descending("compra.fecha")).cursor();
        Compra leido;
        Proveedor proveedor;
        long fecha;
        int total = 0, mes = -1;
        boolean mismoMes = true;
        Document query;

        while (cursor.hasNext() && mismoMes) {

            leido = CompraConverter.convert(cursor.next());
            proveedor = leido.getProveedor();
            fecha = leido.getFecha().toEpochDay();

            if (mes == -1)
                mes = leido.getFecha().getMonthValue();

            query = new Document();
            query.append("compra.proveedor.nombre", proveedor.getNombre());
            query.append("compra.proveedor.apellido", proveedor.getApellido());
            query.append("compra.fecha", fecha);

            total += (int) compras.countDocuments(query);

            lista.put(proveedor.getNombreCompleto(), total);

            if (leido.getFecha().getMonthValue() != mes)
                mismoMes = false;

        }

        return lista;
    }

    public Map<String, Integer> getEmpleadosUltimoMes() {
        Map<String, Integer> lista = new HashMap<>();
        MongoCursor<Document> cursor = ventas.find().sort(descending("ventas.fecha")).cursor();
        Venta leido;
        Empleado empleado;
        long fecha;
        int total = 0, mes = -1;
        boolean mismoMes = true;
        Document query;

        while (cursor.hasNext() && mismoMes) {

            leido = VentaConverter.convert(cursor.next());
            empleado = leido.getEmpleado();
            fecha = leido.getFecha().toEpochDay();

            if (mes == -1)
                mes = leido.getFecha().getMonthValue();

            query = new Document();
            query.append("venta.empleado.nombre", empleado.getNombre());
            query.append("venta.empleado.apellido", empleado.getApellido());
            query.append("venta.fecha", fecha);

            total += (int) ventas.countDocuments(query);

            lista.put(empleado.getNombreCompleto(), total);

            if (leido.getFecha().getMonthValue() != mes)
                mismoMes = false;

        }

        return lista;
    }

    public Map<String, Map<String, Integer>> getDesempeñoEmpleados() {
        Map<String, Map<String, Integer>> lista = new HashMap<>();
        Map<String, Integer> datosMes;

        MongoCursor<Document> cursorEmpleados = ventas.find().sort(descending("ventas.fecha")).cursor();
        MongoCursor<Document> cursorDatos;
        Venta leidoEmpleado, leidoDatos;
        Empleado empleado;
        long fecha;
        int total, mesEmpleados = -1, mesDatos = -1, diaDatos;
        boolean mismoMesEmpleados = true, mismoMesDatos = true;
        Document query;

        while (cursorEmpleados.hasNext() && mismoMesEmpleados) {

            leidoEmpleado = VentaConverter.convert(cursorEmpleados.next());
            empleado = leidoEmpleado.getEmpleado();

            if (mesEmpleados == -1)
                mesEmpleados = leidoEmpleado.getFecha().getMonthValue();

            query = new Document("venta.empleado.nombre", empleado.getNombre());
            query.append("venta.empleado.apellido", empleado.getApellido());

            cursorDatos = ventas.find().sort(descending("ventas.fecha")).cursor();

            datosMes = new HashMap<>();

            while (cursorDatos.hasNext() && mismoMesDatos) {

                leidoDatos = VentaConverter.convert(cursorDatos.next());
                fecha = leidoDatos.getFecha().toEpochDay();
                diaDatos = leidoDatos.getFecha().getDayOfMonth();

                if (mesDatos == -1)
                    mesDatos = leidoDatos.getFecha().getMonthValue();

                query.append("venta.fecha", fecha);

                total = (int) ventas.countDocuments(query);

                datosMes.put("" + diaDatos, total);

                if (leidoDatos.getFecha().getMonthValue() != mesDatos)
                    mismoMesDatos = false;                
                
            }
            
            lista.put(empleado.getNombreCompleto(), datosMes);

            if (leidoEmpleado.getFecha().getMonthValue() != mesEmpleados)
                mismoMesEmpleados = false;

        }

        return lista;
    }

    public Double getTotalVentas() {
        MongoCursor<Document> cursor = ventas.find().cursor();
        Double total = 0.0;
        Venta venta;

        while (cursor.hasNext()) {
            venta = VentaConverter.convert(cursor.next());
            total += venta.getTotal();

        }

        return total;

    }

    public Double getTotalCompras() {
        MongoCursor<Document> cursor = compras.find().cursor();
        Double total = 0.0;
        Compra compra;

        while (cursor.hasNext()) {
            compra = CompraConverter.convert(cursor.next());
            total += compra.getTotal();

        }

        return total;

    }
}
