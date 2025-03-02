package Modelo;

import java.io.Serializable;

public class Pago implements Serializable {
    private int id_pago;
    private int id_funcion;
    private String name;
    private String numTarjeta;
    private String tipo;
    private int numEntradas;
    private double totalPagar;

    /**
     * Constructor de la clase `Pago`.
     *
     * @param id_pago Identificador único del pago.
     * @param id_funcion Identificador único de la funcion.
     * @param name Nombre del usuario.
     * @param numTarjeta Numero de tarjeta del usuario.
     * @param tipo Tipo de tarjeta del usuario.
     * @param numEntradas Numero de entradas compradas.
     * @param totalPagar Total a pagar.
     */
    public Pago(int id_pago, int id_funcion, String name, String numTarjeta, String tipo, int numEntradas, double totalPagar) {
        this.id_pago = id_pago;
        this.id_funcion = id_funcion;
        this.name = name;
        this.numTarjeta = numTarjeta;
        this.tipo = tipo;
        this.numEntradas = numEntradas;
        this.totalPagar = totalPagar;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(int id_funcion) {
        this.id_funcion = id_funcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    /**
     * Representación en cadena del pago.
     *
     * @return Cadena con la información del pago.
     */
    @Override
    public String toString() {
        return String.valueOf(id_pago)+","+String.valueOf(id_funcion)+","+name+","+
                numTarjeta+","+tipo+","+String.valueOf(numEntradas)+","+String.valueOf(totalPagar);
    }
}
