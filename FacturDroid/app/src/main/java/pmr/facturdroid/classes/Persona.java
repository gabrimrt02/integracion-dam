package pmr.facturdroid.classes;

import org.bson.types.ObjectId;
import pmr.facturdroid.classes.Domicilio;

public class Persona {

    // Properties
    private ObjectId id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private String telefono;
    private String mail;

    // Constructores
    public Persona() {
        // Constructor vacio
    }

    public Persona(ObjectId id, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.telefono = nTelefono;
        this.mail = mail;
    }

    public Persona(String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.telefono = nTelefono;
        this.mail = mail;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getNTelefono() {
        return telefono;
    }

    public void setNTelefono(String nTelefono) {
        this.telefono = nTelefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombreCompleto() {
        return getApellido() + ", " + getNombre();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getApellido(), getNombre());
    }

}
