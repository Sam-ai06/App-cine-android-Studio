package com.example.par5_proy2p_villagomez_miaca_chichande;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Modelo.Funcion;
import Modelo.Pago;
import Modelo.Pelicula;
import Modelo.datosIncompletosException;

public class MainActivity_payments extends AppCompatActivity {

    private final double precioEntrada = 5;
    private Pelicula peliculaRecuperada;
    private String entradasRecuperada;
    private Funcion funcionRecperada;
    private double precioPagar;
    private LinearLayout linearLayoutFuncion;
    private EditText editTextNombre;
    private EditText editTextCardNumber;
    private EditText editTextCVV;
    private RadioGroup groupCardType;
    private CheckBox checkBoxConditions;
    private Button btnPagar;
    private Button btnCancelar;
    private Pago pago;
    private static int idpago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_payments);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayoutFuncion = findViewById(R.id.linearLayoutDetailsFuncion);
        Intent intentObtenido = getIntent();
        peliculaRecuperada = (Pelicula)intentObtenido.getSerializableExtra("pelicula");
        funcionRecperada = (Funcion) intentObtenido.getSerializableExtra("funcion");
        entradasRecuperada = intentObtenido.getStringExtra("entradasF");

        mostrarDetalleFuncion();

        editTextNombre = findViewById(R.id.editTextName);
        editTextCardNumber = findViewById(R.id.editTextCardNumber);
        editTextCVV = findViewById(R.id.editTextCVV);
        groupCardType = findViewById(R.id.radioGroupCardType);
        checkBoxConditions = findViewById(R.id.checkBoxConditions);
        btnPagar = findViewById(R.id.btnPayment);
        btnCancelar = findViewById(R.id.btnCancel);

    }

    /**
     * Muestra los detalles de la función seleccionada en la ventana pagos.
     *
     * - Elimina cualquier contenido previo en `linearLayoutFuncion`.
     * - Calcula el precio total a pagar en función del número de entradas y el precio unitario.
     * - Crea y configura `TextView` para mostrar la información de la película, duración, fecha, hora, sala, entradas y precio.
     * - Agrega los `TextView` al `linearLayoutFuncion` para mostrarlos en la UI.
     */
    public void mostrarDetalleFuncion(){
        linearLayoutFuncion.removeAllViews();
        precioPagar = precioEntrada * Integer.parseInt(entradasRecuperada);

        TextView txtPelicula = new TextView(this);
        txtPelicula.setText("Película: "+peliculaRecuperada.getTitulo());
        txtPelicula.setTextSize(14);

        TextView txtDuracion = new TextView(this);
        txtDuracion.setText("Duración: "+peliculaRecuperada.getDuracion()+" min");
        txtDuracion.setTextSize(14);

        TextView txtFecha = new TextView(this);
        txtFecha.setText("Fecha: "+funcionRecperada.getFecha());
        txtFecha.setTextSize(14);

        TextView txtFuncion = new TextView(this);
        txtFuncion.setText("Película: "+funcionRecperada.getHora()+" - "+funcionRecperada.getSala());
        txtFuncion.setTextSize(14);

        TextView txtEntradas = new TextView(this);
        txtEntradas.setText("Película: "+entradasRecuperada);
        txtEntradas.setTextSize(14);

        TextView txtPrecio = new TextView(this);
        txtPrecio.setText("Película: $"+precioPagar);
        txtPrecio.setTextSize(14);

        linearLayoutFuncion.addView(txtPelicula);
        linearLayoutFuncion.addView(txtDuracion);
        linearLayoutFuncion.addView(txtFecha);
        linearLayoutFuncion.addView(txtFuncion);
        linearLayoutFuncion.addView(txtEntradas);
        linearLayoutFuncion.addView(txtPrecio);

    }

    /**
     * Restablece los campos del formulario de pago a sus valores iniciales.
     *
     * - Borra el contenido de los campos de texto (`editTextNombre`, `editTextCardNumber`, `editTextCVV`).
     * - Desactiva la selección del grupo de tipo de tarjeta (`groupCardType`).
     * - Desmarca la casilla de términos y condiciones (`checkBoxConditions`).
     */
    public void resetearCampos(){
        editTextNombre.setText("");
        editTextCardNumber.setText("");
        editTextCVV.setText("");
        groupCardType.setActivated(false);
        checkBoxConditions.setChecked(false);
    }

    /**
     * Valida los datos ingresados para el pago y registra la transacción si son correctos.
     *
     * - Obtiene y verifica los datos ingresados por el usuario (nombre, número de tarjeta, tipo de tarjeta, aceptación de condiciones).
     * - Si falta algún dato, lanza una excepción `datosIncompletosException` y muestra un mensaje de error.
     * - Si los datos son correctos:
     *   - Crea un objeto `Pago` con la información ingresada.
     *   - Guarda el pago en el archivo de texto `pagos.txt`.
     *   - Serializa el objeto `Pago` en un archivo binario asociado a la función.
     *   - Llama a `resetearCampos()` para limpiar el formulario.
     *   - Muestra un mensaje de éxito al usuario.
     * - Maneja posibles errores de escritura de archivos mostrando mensajes de error apropiados.
     *
     * @param view Vista asociada al evento de clic.
     * @throws datosIncompletosException Si alguno de los datos ingresados está incompleto o es inválido.
     */
    public void realizarPago(View view){
        String name = editTextNombre.getText().toString();
        String cardNumber = editTextCardNumber.getText().toString();
        int cardTypeSelec = groupCardType.getCheckedRadioButtonId();
        RadioButton cardSelec= findViewById(cardTypeSelec);
        boolean acceptedConditions = checkBoxConditions.isChecked();
        try {
            if (name.isEmpty() || cardNumber.isEmpty() || cardSelec == null || !acceptedConditions){
                throw new datosIncompletosException("");
            } else {
                String tipoTarjeta = cardSelec.getText().toString();
                idpago += 1;
                pago = new Pago(idpago,funcionRecperada.getId_funcion(),name,cardNumber,tipoTarjeta,Integer.parseInt(entradasRecuperada),precioPagar);

                //guardamos los datos del pago en el archivo
                File archivo = new File(getExternalCacheDir(), "/pagos.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {

                    writer.write(pago.toString());
                    writer.newLine();
                    //serializamos el objeto pago
                    String archivoSerializado = "funcion" + funcionRecperada.getId_funcion() + ".bin";
                    File archivoSer = new File(getFilesDir(), archivoSerializado);

                    try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(archivoSer)))){
                        oos.writeObject(pago);
                    } catch (IOException e) {
                        System.out.println("Error al serializar el objeto");
                        e.printStackTrace();
                    }

                    resetearCampos();
                    Toast.makeText(this, "Su pago ha sido registrado con exito", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    Toast.makeText(this, "Ha ocurrido un problema texnico", Toast.LENGTH_LONG).show();
                }
            }
        } catch (datosIncompletosException e){
            Toast.makeText(this, "No estan todos los datos completos", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Redirige a la pantalla de funcion.
     *
     * @param view Vista asociada al evento de clic.
     */
    public void cancelarPago(View view){
        Intent intent = new Intent(this, MainActivity_funcion.class);
        startActivity(intent);
    }
}