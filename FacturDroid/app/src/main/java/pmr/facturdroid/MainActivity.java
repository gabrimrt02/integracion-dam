package pmr.facturdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;

import pmr.facturdroid.database.MongoDBClient;
import pmr.facturdroid.database.MongoDBManager;
import pmr.facturdroid.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    /*
     * Fragments del programa
     */
    private Fragment loginFragment;

    /*
     * Manejador de la BBDD
     */
    public static MongoDBManager dbManager;

    /*
     * Nombre del Usuario que realiza el login
     */
    public static String NOMBRE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dbManager = MongoDBManager.connect();

        MongoDBClient cliente = new MongoDBClient();

        loginFragment = new LoginFragment();
    }

}