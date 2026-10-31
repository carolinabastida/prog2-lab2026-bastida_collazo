
package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import modelo.Registro;

public class VentanaPrincipal extends JFrame {

    private final JTextField campoIdentificador = new JTextField(10);
    private final JTextField campoNombre = new JTextField(20);

    private final JComboBox<String> comboCategoria =
            new JComboBox<>(new String[] {"Hardware", "Software", "Red"});

    private final JCheckBox casillaActivo =
            new JCheckBox("Solicitud activa", true);

    // Campos nuevos para las solicitudes de soporte técnico
    private final JTextArea campoDescripcion = new JTextArea(2, 20);

    private final JSpinner campoCantidadEquipos =
            new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

    private final JRadioButton radioAlta = new JRadioButton("Alta");
    private final JRadioButton radioMedia = new JRadioButton("Media", true);
    private final JRadioButton radioBaja = new JRadioButton("Baja");

    private final JTextArea campoSolucion = new JTextArea(2, 20);

    private final JTextField campoBusqueda = new JTextField(20);

    private final JButton botonRegistrar = new JButton("Registrar");
    private final JButton botonModificar = new JButton("Modificar");
    private final JButton botonEliminar = new JButton("Eliminar");
    private final JButton botonBuscar = new JButton("Buscar");
    private final JButton botonLimpiar = new JButton("Limpiar");
    private final JButton botonGuardar = new JButton("Guardar");

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new String[] {
                "ID", "Problema", "Categoría", "Activo",
                "Descripción", "Equipos", "Prioridad", "Solución aplicada"
            }, 0) {

        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    private final JTable tablaRegistros = new JTable(modeloTabla);

    public VentanaPrincipal() {

        super("Solicitudes de soporte técnico");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Solo se puede elegir una prioridad
        ButtonGroup grupoPrioridad = new ButtonGroup();
        grupoPrioridad.add(radioAlta);
        grupoPrioridad.add(radioMedia);
        grupoPrioridad.add(radioBaja);

        JPanel opcionesPrioridad =
                new JPanel(new FlowLayout(FlowLayout.LEFT));

        opcionesPrioridad.add(radioAlta);
        opcionesPrioridad.add(radioMedia);
        opcionesPrioridad.add(radioBaja);

        campoDescripcion.setLineWrap(true);
        campoDescripcion.setWrapStyleWord(true);

        campoSolucion.setLineWrap(true);
        campoSolucion.setWrapStyleWord(true);

        // Formulario de las solicitudes
        JPanel formulario = new JPanel(new GridLayout(8, 2, 5, 5));

        formulario.add(new JLabel("Identificador:"));
        formulario.add(campoIdentificador);

        formulario.add(new JLabel("Nombre del problema:"));
        formulario.add(campoNombre);

        formulario.add(new JLabel("Categoría:"));
        formulario.add(comboCategoria);

        formulario.add(new JLabel("Estado:"));
        formulario.add(casillaActivo);

        formulario.add(new JLabel("Descripción del problema:"));
        formulario.add(new JScrollPane(campoDescripcion));

        formulario.add(new JLabel("Cantidad de equipos:"));
        formulario.add(campoCantidadEquipos);

        formulario.add(new JLabel("Prioridad:"));
        formulario.add(opcionesPrioridad);

        formulario.add(new JLabel("Solución aplicada:"));
        formulario.add(new JScrollPane(campoSolucion));

        // Botones del programa
        JPanel acciones = new JPanel(new FlowLayout());

        acciones.add(botonRegistrar);
        acciones.add(botonModificar);
        acciones.add(botonEliminar);
        acciones.add(botonLimpiar);
        acciones.add(botonGuardar);

        JScrollPane scrollFormulario = new JScrollPane(formulario);
        scrollFormulario.setPreferredSize(new Dimension(960, 380));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(scrollFormulario, BorderLayout.CENTER);
        panelSuperior.add(acciones, BorderLayout.SOUTH);

        // Búsqueda de solicitudes
        JPanel busqueda = new JPanel(new FlowLayout());

        busqueda.add(new JLabel("Buscar:"));
        busqueda.add(campoBusqueda);
        busqueda.add(botonBuscar);

        // Tabla de solicitudes
        tablaRegistros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] anchos = {65, 190, 100, 65, 220, 70, 80, 220};

        for (int i = 0; i < anchos.length; i++) {
            tablaRegistros.getColumnModel()
                    .getColumn(i).setPreferredWidth(anchos[i]);
        }

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaRegistros), BorderLayout.CENTER);
        add(busqueda, BorderLayout.SOUTH);

        setSize(1050, 720);
        setLocationRelativeTo(null);
    }

    // Mostrar las solicitudes en la tabla
    public void mostrarRegistros(List<Registro> registros) {

        modeloTabla.setRowCount(0);

        for (Registro registro : registros) {

            modeloTabla.addRow(new Object[] {
                registro.getIdentificador(),
                registro.getNombre(),
                registro.getCategoria(),
                registro.isActivo() ? "Sí" : "No",
                registro.getDescripcion(),
                registro.getCantidadEquipos(),
                registro.getPrioridad(),
                registro.getSolucionAplicada()
            });
        }
    }

    public void mostrarMensaje(String mensaje) {

        JOptionPane.showMessageDialog(
                this, mensaje, "Soporte técnico",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {

        JOptionPane.showMessageDialog(
                this, mensaje, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Métodos para acceder a los campos y botones
    public JTextField getCampoIdentificador() {
        return campoIdentificador;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JComboBox<String> getComboCategoria() {
        return comboCategoria;
    }

    public JCheckBox getCasillaActivo() {
        return casillaActivo;
    }

    public JTextField getCampoBusqueda() {
        return campoBusqueda;
    }

    public JTable getTablaRegistros() {
        return tablaRegistros;
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JButton getBotonBuscar() {
        return botonBuscar;
    }

    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }

    public JButton getBotonGuardar() {
        return botonGuardar;
    }

    // Acceso a los campos nuevos
    public JTextArea getCampoDescripcion() {
        return campoDescripcion;
    }

    public JSpinner getCampoCantidadEquipos() {
        return campoCantidadEquipos;
    }

    public JTextArea getCampoSolucion() {
        return campoSolucion;
    }

    public String getPrioridadSeleccionada() {

        if (radioAlta.isSelected()) {
            return "Alta";
        }

        if (radioBaja.isSelected()) {
            return "Baja";
        }

        return "Media";
    }

    public void seleccionarPrioridad(String prioridad) {

        if ("Alta".equalsIgnoreCase(prioridad)) {
            radioAlta.setSelected(true);
        } else if ("Baja".equalsIgnoreCase(prioridad)) {
            radioBaja.setSelected(true);
        } else {
            radioMedia.setSelected(true);
        }
    }
}
