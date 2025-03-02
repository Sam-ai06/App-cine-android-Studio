package com.example.par5_proy2p_villagomez_miaca_chichande;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Modelo.Pelicula;
import Modelo.contenidoCine;

public class MainActivity_Movies extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_movies);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        container = findViewById(R.id.container_peliculas);

        //mostrar las peliculas
        mostrarPeliculas();

    }

    /**
     * Muestra las películas almacenadas en la lista de contenidoCine dentro del contenedor
     *
     * Este método recorre la lista `MainActivity.listContenidoCine`, filtra los elementos de tipo `Pelicula`,
     * y genera dinámicamente botones con imágenes representando cada película. Al hacer clic en una imagen,
     * se abre la actividad `MainActivity_funcion` para mostrar más detalles sobre la película seleccionada.
     *
     * - Se eliminan todas las vistas existentes en `container` antes de agregar nuevas.
     * - Se crean botones con imágenes (`ImageButton`) para cada película y se configuran sus propiedades.
     * - Se establece un `OnClickListener` para que cada botón abra la actividad de detalles de la película.
     *
     * Requiere que cada objeto `Pelicula` tenga un nombre de imagen válido en los recursos `drawable`.
     */
    public void mostrarPeliculas(){
        container.removeAllViews();

        for (contenidoCine c: MainActivity.listContenidoCine) {
            if (c instanceof Pelicula) {
                ImageButton btnPoster = new ImageButton(this);

                Pelicula movie = (Pelicula) c;

                int imageId = getResources().getIdentifier(movie.getNombreImagen(), "drawable", getPackageName());
                btnPoster.setImageResource(imageId);

                btnPoster.setBackgroundColor(0x00000000);

                btnPoster.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                btnPoster.setAdjustViewBounds(true);
                btnPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);

                btnPoster.setOnClickListener(v -> {
                    // Crear el Intent para abrir DetailActivity
                    Intent intent = new Intent(MainActivity_Movies.this, MainActivity_funcion.class);

                    // Pasar el objeto Pelicula a la nueva actividad
                    intent.putExtra("pelicula", movie);

                    // Iniciar la nueva actividad
                    startActivity(intent);
                });

                container.addView(btnPoster);
            }

        }

    }

}