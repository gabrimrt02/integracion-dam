package pmr.facturapp.classes.statics;

public class Unidad {

    // Atributos
    @SuppressWarnings("unused")
    private String nombre;

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

}
