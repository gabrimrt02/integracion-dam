package pmr.facturdroid.classes.statics;

import java.util.ArrayList;
import java.util.List;

public class TipoCliente {

    /*
     * Properties
     */
    private String nombre;
    private List<TipoCliente> tipos = new ArrayList<>(List.of(PARTICULAR(), SOCIEDAD_CIVIL(), SOCIEDAD_LIMITADA(), COOPERATIVA()));

    private TipoCliente(String nombre) {
        this.nombre = nombre;
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

    public String getTipo() {
        return nombre;
    }

    public List<TipoCliente> getAllTipos() {
        return tipos;
    }

    public String toString() {
        return getTipo();
    }

}
