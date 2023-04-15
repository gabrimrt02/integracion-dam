package pmr.facturapp.classes.statics;

public class TipoCliente {

    // Atributos
    @SuppressWarnings("unused")
    private String nombre;

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
    
}
