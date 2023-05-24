package pmr.facturapp.classes;

import org.bson.types.ObjectId;

public class Empleado extends Persona {

    public Empleado(){
        // Constructor Vacio
    }

    public Empleado(String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(nombre, apellido, domicilio, nTelefono, mail);
    }

    public Empleado(ObjectId id, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(id, nombre, apellido, domicilio, nTelefono, mail);
    }

}
