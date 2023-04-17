package pmr.facturapp.converters;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.statics.TipoCliente;

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

        TipoCliente tipoCliente = TipoClienteConverter.convert(documento.get("tipoCliente", Document.class));

        String nombre = documento.getString("nombre");
        String apellido = documento.getString("apellido");

        Domicilio domicilio = DomicilioConverter.convert(documento.get("domicilio", Document.class));
        
        String telefono = documento.getString("telefono");
        String mail = documento.getString("mail");
        return new Cliente(id, tipoCliente, nombre, apellido, domicilio, telefono, mail);
    }
    
}
