package pmr.facturapp.classes.statics;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class TipoCliente {

    /*
     * Properties
     */
    private StringProperty nombreSP = new SimpleStringProperty();
    private static ListProperty<TipoCliente> tiposLP = new SimpleListProperty<>(
            FXCollections.observableArrayList(PARTICULAR(), SOCIEDAD_CIVIL(), SOCIEDAD_LIMITADA(), COOPERATIVA()));

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

    public static ListProperty<TipoCliente> tiposProperty() {
        return tiposLP;
    }

    public String getTipo() {
        return nombreSP.get();
    }

    public List<TipoCliente> getAllTipos() {
        return tiposLP.get();
    }

    public String toString() {
        return getTipo();
    }

}
