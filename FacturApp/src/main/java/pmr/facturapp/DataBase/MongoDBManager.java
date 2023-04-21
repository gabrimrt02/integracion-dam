package pmr.facturapp.DataBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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
    private MongoCollection<Document> clientes, empleados, productos, proveedores;

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
        properties.load(MongoDBManager.class.getResourceAsStream("properties/mongoConn.properties"));
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
    }

    /**
     * Intenta conectarse a una instancia de MongoDB utilizando la clase
     * MongoDBManager. Si la conexión es exitosa, devuelve true, de lo contrario,
     * devuelve false.
     *
     * @return true si la conexión fue exitosa, false de lo contrario.
     */
    public Boolean connectar() {
        Boolean connResult = false;
        try {
            new MongoDBManager();
            connResult = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connResult;
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
     * "Clientes" que coinciden con el nombre especificado.
     *
     * @param nombre El nombre a buscar en los documentos de la colección
     *               "Clientes".
     * @return Una lista de documentos de tipo "Document" con los clientes que
     *         coinciden con el nombre especificado.
     */
    public List<Document> getClientesByName(String nombre) {
        ArrayList<Document> documentosClientes = new ArrayList<>();
        MongoCursor<Document> cursor = clientes.find().cursor();
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
        MongoCursor<Document> cursor = empleados.find().cursor();
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
        MongoCursor<Document> cursor = proveedores.find().cursor();
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
        MongoCursor<Document> cursor = proveedores.find().cursor();
        while (cursor.hasNext()) {
            documentosProveedores.add(cursor.next());
        }
        return documentosProveedores;
    }

}
