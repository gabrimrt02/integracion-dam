package pmr.facturapp.converters;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Empleado;

public class EmpleadoConverter {
    
    public static Document convert(Empleado empleado) {
        return new Document("nombre", empleado.getNombre())
                .append("apellido", empleado.getApellido())
                .append("domicilio", DomicilioConverter.convert(empleado.getDomicilio()))
                .append("telefono", empleado.getNTelefono())
                .append("mail", empleado.getMail());
    }

    public static Empleado convert(Document documento) {
        ObjectId id = documento.getObjectId("_id");
        String nombre = documento.getString("nombre");
        String apellido = documento.getString("apellido");
        Domicilio domicilio = DomicilioConverter.convert(documento.get("domicilio", Document.class));
        String telefono = documento.getString("telefono");
        String mail = documento.getString("mail");
        return new Empleado(id, nombre, apellido, domicilio, telefono, mail);
    }

}
