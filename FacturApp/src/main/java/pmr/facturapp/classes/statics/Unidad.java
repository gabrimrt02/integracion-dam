package pmr.facturapp.classes.statics;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Unidad {

    // Properties
    private StringProperty nombreSP = new SimpleStringProperty();

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

    public String getUnidad() {
        return nombreSP.get();
    }

}
