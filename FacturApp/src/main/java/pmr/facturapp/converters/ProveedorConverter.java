package pmr.facturapp.converters;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Proveedor;

public class ProveedorConverter {
    
    public static Document convert(Proveedor proveedor) {
        return new Document("nombre", proveedor.getNombre())
                .append("apellido", proveedor.getApellido())
                .append("telefono", proveedor.getNTelefono())
                .append("mail", proveedor.getMail());
    }

    public static Proveedor convert(Document documento) {
        // Atributos del proveedor
        ObjectId id = documento.getObjectId("_id");
        String nombre = documento.getString("nombre");
        String apellido = documento.getString("apellido");
        String telefono = documento.getString("telefono");
        String mail = documento.getString("mail");

        // Atributos del domicilio
        Document d = documento.get("domicilio", Document.class);

        Domicilio domicilio = DomicilioConverter.convert(d);

        return new Proveedor(id, nombre, apellido, domicilio, telefono, mail);
    }

}
