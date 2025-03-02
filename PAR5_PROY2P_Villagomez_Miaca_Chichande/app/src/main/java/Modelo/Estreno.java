package Modelo;

public class Estreno extends contenidoCine{
    private int id_movie;
    private String titulo;
    private String fecha;

    /**
     * Constructor de la clase `Estreno`.
     *
     * @param id_movie Identificador único de la pelicula.
     * @param titulo Titulo de la pelicula.
     * @param fecha Fecha de estreno.
     */
    public Estreno(int id_movie, String titulo, String fecha) {
        super(id_movie, titulo);
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Representación en cadena del estreno.
     *
     * @return Cadena con la información del estreno.
     */
    @Override
    public String toString() {
        return String.valueOf(getId())+","+getTitulo()+","+fecha;
    }
}
