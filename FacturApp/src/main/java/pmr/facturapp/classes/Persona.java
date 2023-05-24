package pmr.facturapp.classes;

import org.bson.types.ObjectId;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {

    // Properties
    private ObjectProperty<ObjectId> idOP = new SimpleObjectProperty<>();
    private StringProperty nombreSP = new SimpleStringProperty();
    private StringProperty apellidoSP = new SimpleStringProperty();
    private Domicilio domicilio = new Domicilio();
    private StringProperty telefonoSP = new SimpleStringProperty();
    private StringProperty mailSP = new SimpleStringProperty();

    // Constructores
    public Persona() {
        // Constructor vacio
    }

    public Persona(ObjectId id, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        this.idOP.set(id);
        this.nombreSP.set(nombre);
        this.apellidoSP.set(apellido);
        this.domicilio = domicilio;
        this.telefonoSP.set(nTelefono);
        this.mailSP.set(mail);
    }

    public Persona(String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        this.nombreSP.set(nombre);
        this.apellidoSP.set(apellido);
        this.domicilio = domicilio;
        this.telefonoSP.set(nTelefono);
        this.mailSP.set(mail);
    }

    public ObjectProperty<ObjectId> idProperty() {
        return idOP;
    }

    public StringProperty nombreProperty() {
        return nombreSP;
    }

    public StringProperty apellidosProperty() {
        return apellidoSP;
    }

    public StringProperty telefonoProperty() {
        return telefonoSP;
    }

    public StringProperty mailProperty() {
        return mailSP;
    }

    // Getters y Setters
    public ObjectId getId() {
        return idOP.get();
    }

    public String getNombre() {
        return nombreSP.get();
    }

    public void setNombre(String nombre) {
        this.nombreSP.set(nombre);
    }

    public String getApellido() {
        return apellidoSP.get();
    }

    public void setApellido(String apellido) {
        this.apellidoSP.set(apellido);
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getNTelefono() {
        return telefonoSP.get();
    }

    public void setNTelefono(String nTelefono) {
        this.telefonoSP.set(nTelefono);
    }

    public String getMail() {
        return mailSP.get();
    }

    public void setMail(String mail) {
        this.mailSP.set(mail);
    }

    @Override
    public String toString() {
        return String.format("%s, %s - %s - %s / %s", getApellido(), getNombre(), getDomicilio().toString(),
                getNTelefono(), getMail());
    }

}
