package pmr.facturapp.classes;

import org.bson.types.ObjectId;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pmr.facturapp.classes.statics.TipoCliente;

public class Cliente extends Persona {
    
    // Properties
    private ObjectProperty<TipoCliente> tipoClienteOP = new SimpleObjectProperty<>();

    // Constructor
    public Cliente() {
        // Constructor vacio
    }

    public Cliente(ObjectId id, TipoCliente tipoCliente, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(id, nombre, apellido, domicilio, nTelefono, mail);
        this.tipoClienteOP.set(tipoCliente);
    }
    
    public Cliente(TipoCliente tipoCliente, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        super(nombre, apellido, domicilio, nTelefono, mail);
        this.tipoClienteOP.set(tipoCliente);
    }

    public ObjectProperty<TipoCliente> tipoClienteProperty() {
        return tipoClienteOP;
    }

    // Getters y Setters    
    public TipoCliente getTipoCliente() {
        return tipoClienteOP.get();
    }
    
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoClienteOP.set(tipoCliente);
    }

    @Override
    public String toString() {
        return String.format("%s", getNombreCompleto());
    }
}
