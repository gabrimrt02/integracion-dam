package pmr.facturapp.classes;

import org.bson.types.ObjectId;

import pmr.facturapp.classes.statics.TipoCliente;

public class Cliente extends Persona {
    
    // Atributos
    private TipoCliente tipoCliente;

    // Constructor
    public Cliente(ObjectId id, TipoCliente tipoCliente, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(id, nombre, apellido, domicilio, nTelefono, mail);
        this.tipoCliente = tipoCliente;
    }
    // Constructor
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
        return String.format("%s - %s", getTipoCliente().getTipo(), super.toString());
    }
}
