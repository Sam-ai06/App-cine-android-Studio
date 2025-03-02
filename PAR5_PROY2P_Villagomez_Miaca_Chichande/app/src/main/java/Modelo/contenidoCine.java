package Modelo;

import java.io.Serializable;

public abstract class contenidoCine implements Serializable, Comparable<contenidoCine>{
    private int id;
    private String titulo;

    /**
     * Constructor de la clase `contenidoCine`.
     *
     * @param id Identificador único del contenido.
     * @param titulo Título del contenido.
     */
    public contenidoCine(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    /**
     * Compara este objeto con otro objeto de tipo `contenidoCine`.
     * @param otro Objeto a comparar.
     * @return Retorna un valor entero dependiendo de la comparación.
     */
    @Override
    public int compareTo(contenidoCine otro) {
        return this.titulo.compareTo(otro.getTitulo());
    }

    /**
     * Representación en cadena del contenido.
     *
     * @return Cadena con la información del contenido.
     */
    @Override
    public abstract String toString();
}
