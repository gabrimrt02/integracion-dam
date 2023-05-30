package pmr.facturdroid.login;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.bson.Document;

import java.util.List;

import pmr.facturdroid.MainActivity;
import pmr.facturdroid.R;
import pmr.facturdroid.classes.Usuario;
import pmr.facturdroid.converters.UsuarioConverter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    /*
     * Atributos
     */
    // Listas de datos
    private List<Document> docsUsuarios;
    private List<Usuario> usuarios;

    // Variables booleanas
    private Boolean contenido = false;

    // Variables de texto con los datos introducidos
    private String username, password;

    // Variables alfanuméricas
    String inicioText;
    String noInicioText;

    /*
     * Elementos de la interfaz
     */
    private EditText loginUsernameET;
    private EditText loginPasswdET;
    private Button loginAccederBTN;



    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // docsUsuarios = MainActivity.dbManager.getAllUsuarios();

        inicioText = getString(R.string.toast_inicio);
        noInicioText = getString(R.string.toast_no_inicio);

        for (Document d : docsUsuarios) {
            usuarios.add(UsuarioConverter.convert(d));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recuperación de los elementos de la interfaz
        loginUsernameET = (EditText) view.findViewById(R.id.loginUsername);
        loginPasswdET = (EditText) view.findViewById(R.id.loginPassword);
        loginAccederBTN = (Button) view.findViewById(R.id.loginAcceder);

        // Asignación de la función al pulsar el botón
        loginAccederBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = loginUsernameET.getText().toString();
                password = loginPasswdET.getText().toString();

                Usuario user = new Usuario(username, password, "");

                int index = 0;
                while (index < usuarios.size() && !contenido) {
                    if (usuarios.get(index).equals(user))
                        contenido = true;

                    index++;
                }

                if (contenido) {
                    Toast.makeText(getContext(), inicioText, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), noInicioText, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}