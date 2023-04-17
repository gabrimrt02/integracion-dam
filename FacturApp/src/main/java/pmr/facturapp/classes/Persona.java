package pmr.facturapp.classes;

import org.bson.types.ObjectId;

public class Persona {

    // Atributos
    private ObjectId id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private String nTelefono;
    private String mail;

    // Constructores
    public Persona(ObjectId id, String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.nTelefono = nTelefono;
        this.mail = mail;
    }

    public Persona(String nombre, String apellido, Domicilio domicilio, String nTelefono, String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.nTelefono = nTelefono;
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
        return nTelefono;
    }

    public void setNTelefono(String nTelefono) {
        this.nTelefono = nTelefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return String.format("%s, %s - %s - %s / %s", getApellido(), getNombre(), getDomicilio().toString(),
                getNTelefono(), getMail());
    }

}
