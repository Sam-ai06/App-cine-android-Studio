package com.example.par5_proy2p_villagomez_miaca_chichande;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;

import Modelo.Funcion;
import Modelo.Pelicula;
import Modelo.datosIncompletosException;
import Modelo.sinFuncionExepcion;

public class MainActivity_funcion extends AppCompatActivity {

    private Pelicula peliculaRecuperada;
    private LinearLayout linearLayoutmovie;
    private Spinner spHorario;
    private EditText editTextfecha;
    private EditText editTextEntradas;
    private Button btnContinuar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_funcion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayoutmovie = findViewById(R.id.linearLayoutPelicula);
        spHorario = findViewById(R.id.spTimeTable);
        editTextfecha = findViewById(R.id.editTextDate);
        editTextEntradas = findViewById(R.id.editTextEntries);
        btnContinuar = findViewById(R.id.btn_Continue);
        btnCancelar = findViewById(R.id.btn_Cancel);

        Intent intentObtenido = getIntent();
        peliculaRecuperada = (Pelicula)intentObtenido.getSerializableExtra("pelicula");

        cargarDatosPeliculas(peliculaRecuperada);


        editTextfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpicker = new DatePickerDialog(MainActivity_funcion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker dataPicker, int yearSel, int monthSel, int dayOfMonthSel) {
                        String fechaSeleccionada = String.format("%02d/%02d/%04d", dayOfMonthSel, (monthSel+1), yearSel);
                        editTextfecha.setText(fechaSeleccionada);

                        cargarFunciones(fechaSeleccionada);

                    }
                },year, month, day);
                dpicker.show();
            }
        });
    }

    /**
     * Carga y muestra los datos de una película en la ventana funcion.
     *
     * - Crea un `ImageView` para mostrar la imagen de la película obtenida de los recursos `drawable`.
     * - Crea un `LinearLayout` vertical para organizar los textos con la información de la película.
     * - Agrega un `TextView` con el título de la película en negrita.
     * - Agrega un `TextView` con la duración de la película.
     * - Inserta todos los elementos en `linearLayoutmovie` para que se muestren correctamente en la UI.
     *
     * @param movie Objeto `Pelicula` con los datos de la película a mostrar.
     */
    public void cargarDatosPeliculas(Pelicula movie){
        ImageView imageV = new ImageView(this);
        int imageId = getResources().getIdentifier(movie.getNombreImagen(), "drawable", getPackageName());
        imageV.setImageResource(imageId);
        imageV.setLayoutParams(new LinearLayout.LayoutParams(
                200,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        LinearLayout linearLayoutTxt = new LinearLayout(this);
        linearLayoutTxt.setOrientation(LinearLayout.VERTICAL);
        linearLayoutTxt.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        TextView txt_title = new TextView(this);
        txt_title.setText(movie.getTitulo());
        txt_title.setTextSize(14);
        txt_title.setTypeface(txt_title.getTypeface(), Typeface.BOLD);

        TextView txt_duracion = new TextView(this);
        txt_duracion.setText(String.valueOf(movie.getDuracion()));
        txt_duracion.setTextSize(14);

        linearLayoutmovie.addView(imageV);
        linearLayoutmovie.addView(linearLayoutTxt);
        linearLayoutTxt.addView(txt_title);
        linearLayoutTxt.addView(txt_duracion);

    }

    /**
     * Carga las funciones disponibles para una fecha específica y las muestra en un Spinner.
     *
     * - Filtra la lista `MainActivity.listFunciones` para encontrar funciones con la fecha indicada.
     * - Si hay funciones disponibles, las agrega a un `ArrayAdapter` y las muestra en `spHorario`.
     * - Si no hay funciones para la fecha seleccionada, lanza la excepción `sinFuncionExepcion`
     *   y muestra un mensaje de error al usuario.
     *
     * @param fecha Fecha para la cual se desean cargar las funciones en formato `String`.
     * @throws sinFuncionExepcion Si no hay funciones disponibles para la fecha seleccionada.
     */
    public void cargarFunciones(String fecha){
        ArrayList<String> datosFuncion = new ArrayList<>();

        for (Funcion funcion: MainActivity.listFunciones){
            if (funcion.getFecha().equals(fecha)){
                datosFuncion.add(funcion.toString());
            }
        }

        try {
            if (datosFuncion.size() != 0){
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity_funcion.this, android.R.layout.simple_spinner_item, datosFuncion);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spHorario.setAdapter(adapter);
            } else {
                throw new sinFuncionExepcion("");
            }
        } catch (sinFuncionExepcion e) {
            spHorario.setAdapter(null);
            Toast.makeText(this, "No hay funciones para la fecha seleccionada", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Valida los datos ingresados por el usuario y redirige a la ventana de pagos
     *
     * - Verifica que la fecha, el horario y el número de entradas hayan sido ingresados correctamente.
     * - Valida que el número de entradas sea un entero válido.
     * - Extrae la información de la función seleccionada (hora y sala) del Spinner.
     * - Busca la función correspondiente en `MainActivity.listFunciones`.
     * - Si los datos son válidos, inicia la actividad `MainActivity_payments` y pasa los datos necesarios.
     * - Si falta algún dato o hay errores en la entrada, lanza una excepción `datosIncompletosException`
     *   y muestra un mensaje de advertencia.
     *
     * @param view Vista asociada al evento de clic.
     * @throws datosIncompletosException Si alguno de los datos ingresados está incompleto o es inválido.
     */
    public void continuar(View view){
        String fecha = editTextfecha.getText().toString();
        String horarioSeleccionado = spHorario.getSelectedItem().toString(); //hora y sala
        String entradas = editTextEntradas.getText().toString();
        Funcion funcionSelec = null;
        boolean isnotEntero = true;

        try {
            Integer.parseInt(entradas);
            isnotEntero = false;
        } catch (NumberFormatException e) {

        }

        try {
            if (fecha.isEmpty() || horarioSeleccionado.isEmpty() || entradas.isEmpty() || isnotEntero){
                throw new datosIncompletosException("");
            } else {
                Intent intent = new Intent(this, MainActivity_payments.class);
                intent.putExtra("pelicula",peliculaRecuperada);
                intent.putExtra("entradasF",entradas);

                //separamos por guion
                String[] partes = horarioSeleccionado.split(" - ");
                //obtenemos la hora
                String hora = partes[0].split(": ")[1];
                //obtenemos la sala
                String sala = partes[1].split(": ")[1];
                for (Funcion funcion: MainActivity.listFunciones){
                    if (funcion.getFecha().equals(fecha) && funcion.getHora().equals(hora) && funcion.getSala().equals(sala)){
                        funcionSelec = funcion;
                    }
                }

                intent.putExtra("funcion",funcionSelec);

                startActivity(intent);

            }
        } catch (datosIncompletosException e) {
            Toast.makeText(this, "No estan todos los datos completos", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Redirige a la pantalla de peliculas.
     *
     * @param view Vista asociada al evento de clic.
     */
    public void cancelar(View view){
        Intent intent = new Intent(this, MainActivity_Movies.class);
        startActivity(intent);
    }
}