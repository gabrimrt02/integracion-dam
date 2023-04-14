package pmr.facturapp.classes;

public class Proveedor extends Persona {

    public Proveedor(String id, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(id, nombre, apellido, domicilio, nTelefono, mail);
    }
    
    public Proveedor(String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(nombre, apellido, domicilio, nTelefono, mail);
    }
    
}
