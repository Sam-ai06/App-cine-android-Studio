package com.example.par5_proy2p_villagomez_miaca_chichande;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Modelo.CredecialesInvalidasException;
import Modelo.Usuario;

public class MainActivity_Login extends AppCompatActivity {

    private EditText editUser;
    private EditText editPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editUser = findViewById(R.id.editText_user);
        editPassword = findViewById(R.id.editText_password);
        btnLogin = findViewById(R.id.btn_login2);
    }

    /**
     * Valida el usuario y contraseña ingresados con los datos almacenados en la lista de usuarios y redirige a la pantalla de películas.
     *
     * @param view Vista asociada al evento de clic.
     *
     * @trows CredecialesInvalidasException Si las credenciales son inválidas.
     */
    public void validarSesion(View view){
        //se obtiene los datos de los edittext
        String user = editUser.getText().toString();
        String password = editPassword.getText().toString();

        //se declara una variable para verificar si el usuario existe
        boolean usuarioExistente = false;
        if (user.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Llena los datos", Toast.LENGTH_LONG).show();
        } else {

            try {
                for (Usuario usuario: MainActivity.listUsuario){
                    //si los datos leidos concuerdan con los obtenidos la variable cambia a verdadero
                    if (user.equals(usuario.getUser()) && password.equals(usuario.getPassword())){
                        usuarioExistente = true;
                        break;
                    }
                }

                //condicion si el usuario existe se manda a la siguiente ventana caso contrario se lanza la excepcion
                if (usuarioExistente == true) {
                    Toast.makeText(this, "Ha iniciado sesion correctamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MainActivity_Movies.class);
                    startActivity(intent);
                } else {
                    throw new CredecialesInvalidasException("");
                }
            } catch (CredecialesInvalidasException e) {
                Toast.makeText(this, "El usuario o la contraseña es incorrecta", Toast.LENGTH_LONG).show();
            }

        }
    }
}