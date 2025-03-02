package com.example.par5_proy2p_villagomez_miaca_chichande;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;

import Modelo.Estreno;
import Modelo.contenidoCine;

public class MainActivity_Premieres extends AppCompatActivity {

    private Button btnOrdenar;
    private Button btnSalir;

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_premieres);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnOrdenar = findViewById(R.id.btn_order);
        btnSalir = findViewById(R.id.btn_leave);
        tableLayout = findViewById(R.id.tablaEstrenos);

        mostrarDatosEstrenos();
    }

    /**
     * Muestra los datos de los próximos estrenos en un `TableLayout`.
     *
     * - Crea una fila de encabezado con los títulos "Película" y "Fecha".
     * - Recorre la lista `MainActivity.listContenidoCine` y filtra los elementos de tipo `Estreno`.
     * - Para cada estreno encontrado, crea una nueva fila con su título y fecha de estreno.
     * - Aplica estilos y alineación a las celdas de la tabla.
     * - Agrega un borde en la parte inferior de cada fila para mejorar la visualización.
     */
    private void mostrarDatosEstrenos(){

        TableRow trHeader=new TableRow(this);
        TableRow.LayoutParams params=new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);

        TextView titNombre=new TextView(this);
        titNombre.setText("Pelicula");
        titNombre.setLayoutParams(params);
        titNombre.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView titFecha=new TextView(this);
        titFecha.setText("Fecha");
        titFecha.setLayoutParams(params);
        titFecha.setLayoutParams(new TableRow.LayoutParams(300,60));
        titFecha.setGravity(Gravity.CENTER);

        trHeader.addView(titNombre);
        trHeader.addView(titFecha);

        tableLayout.addView(trHeader);


        for(contenidoCine c: MainActivity.listContenidoCine){

            if (c instanceof Estreno){
                Estreno estreno = (Estreno) c;
                TableRow tr=new TableRow(this);

                TextView t1=new TextView(this);
                t1.setText(estreno.getTitulo());
                t1.setLayoutParams(params);
                t1.setPadding(20,0,0,0);



                TextView t2=new TextView(this);
                t2.setText(String.valueOf(estreno.getFecha()));
                t2.setLayoutParams(params);
                t2.setLayoutParams(new TableRow.LayoutParams(300,60));
                t2.setGravity(Gravity.CENTER);


                tr.addView(t1);
                tr.addView(t2);
                tr.setPadding(0, 5, 0, 5);
                tr.setBackground(borderBottom(tr));

                tableLayout.addView(tr);
            }
        }
    }

    /**
     * Redirige a la pantalla de principal.
     *
     * @param view Vista asociada al evento de clic.
     */
    public void regresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Ordena la lista de estrenos y actualiza la tabla con los datos ordenados.
     *
     * - Elimina todas las filas actuales del `TableLayout`.
     * - Ordena la lista `MainActivity.listContenidoCine` utilizando `Collections.sort()`.
     * - Llama al método `mostrarDatosEstrenos()` para volver a mostrar los datos ordenados.
     *
     * @param view Vista asociada al evento de clic.
     */
    public void ordenar(View view){
        tableLayout.removeAllViews();

        Collections.sort(MainActivity.listContenidoCine);

        mostrarDatosEstrenos();
    }

    /**
     * Crea un borde inferior para un `View`, utilizado en las filas de la tabla.
     *
     * - Establece un color de fondo transparente.
     * - Define un borde negro de 2 píxeles de grosor.
     * - Ajusta el borde para colocarlo en la parte inferior del `View`.
     *
     * @param text Vista a la que se aplicará el borde.
     * @return `GradientDrawable` con el borde configurado.
     */
    public GradientDrawable borderBottom(View text){
        GradientDrawable border = new GradientDrawable();
        border.setColor(android.graphics.Color.TRANSPARENT);
        border.setStroke(2, android.graphics.Color.BLACK);
        border.setBounds(0, text.getHeight() - 5, text.getWidth(), text.getHeight());
        return border;
    }

}