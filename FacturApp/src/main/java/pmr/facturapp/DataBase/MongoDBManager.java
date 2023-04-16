package pmr.facturapp.DataBase;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.statics.TipoCliente;
import pmr.facturapp.codecs.DomicilioCodec;
import pmr.facturapp.codecs.TipoClienteCodec;

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
        MongoCollection<Document> clientes;
        Document documentoPrueba;

        // TODO Crear XXXConverter para poder transformar los objetos en documentos y viceversa

        clienteSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONN_STRING))
                .build();

        cliente = MongoClients.create(clienteSettings);
        database = cliente.getDatabase("FacturApp");

        System.out.println(" ===== Conexión Realizada con éxito");

        clientes = database.getCollection("Clientes");

        Domicilio domicilio = new Domicilio("SC de Tenerife", "El Rosario");

        Cliente clienteInsert = new Cliente(TipoCliente.PARTICULAR(), "Rodolfito", "Ramirez", domicilio, "922 34 85 08",
                "rod.ram@dev.facturapp.es");

        Document dTipoCliente = new Document("tipo", clienteInsert.getTipoCliente().getTipo());

        Document dDomicilio = new Document("provincia", clienteInsert.getDomicilio().getProvincia())
                .append("municipio", clienteInsert.getDomicilio().getMunicipio());

        documentoPrueba = new Document("tipoCliente", dTipoCliente)
                .append("nombre", clienteInsert.getNombre())
                .append("apellido", clienteInsert.getApellido())
                .append("domicilio", dDomicilio)
                .append("nTelefono", clienteInsert.getNTelefono())
                .append("mail", clienteInsert.getMail());

        System.out.println(" ===== Se va a realizar la inserción...");

        clientes.insertOne(documentoPrueba);

        System.out.println(" ===== La inserción se a realizado correctamente");

    }

}
