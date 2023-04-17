package pmr.facturapp.converters;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Proveedor;

public class ProveedorConverter {
    
    public static Document converter(Proveedor proveedor) {
        return new Document("nombre", proveedor.getNombre())
                .append("apellido", proveedor.getApellido())
                .append("domicilio", DomicilioConverter.convert(proveedor.getDomicilio()))
                .append("telefono", proveedor.getNTelefono())
                .append("mail", proveedor.getMail());
    }

    public static Proveedor converter(Document documento) {
        ObjectId id = documento.getObjectId("_id");
        String nombre = documento.getString("nombre");
        String apellido = documento.getString("apellido");
        Domicilio domicilio = DomicilioConverter.convert(documento.get("domicilio", Document.class));
        String telefono = documento.getString("telefono");
        String mail = documento.getString("mail");
        return new Proveedor(id, nombre, apellido, domicilio, telefono, mail);
    }

}
