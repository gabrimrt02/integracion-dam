package pmr.facturapp.classes;

import pmr.facturapp.classes.statics.TipoCliente;

public class Cliente extends Persona {
    
    // Atributos
    private TipoCliente tipoCliente;

    // Constructor
    private Cliente(String id, TipoCliente tipoCliente, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
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

}
