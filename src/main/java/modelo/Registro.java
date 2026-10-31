
package modelo;

public class Registro {

    // Datos de cada solicitud de soporte técnico
    private int identificador;
    private String nombre;
    private String categoria;
    private boolean activo;

    // Datos agregados para nuestro proyecto
    private String descripcion;
    private int cantidadEquipos;
    private String prioridad;
    private String solucionAplicada;

    // Constructor original del profesor
    public Registro(int identificador, String nombre,
                    String categoria, boolean activo) {
        this(identificador, nombre, categoria, activo,
             "", 1, "Media", "");
    }

    // Constructor con los datos de soporte técnico
    public Registro(int identificador, String nombre,
                    String categoria, boolean activo,
                    String descripcion, int cantidadEquipos,
                    String prioridad, String solucionAplicada) {

        this.identificador = identificador;
        this.nombre = nombre;
        this.categoria = categoria;
        this.activo = activo;
        this.descripcion = descripcion;
        this.cantidadEquipos = cantidadEquipos;
        this.prioridad = prioridad;
        this.solucionAplicada = solucionAplicada;
    }

    // Consultar los datos
    public int getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadEquipos() {
        return cantidadEquipos;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getSolucionAplicada() {
        return solucionAplicada;
    }

    // Modificar los datos
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidadEquipos(int cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setSolucionAplicada(String solucionAplicada) {
        this.solucionAplicada = solucionAplicada;
    }

    // Comprobar los datos de una solicitud
    public void validar() {

        if (identificador <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser mayor que cero.");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "Debe escribir el nombre del problema.");
        }

        if (cantidadEquipos < 1) {
            throw new IllegalArgumentException(
                    "La cantidad de equipos debe ser mayor que cero.");
        }

        // Una solicitud no puede cerrarse sin una solución
        if (!activo && (solucionAplicada == null
                || solucionAplicada.isBlank())) {

            throw new IllegalArgumentException(
                    "Para cerrar una solicitud debe indicar la solución aplicada.");
        }
    }

}
