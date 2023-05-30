package pmr.facturdroid.classes;

public class Usuario {

    /*
     * Properties
     */
    private String username;
    private String password;
    private String nombre;

    /*
     * Constructor
     */
    public Usuario() {
        // Constructor Vacios
    }

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    /*
     * Getters de los valores de las properties
     */
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", getUsername(), getPassword(), getNombre());
    }

    @Override
    public boolean equals(Object obj) {
        Boolean res = false;

        if (obj instanceof Usuario) {
            Usuario other = new Usuario(((Usuario) obj).getUsername(), ((Usuario) obj).getPassword(),
                    ((Usuario) obj).getNombre());
            if (other.getUsername().equals(this.getUsername())) {
                if (other.getPassword().equals(this.getPassword())) {
                    res = true;

                }

            }

        }

        return res;
    }

}
