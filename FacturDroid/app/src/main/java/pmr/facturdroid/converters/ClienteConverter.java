package pmr.facturdroid.converters;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturdroid.classes.Cliente;
import pmr.facturdroid.classes.Domicilio;
import pmr.facturdroid.classes.statics.TipoCliente;

public class ClienteConverter {

    public static Document convert(Cliente cliente) {
        return new Document("tipoCliente", TipoClienteConverter.convert(cliente.getTipoCliente()))
                .append("nombre", cliente.getNombre())
                .append("apellido", cliente.getApellido())
                .append("domicilio", DomicilioConverter.convert(cliente.getDomicilio()))
                .append("telefono", cliente.getNTelefono())
                .append("mail", cliente.getMail());
    }

    public static Cliente convert(Document documento) {
        ObjectId id = documento.getObjectId("_id");

        // Recuperamos el documento Cliente incluido dentro de la BBDD
        Document docCliente = documento.get("cliente", Document.class);

        // Transformamos el subDocumento a la clase correspondiente
        TipoCliente tipoCliente = TipoClienteConverter.convert(docCliente.get("tipoCliente", Document.class));

        String nombre = docCliente.getString("nombre");
        String apellido = docCliente.getString("apellido");

        // Transformamos el subDocumento a la clase correspondiente
        Domicilio domicilio = DomicilioConverter.convert(docCliente.get("domicilio", Document.class));

        String telefono = docCliente.getString("telefono");
        String mail = docCliente.getString("mail");

        return new Cliente(id, tipoCliente, nombre, apellido, domicilio, telefono, mail);
    }

}
