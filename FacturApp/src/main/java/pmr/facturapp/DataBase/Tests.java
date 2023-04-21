package pmr.facturapp.DataBase;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.classes.statics.TipoCliente;
import pmr.facturapp.classes.statics.Unidad;
import pmr.facturapp.converters.ClienteConverter;
import pmr.facturapp.converters.DomicilioConverter;
import pmr.facturapp.converters.ProductoConverter;
import pmr.facturapp.converters.ProveedorConverter;
import pmr.facturapp.converters.TipoClienteConverter;

public class Tests {

    // private final String CONN_STRING =
    // "mongodb+srv://Developer:3gughi9KTrPYd3uW@facturapp.exx6an8.mongodb.net/test";

    // MongoClientSettings clienteSettings;
    // MongoClient cliente;

    public static void main(String[] args) {
        final String CONN_STRING = "mongodb+srv://Developer:3gughi9KTrPYd3uW@facturapp.exx6an8.mongodb.net/test";

        MongoClientSettings clienteSettings;
        MongoClient cliente;
        MongoDatabase database;
        MongoCollection<Document> clientes, productos, proveedores;
        Document documentoPrueba;

        clienteSettings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(CONN_STRING))
                .build();

        cliente = MongoClients.create(clienteSettings);
        database = cliente.getDatabase("FacturApp");

        System.out.println(" ===== Conexión Realizada con éxito");

        clientes = database.getCollection("Clientes");

        productos = database.getCollection("Productos");

        proveedores = database.getCollection("Proveedores");

        Domicilio domicilio = new Domicilio("SC de Tenerife", "El Rosario");

        Cliente clienteInsert = new Cliente(TipoCliente.PARTICULAR(), "Rodolfito", "Ramirez", domicilio, "922 34 85 08",
                "rod.ram@dev.facturapp.es");

        Producto producto = new Producto("Plátano de Canarias", "Plátano de Canarias", 1.10, 100, Unidad.CAJAS());

        Proveedor proveedor = new Proveedor("Proveedor_1", "S.L", domicilio, "999 888 777",
                "proveedor@dev.facturapp.es");

        // documentoPrueba = new Document("tipoCliente",
        // TipoClienteConverter.convert(clienteInsert.getTipoCliente()))
        // .append("nombre", clienteInsert.getNombre())
        // .append("apellido", clienteInsert.getApellido())
        // .append("domicilio",
        // DomicilioConverter.convert(clienteInsert.getDomicilio()))
        // .append("nTelefono", clienteInsert.getNTelefono())
        // .append("mail", clienteInsert.getMail());

        // documentoPrueba = new Document("producto",
        // ProductoConverter.convert(producto));

        // documentoPrueba = new Document("cliente",
        // ClienteConverter.convert(clienteInsert));

        // System.out.println("Proveedor.Domicilio = " +
        // proveedor.getDomicilio().toString());

        documentoPrueba = new Document("proveedor", ProveedorConverter.convert(proveedor));
        // .append("domicilio", DomicilioConverter.convert(proveedor.getDomicilio()));

        System.out.println(" ===== Se va a realizar la inserción...");

        // clientes.insertOne(documentoPrueba);
        // productos.insertOne(documentoPrueba);
        // proveedores.insertOne(documentoPrueba);

        System.out.println(" ===== La inserción se a realizado correctamente");

        Document documento = documentoPrueba.get("proveedor.domicilio", Document.class);

        // System.out.println("Domicilio = " + documento);

        System.out.println(" ===== Recuperando datos de los clientes...");

        // Bson filtro = Filters.all("cliente.nombre", "Rodolfito");
        // Bson filtro = Filters.all("_id", "6442cc79240cec7c13ef02df");

        ObjectId idBuscado = new ObjectId("6442d13d8a9a3a77ead07c64");

        // proveedores.find(new Document("_id", idBuscado)).forEach(doc -> {
        //     System.out.println(doc);
        //     System.out.println(doc.get("proveedor.domicilio"));
        //     Proveedor p = ProveedorConverter.convert(doc);
        //     System.out.println(p.getDomicilio().toString());
        // });

        Document encontrado = proveedores.find(new Document("_id",
        idBuscado)).first();

        Proveedor pEncontrado = ProveedorConverter.convert(encontrado);

        System.out.println(pEncontrado.getNombre());

    }

}
