package Modelo;

public class Usuario {
    private int id_user;
    private String name;
    private String last_name;
    private String user;
    private String password;

    /**
     * Constructor de la clase `Usuario`.
     *
     * @param id_user Identificador único del usuario.
     * @param name Nombre del usuario.
     * @param last_name Apellido del usuario.
     * @param user Usuario del sistema.
     * @param password Contraseña del usuario.
     */
    public Usuario(int id_user, String name, String last_name, String user, String password) {
        this.id_user = id_user;
        this.name = name;
        this.last_name = last_name;
        this.user = user;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Representación en cadena del usuario.
     *
     * @return Cadena con la información del usuario.
     */
    @Override
    public String toString() {
        return String.valueOf(id_user)+","+name+","+last_name+","+user+","+password;
    }
}
