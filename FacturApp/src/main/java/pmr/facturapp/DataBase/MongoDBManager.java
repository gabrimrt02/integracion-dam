package pmr.facturapp.DataBase;

import org.bson.Document;
import org.bson.conversions.Bson;

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
import pmr.facturapp.classes.statics.TipoCliente;
import pmr.facturapp.classes.statics.Unidad;
import pmr.facturapp.converters.ClienteConverter;
import pmr.facturapp.converters.DomicilioConverter;
import pmr.facturapp.converters.ProductoConverter;
import pmr.facturapp.converters.TipoClienteConverter;

public class MongoDBManager {

    // private final String CONN_STRING =
    // "mongodb+srv://Developer:3gughi9KTrPYd3uW@facturapp.exx6an8.mongodb.net/test";

    // MongoClientSettings clienteSettings;
    // MongoClient cliente;

    public static void main(String[] args) {
        final String CONN_STRING = "mongodb+srv://Developer:3gughi9KTrPYd3uW@facturapp.exx6an8.mongodb.net/test";

        MongoClientSettings clienteSettings;
        MongoClient cliente;
        MongoDatabase database;
        MongoCollection<Document> clientes, productos;
        Document documentoPrueba;

        clienteSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONN_STRING))
                .build();

        cliente = MongoClients.create(clienteSettings);
        database = cliente.getDatabase("FacturApp");

        System.out.println(" ===== Conexión Realizada con éxito");

        clientes = database.getCollection("Clientes");

        productos = database.getCollection("Productos");

        Domicilio domicilio = new Domicilio("SC de Tenerife", "El Rosario");

        Cliente clienteInsert = new Cliente(TipoCliente.PARTICULAR(), "Rodolfito", "Ramirez", domicilio, "922 34 85 08",
                "rod.ram@dev.facturapp.es");


        // Producto producto = new Producto("Plátano de Canarias", "Plátano de Canarias", 1.10, 100, Unidad.CAJAS());

        // documentoPrueba = new Document("tipoCliente", TipoClienteConverter.convert(clienteInsert.getTipoCliente()))
        //         .append("nombre", clienteInsert.getNombre())
        //         .append("apellido", clienteInsert.getApellido())
        //         .append("domicilio", DomicilioConverter.convert(clienteInsert.getDomicilio()))
        //         .append("nTelefono", clienteInsert.getNTelefono())
        //         .append("mail", clienteInsert.getMail());

        // documentoPrueba = new Document("producto", ProductoConverter.convert(producto));

        documentoPrueba = new Document("cliente", ClienteConverter.convert(clienteInsert));

        System.out.println(" ===== Se va a realizar la inserción...");

        clientes.insertOne(documentoPrueba);
        // productos.insertOne(documentoPrueba);

        System.out.println(" ===== La inserción se a realizado correctamente");

        System.out.println(" ===== Recuperando datos de los clientes...");

        Bson filtro = Filters.all("cliente.nombre", "Rodolfito");

        clientes.find(filtro).forEach(doc -> System.out.println(doc));

    }

}
