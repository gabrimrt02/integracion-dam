package pmr.facturapp.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Domicilio {

    // Model
    private StringProperty provinciaSP = new SimpleStringProperty();
    private StringProperty municipioSP = new SimpleStringProperty();

    // Constructor
    public Domicilio() {
        // Constructor Vacio
    }

    public Domicilio(String provincia, String municipio) {
        this.provinciaSP.set(provincia);
        this.municipioSP.set(municipio);
    }

    public StringProperty provinciaProperty() {
        return provinciaSP;
    }

    public StringProperty municipioProperty() {
        return municipioSP;
    }

    // Getters y Setters
    public String getProvincia() {
        return provinciaSP.get();
    }

    public void setProvincia(String provincia) {
        this.provinciaSP.set(provincia);
    }

    public String getMunicipio() {
        return municipioSP.get();
    }

    public void setMunicipio(String municipio) {
        this.municipioSP.set(municipio);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getProvincia(), getMunicipio());
    }

}
