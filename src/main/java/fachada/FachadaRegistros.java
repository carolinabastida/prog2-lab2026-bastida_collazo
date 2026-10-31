
package fachada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.Registro;
import persistencia.PersistenciaRegistros;

public class FachadaRegistros {

    private final PersistenciaRegistros persistencia;
    private List<Registro> registros;
    private boolean cargaCorrecta;

    public FachadaRegistros(PersistenciaRegistros persistencia) {
        this.persistencia = persistencia;
        registros = new ArrayList<>();
    }

    // Cargar las solicitudes guardadas
    public void cargarDatos() throws IOException {
        cargaCorrecta = false;
        registros = persistencia.leer();
        cargaCorrecta = true;
    }

    // Obtener la lista de solicitudes
    public List<Registro> listarRegistros() {
        return new ArrayList<>(registros);
    }

    // Registrar una solicitud nueva
    public void registrar(Registro nuevo) {
        comprobarCarga();

        if (nuevo == null) {
            throw new IllegalArgumentException("Faltan los datos de la solicitud.");
        }

        nuevo.validar();

        for (Registro registro : registros) {
            if (registro.getIdentificador() == nuevo.getIdentificador()) {
                throw new IllegalArgumentException(
                        "Ya existe una solicitud con ese identificador.");
            }
        }

        registros.add(nuevo);
    }

    // Modificar una solicitud existente
    public void modificar(int identificador, Registro datos) {
        comprobarCarga();

        if (datos == null) {
            throw new IllegalArgumentException("Faltan los datos para modificar.");
        }

        datos.validar();

        for (Registro registro : registros) {
            if (registro.getIdentificador() == identificador) {

                registro.setNombre(datos.getNombre());
                registro.setCategoria(datos.getCategoria());
                registro.setActivo(datos.isActivo());
                registro.setDescripcion(datos.getDescripcion());
                registro.setCantidadEquipos(datos.getCantidadEquipos());
                registro.setPrioridad(datos.getPrioridad());
                registro.setSolucionAplicada(datos.getSolucionAplicada());

                return;
            }
        }

        throw new IllegalArgumentException("No se encontró la solicitud.");
    }

    // Eliminar una solicitud por su identificador
    public void eliminar(int identificador) {
        comprobarCarga();

        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getIdentificador() == identificador) {
                registros.remove(i);
                return;
            }
        }

        throw new IllegalArgumentException("No se encontró la solicitud.");
    }

    // Buscar solicitudes
    public List<Registro> buscar(String texto) {
        List<Registro> resultados = new ArrayList<>();

        if (texto == null || texto.trim().isEmpty()) {
            return listarRegistros();
        }

        String busqueda = texto.trim().toLowerCase();

        for (Registro registro : registros) {

            String nombre = registro.getNombre();
            String categoria = registro.getCategoria();
            String descripcion = registro.getDescripcion();

            if ((nombre != null && nombre.toLowerCase().contains(busqueda))
                    || (categoria != null && categoria.toLowerCase().contains(busqueda))
                    || (descripcion != null && descripcion.toLowerCase().contains(busqueda))) {

                resultados.add(registro);
            }
        }

        return resultados;
    }

    // Guardar los cambios en JSON
    public void guardarDatos() throws IOException {
        if (!cargaCorrecta) {
            throw new IOException(
                    "No se puede guardar porque los datos no se cargaron correctamente.");
        }

        persistencia.escribir(registros);
    }

    // Evitar cambios si hubo problemas al cargar
    private void comprobarCarga() {
        if (!cargaCorrecta) {
            throw new IllegalStateException(
                    "No se pueden modificar datos porque falló la carga inicial.");
        }
    }
}
