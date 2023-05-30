package pmr.facturdroid.classes.statics;

import java.util.ArrayList;
import java.util.List;

public class Unidad {

    /*
     * Properties
     */
    private String nombre;
    private static List<Unidad> unidades = new ArrayList<>(List.of(KILOS(), CAJAS(), UNIDADES_SUELTAS()));

    private Unidad(String nombre) {
        this.nombre = nombre;
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

    public String getUnidad() {
        return nombre;
    }

    public List<Unidad> getAllUnidades() {
        return unidades;
    }

    @Override
    public String toString() {
        return getUnidad();
    }

}
