package pmr.facturapp.classes.statics;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Unidad {

    /*
     * Properties
     */
    private StringProperty nombreSP = new SimpleStringProperty();
    private static ListProperty<Unidad> unidadesLP = new SimpleListProperty<>(
            FXCollections.observableArrayList(KILOS(), CAJAS(), UNIDADES_SUELTAS()));

    private Unidad(String nombre) {
        this.nombreSP.set(nombre);
    }

    public static Unidad KILOS() {
        return new Unidad("KILO(S)");
    }

    public static Unidad CAJAS() {
        return new Unidad("CAJA(S)");
    }

    public static Unidad UNIDADES_SUELTAS() {
        return new Unidad("UNIDAD(ES) SUELTA(S)");
    }

    public StringProperty nombreProperty() {
        return nombreSP;
    }

    public static ListProperty<Unidad> unidadesProperty() {
        return unidadesLP;
    }

    public String getUnidad() {
        return nombreSP.get();
    }

    public List<Unidad> getAllUnidades() {
        return unidadesLP.get();
    }

    @Override
    public String toString() {
        return getUnidad();
    }

}
