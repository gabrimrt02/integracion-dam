package pmr.facturapp.converters;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Proveedor;

public class ProveedorConverter {
    
    public static Document convert(Proveedor proveedor) {
        return new Document("nombre", proveedor.getNombre())
                .append("apellido", proveedor.getApellido())
                .append("domicilio", DomicilioConverter.convert(proveedor.getDomicilio()))
                .append("telefono", proveedor.getNTelefono())
                .append("mail", proveedor.getMail());
    }

    public static Proveedor convert(Document documento) {
        ObjectId id = documento.getObjectId("_id");

        // Recuperamos el documento Cliente incluido dentro de la BBDD
        Document docProveedor = documento.get("proveedor", Document.class);

        String nombre = docProveedor.getString("nombre");
        String apellido = docProveedor.getString("apellido");

        // Transformamos el subDocumento a la clase correspondiente
        Domicilio domicilio = DomicilioConverter.convert(docProveedor.get("domicilio", Document.class));

        String telefono = docProveedor.getString("telefono");
        String mail = docProveedor.getString("mail");
        
        return new Proveedor(id, nombre, apellido, domicilio, telefono, mail);
    }

}
