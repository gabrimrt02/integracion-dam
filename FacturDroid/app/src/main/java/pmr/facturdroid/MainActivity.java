package pmr.facturdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import pmr.facturdroid.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    /*
     * Fragments del programa
     */
    private Fragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginFragment = new LoginFragment();
    }
}