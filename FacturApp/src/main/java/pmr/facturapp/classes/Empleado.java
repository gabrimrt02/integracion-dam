package pmr.facturapp.classes;

public class Empleado extends Persona {

    public Empleado(String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(nombre, apellido, domicilio, nTelefono, mail);
    }

    public Empleado(String id, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(id, nombre, apellido, domicilio, nTelefono, mail);
    }

}
