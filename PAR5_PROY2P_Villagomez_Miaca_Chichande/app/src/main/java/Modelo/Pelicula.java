package Modelo;

import java.io.Serializable;

public class Pelicula extends contenidoCine{
    private int id_movie;
    private String titulo;
    private int duracion;
    private String nombreImagen;

    /**
     * Constructor de la clase `Pelicula`.
     *
     * @param id_movie Identificador único de la película.
     * @param titulo Título de la película.
     * @param duracion Duración de la película en minutos.
     * @param nombreImagen Nombre del archivo de imagen asociado a la película.
     */
    public Pelicula(int id_movie, String titulo, int duracion, String nombreImagen) {
        super(id_movie, titulo);
        this.duracion = duracion;
        this.nombreImagen = nombreImagen;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    /**
     * Representación en cadena de la película.
     *
     * @return Cadena con la información de la película.
     */
    @Override
    public String toString() {
        return String.valueOf(getId())+","+getTitulo()+","+duracion+","+nombreImagen;
    }
}
