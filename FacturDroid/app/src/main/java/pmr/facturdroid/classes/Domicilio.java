package pmr.facturdroid.classes;

public class Domicilio {

    // Model
    private String provincia;
    private String municipio;

    // Constructor
    public Domicilio() {
        // Constructor Vacio
    }

    public Domicilio(String provincia, String municipio) {
        this.provincia = provincia;
        this.municipio = municipio;
    }

    // Getters y Setters
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getProvincia(), getMunicipio());
    }

}
