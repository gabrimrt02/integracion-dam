package pmr.facturdroid.converters;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturdroid.classes.Domicilio;
import pmr.facturdroid.classes.Empleado;
import pmr.facturdroid.converters.DomicilioConverter;

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

        // Recuperamos el documento Cliente incluido dentro de la BBDD
        Document docEmpleado = documento.get("empleado", Document.class);

        String nombre = docEmpleado.getString("nombre");
        String apellido = docEmpleado.getString("apellido");

        // Transformamos el subDocumento a la clase correspondiente
        Domicilio domicilio = DomicilioConverter.convert(docEmpleado.get("domicilio", Document.class));

        String telefono = docEmpleado.getString("telefono");
        String mail = docEmpleado.getString("mail");

        return new Empleado(id, nombre, apellido, domicilio, telefono, mail);
    }

}
