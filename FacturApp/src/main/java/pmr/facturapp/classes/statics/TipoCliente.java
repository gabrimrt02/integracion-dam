package pmr.facturapp.classes.statics;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TipoCliente {

    // Properties
    private StringProperty nombreSP = new SimpleStringProperty();

    private TipoCliente(String nombre) {
        this.nombreSP.set(nombre);
    }

    public static TipoCliente PARTICULAR() {
        return new TipoCliente("PARTICULAR");
    }

    public static TipoCliente SOCIEDAD_LIMITADA() {
        return new TipoCliente("SOCIEDAD LIMITADA");
    }

    public static TipoCliente SOCIEDAD_CIVIL() {
        return new TipoCliente("SOCIEDAD CIVIL");
    }

    public static TipoCliente COOPERATIVA() {
        return new TipoCliente("COOPERATIVA");
    }

    public StringProperty nombreProperty() {
        return nombreSP;
    }

    public String getTipo() {
        return nombreSP.get();
    }
    
}
