
package controlador;

import fachada.FachadaRegistros;
import java.io.IOException;
import javax.swing.JOptionPane;
import modelo.Registro;
import vista.VentanaPrincipal;

public class ControladorRegistros {

    private final VentanaPrincipal vista;
    private final FachadaRegistros fachada;

    public ControladorRegistros(VentanaPrincipal vista,
                               FachadaRegistros fachada) {

        this.vista = vista;
        this.fachada = fachada;

        // Conectar los botones con sus acciones
        vista.getBotonRegistrar().addActionListener(e -> registrar());
        vista.getBotonModificar().addActionListener(e -> modificar());
        vista.getBotonEliminar().addActionListener(e -> eliminar());
        vista.getBotonBuscar().addActionListener(e -> buscar());
        vista.getBotonLimpiar().addActionListener(e -> limpiarTodo());
        vista.getBotonGuardar().addActionListener(e -> guardar());

        // Mostrar los datos cuando seleccionamos una fila
        vista.getTablaRegistros().getSelectionModel()
                .addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        cargarSeleccion();
                    }
                });
    }

    // Abrir el programa y cargar las solicitudes
    public void iniciar() {
        vista.setVisible(true);

        try {
            fachada.cargarDatos();
            vista.mostrarRegistros(fachada.listarRegistros());
        } catch (IOException e) {
            vista.mostrarError(
                    "No se pudieron cargar los datos: " + e.getMessage());
        }
    }

    // Obtener los datos escritos en el formulario
    private Registro datosFormulario() {

        int id;

        try {
            id = Integer.parseInt(
                    vista.getCampoIdentificador().getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Escribí un identificador numérico.");
        }

        return new Registro(
                id,
                vista.getCampoNombre().getText().trim(),
                (String) vista.getComboCategoria().getSelectedItem(),
                vista.getCasillaActivo().isSelected(),
                vista.getCampoDescripcion().getText().trim(),
                (Integer) vista.getCampoCantidadEquipos().getValue(),
                vista.getPrioridadSeleccionada(),
                vista.getCampoSolucion().getText().trim()
        );
    }

    // Saber qué solicitud seleccionó el usuario
    private int idSeleccionado() {

        int fila = vista.getTablaRegistros().getSelectedRow();

        if (fila == -1) {
            throw new IllegalArgumentException(
                    "Primero seleccioná una solicitud de la tabla.");
        }

        int filaModelo =
                vista.getTablaRegistros().convertRowIndexToModel(fila);

        return Integer.parseInt(
                vista.getTablaRegistros().getModel()
                        .getValueAt(filaModelo, 0).toString());
    }

    // Mostrar la solicitud seleccionada en el formulario
    private void cargarSeleccion() {

        if (vista.getTablaRegistros().getSelectedRow() == -1) {
            return;
        }

        int id = idSeleccionado();

        for (Registro registro : fachada.listarRegistros()) {

            if (registro.getIdentificador() == id) {

                vista.getCampoIdentificador().setText(
                        String.valueOf(registro.getIdentificador()));

                vista.getCampoIdentificador().setEditable(false);

                vista.getCampoNombre().setText(registro.getNombre());

                vista.getComboCategoria().setSelectedItem(
                        registro.getCategoria());

                vista.getCasillaActivo().setSelected(
                        registro.isActivo());

                vista.getCampoDescripcion().setText(
                        registro.getDescripcion() == null
                        ? "" : registro.getDescripcion());

                vista.getCampoCantidadEquipos().setValue(
                        Math.max(1, registro.getCantidadEquipos()));

                vista.seleccionarPrioridad(registro.getPrioridad());

                vista.getCampoSolucion().setText(
                        registro.getSolucionAplicada() == null
                        ? "" : registro.getSolucionAplicada());

                break;
            }
        }
    }

    // Registrar una solicitud
    private void registrar() {

        try {
            fachada.registrar(datosFormulario());
            fachada.guardarDatos();
            vista.mostrarRegistros(fachada.listarRegistros());
            limpiarFormulario();

            vista.mostrarMensaje("Solicitud registrada correctamente.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            vista.mostrarError(e.getMessage());

        } catch (IOException e) {
            vista.mostrarRegistros(fachada.listarRegistros());
            vista.mostrarError(
                    "La solicitud quedó en memoria, pero no se pudo guardar: "
                    + e.getMessage());
        }
    }

    // Modificar una solicitud
    private void modificar() {

        try {
            int id = idSeleccionado();
            Registro datos = datosFormulario();

            if (datos.getIdentificador() != id) {
                throw new IllegalArgumentException(
                        "No se puede cambiar el identificador.");
            }

            fachada.modificar(id, datos);
            fachada.guardarDatos();
            vista.mostrarRegistros(fachada.listarRegistros());
            limpiarFormulario();

            vista.mostrarMensaje("Solicitud modificada correctamente.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            vista.mostrarError(e.getMessage());

        } catch (IOException e) {
            vista.mostrarRegistros(fachada.listarRegistros());
            vista.mostrarError(
                    "El cambio quedó en memoria, pero no se pudo guardar: "
                    + e.getMessage());
        }
    }

    // Eliminar una solicitud con confirmación
    private void eliminar() {

        try {
            int id = idSeleccionado();

            int respuesta = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Querés eliminar la solicitud " + id + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                fachada.eliminar(id);
                fachada.guardarDatos();
                vista.mostrarRegistros(fachada.listarRegistros());
                limpiarFormulario();

                vista.mostrarMensaje("Solicitud eliminada.");
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            vista.mostrarError(e.getMessage());

        } catch (IOException e) {
            vista.mostrarRegistros(fachada.listarRegistros());
            vista.mostrarError(
                    "La eliminación quedó en memoria, pero no se pudo guardar: "
                    + e.getMessage());
        }
    }

    // Buscar solicitudes por nombre, categoría o descripción
    private void buscar() {

        vista.mostrarRegistros(
                fachada.buscar(vista.getCampoBusqueda().getText()));
    }

    // Limpiar los campos
    private void limpiarFormulario() {

        vista.getTablaRegistros().clearSelection();

        vista.getCampoIdentificador().setEditable(true);
        vista.getCampoIdentificador().setText("");
        vista.getCampoNombre().setText("");

        vista.getComboCategoria().setSelectedIndex(0);
        vista.getCasillaActivo().setSelected(true);

        vista.getCampoDescripcion().setText("");
        vista.getCampoCantidadEquipos().setValue(1);
        vista.seleccionarPrioridad("Media");
        vista.getCampoSolucion().setText("");
    }

    private void limpiarTodo() {

        limpiarFormulario();
        vista.getCampoBusqueda().setText("");
        vista.mostrarRegistros(fachada.listarRegistros());
    }

    // Guardar las solicitudes en JSON
    private void guardar() {

        try {
            fachada.guardarDatos();
            vista.mostrarMensaje("Datos guardados correctamente.");

        } catch (IOException e) {
            vista.mostrarError(
                    "No se pudieron guardar los datos: " + e.getMessage());
        }
    }
}
