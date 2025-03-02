package com.example.par5_proy2p_villagomez_miaca_chichande;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Estreno;
import Modelo.Funcion;
import Modelo.Pelicula;
import Modelo.Usuario;
import Modelo.contenidoCine;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnPremieres;
    public static ArrayList<Usuario> listUsuario;
    public static List<contenidoCine> listContenidoCine;
    public static ArrayList<Funcion> listFunciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.btn_login);
        btnPremieres = findViewById(R.id.btn_premieres);

        listUsuario = new ArrayList<>();
        listContenidoCine = new ArrayList<>();
        listFunciones = new ArrayList<>();

        cargarUsarios();
        cargarPeliculas();
        cargarFunciones();
        cargarEstrenos();
    }

    /**
     * Redirige a la pantalla de login.
     *
     * @param view Vista asociada al evento de clic.
     */
    public void iniciarSesion(View view){
        Intent intent = new Intent(this, MainActivity_Login.class);
        startActivity(intent);
    }

    /**
     * Redirige a la pantalla de próximos estrenos.
     *
     * @param view Vista asociada al evento de clic.
     */
    public void proximosEstrenos(View view){
        Intent intent = new Intent(this, MainActivity_Premieres.class);
        startActivity(intent);
    }

    /**
     * Carga los usuarios desde el archivo "usuarios.txt" y los almacena en la lista de usuarios.
     *
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void cargarUsarios(){
        File archivo = new File(getFilesDir(),"/usuarios.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.trim().split(",");
                //cargamos los usuarios del archivo en la lista de usuarios
                listUsuario.add(new Usuario(Integer.parseInt(datos[0]),datos[1],datos[2],datos[3],datos[4]));
            }

        } catch (IOException e) {
            Toast.makeText(this, "Ha ocurrido un error tecnico", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Carga las películas desde el archivo "peliculas.txt" y las almacena en la lista de contenidoCine.
     *
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void cargarPeliculas(){
        File archivo = new File(getFilesDir(),"/peliculas.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.trim().split(",");
                //cargamos las peliculas del archivo en la lista de contenidoCine
                listContenidoCine.add(new Pelicula(Integer.parseInt(datos[0]),datos[1],Integer.parseInt(datos[2]),datos[3]));
            }

        } catch (IOException e) {
            Toast.makeText(this, "Ha ocurrido un error tecnico", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Carga las funciones desde el archivo "funciones.txt" y las almacena en la lista de funciones.
     *
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void cargarFunciones(){
        File archivo = new File(getFilesDir(),"/funciones.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.trim().split(",");
                //cargamos las funciones del archivo en la lista de funciones
                listFunciones.add(new Funcion(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),datos[2],datos[3],datos[4]));
            }

        } catch (IOException e) {
            Toast.makeText(this, "Ha ocurrido un error tecnico", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Carga los estrenos desde el archivo "estrenos.txt" y los almacena en la lista de contenidoCine.
     *
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void cargarEstrenos(){
        File archivo = new File(getFilesDir(),"/estrenos.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.trim().split(",");
                //cargamos los estrenos del archivo en la lista de contenidoCine
                listContenidoCine.add(new Estreno(Integer.parseInt(datos[0]),datos[1],datos[2]));
            }

        } catch (IOException e) {
            Toast.makeText(this, "Ha ocurrido un error tecnico", Toast.LENGTH_LONG).show();
        }
    }
}