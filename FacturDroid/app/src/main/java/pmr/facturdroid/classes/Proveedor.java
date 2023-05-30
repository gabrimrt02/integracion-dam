package pmr.facturdroid.classes;

import org.bson.types.ObjectId;

public class Proveedor extends Persona {

    public Proveedor() {
        // Constructuro Vacio
    }

    public Proveedor(ObjectId id, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(id, nombre, apellido, domicilio, nTelefono, mail);
    }

    public Proveedor(String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(nombre, apellido, domicilio, nTelefono, mail);
    }

}
