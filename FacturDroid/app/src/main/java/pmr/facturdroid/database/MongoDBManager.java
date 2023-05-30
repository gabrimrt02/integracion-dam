package pmr.facturdroid.database;

import android.content.res.Resources;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pmr.facturdroid.R;
import pmr.facturdroid.classes.Venta;
import pmr.facturdroid.converters.VentaConverter;

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
        // Apertura del fichero de properties y retorno de la clave de conexión
        // Conexión con la base de datos de MongoDB
        conn = new ConnectionString(Resources.getSystem().getString(R.string.connString));
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
            return null;
        }

    }

    public void disconnect() {
        client.close();
    }

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


}
