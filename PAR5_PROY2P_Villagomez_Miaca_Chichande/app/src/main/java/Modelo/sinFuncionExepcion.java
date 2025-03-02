package Modelo;

public class sinFuncionExepcion extends RuntimeException {

    /**
     * Constructor de la clase `sinFuncionExepcion`.
     *
     * @param message Mensaje de error.
     */
    public sinFuncionExepcion(String message) {
        super(message);
    }
}
