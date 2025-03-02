package Modelo;

import java.io.Serializable;

public class Funcion implements Serializable {
    private int id_funcion;
    private int id_movie;
    private String fecha;
    private String hora;
    private String sala;

    /**
     * Constructor de la clase `Funcion`.
     *
     * @param id_funcion Identificador único de la funcion.
     * @param id_movie Identificador único de la pelicula.
     * @param fecha Fecha de la funcion.
     * @param hora Hora de la funcion.
     * @param sala Sala de la funcion.
     */
    public Funcion(int id_funcion, int id_movie, String fecha, String hora, String sala) {
        this.id_funcion = id_funcion;
        this.id_movie = id_movie;
        this.fecha = fecha;
        this.hora = hora;
        this.sala = sala;
    }

    public int getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(int id_funcion) {
        this.id_funcion = id_funcion;
    }

    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    /**
     * Representación en cadena de la funcion.
     *
     * @return Cadena con la información de la funcion.
     */
    @Override
    public String toString() {
        return "Hora: "+hora+" - Sala: "+sala;
    }
}
