package pmr.facturdroid.classes;

import org.bson.types.ObjectId;

import pmr.facturdroid.classes.Domicilio;
import pmr.facturdroid.classes.Persona;
import pmr.facturdroid.classes.statics.TipoCliente;

public class Cliente extends Persona {

    // Properties
    private TipoCliente tipoCliente;

    // Constructor
    public Cliente() {
        // Constructor vacio
    }

    public Cliente(ObjectId id, TipoCliente tipoCliente, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(id, nombre, apellido, domicilio, nTelefono, mail);
        this.tipoCliente = tipoCliente;
    }

    public Cliente(TipoCliente tipoCliente, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(nombre, apellido, domicilio, nTelefono, mail);
        this.tipoCliente = tipoCliente;
    }

    // Getters y Setters
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return String.format("%s", getNombreCompleto());
    }
}
