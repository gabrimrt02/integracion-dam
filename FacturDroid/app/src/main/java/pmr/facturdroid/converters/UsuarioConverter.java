package pmr.facturdroid.converters;

import org.bson.Document;

import pmr.facturdroid.classes.Usuario;

public class UsuarioConverter {

    public static Usuario convert(Document document) {
        // Recuperamos el objeto Usuario dentro de la BBDD
        Document docUsuario = document.get("usuario", Document.class);

        // Recuperamos los atributos del Usuario de la BBDD
        String username = docUsuario.getString("username");
        String password = docUsuario.getString("password");
        String nombre = docUsuario.getString("nombre");

        return new Usuario(username, password, nombre);
    }

}
