package pmr.facturapp.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

    /*
     * Properties
     */
    private StringProperty usernameSP = new SimpleStringProperty();
    private StringProperty passwordSP = new SimpleStringProperty();
    private StringProperty identificadorSP = new SimpleStringProperty();

    /*
     * Constructor
     */
    public Usuario() {
        // Constructor Vacios
    }

    public Usuario(String username, String password, String identificador) {
        this.usernameSP.set(username);
        this.passwordSP.set(password);
        this.identificadorSP.set(identificador);
    }

    /*
     * Getters de las properties
     */
    public StringProperty usernameProperty() {
        return usernameSP;
    }

    public StringProperty passwordProperty() {
        return passwordSP;
    }

    public StringProperty identificadorProperty() {
        return identificadorSP;
    }

    /*
     * Getters de los valores de las properties
     */
    public String getUsername() {
        return this.usernameSP.get();
    }

    public String getPassword() {
        return this.passwordSP.get();
    }

    public String getIdentificador() {
        return this.identificadorSP.get();
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", getUsername(), getPassword(), getIdentificador());
    }

    @Override
    public boolean equals(Object obj) {
        Boolean res = false;

        if (obj instanceof Usuario) {
            Usuario other = new Usuario(((Usuario) obj).getUsername(), ((Usuario) obj).getPassword(),
                    ((Usuario) obj).getIdentificador());
            if (other.getUsername().equals(this.getUsername())) {
                if (other.getPassword().equals(this.getPassword())) {
                    res = true;

                }

            }

        }

        return res;
    }

}
