package proyectoEmpresa.vista;

import java.awt.GridLayout;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;
import proyectoEmpresa.modelo.enums.Cargo;

/** Búsquedas por código, nombre y cargo, además de eliminación y listado. */
public class PanelConsultas extends JPanel {

    private final Controlador controlador;
    private final Consumer<List<Empleado>> salida;
    private final JTextField buscarCodigo = Estilo.campoTexto();
    private final JTextField buscarNombre = Estilo.campoTexto();
    private final JComboBox<Cargo> buscarCargo = new JComboBox<>(Cargo.values());
    private final JTextField eliminarCodigo = Estilo.campoTexto();
    private final JLabel mensaje = Estilo.etiquetaMensaje();

    public PanelConsultas(Controlador controlador, Consumer<List<Empleado>> salida) {
        this.controlador = controlador;
        this.salida = salida;

        JPanel consultas = new JPanel(new GridLayout(4, 1, 5, 5));
        consultas.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas"));
        consultas.add(fila("Código", buscarCodigo, "Buscar por código", this::buscarCodigo));
        consultas.add(fila("Nombre", buscarNombre, "Buscar por nombre", this::buscarNombre));
        consultas.add(fila("Cargo", buscarCargo, "Buscar por cargo", this::buscarCargo));
        consultas.add(fila("Código", eliminarCodigo, "Eliminar empleado", e -> eliminar()));

        JButton todos = new JButton("Listar todos");
        todos.addActionListener(e -> {
            salida.accept(controlador.listarEmpleados());
            mensaje.setText("Listado completo.");
        });

        setLayout(new java.awt.BorderLayout(5, 5));
        add(consultas, java.awt.BorderLayout.NORTH);
        add(Estilo.filaBotones(todos), java.awt.BorderLayout.CENTER);
        add(mensaje, java.awt.BorderLayout.SOUTH);
    }

    private JPanel fila(String etiqueta, javax.swing.JComponent entrada, String texto,
            java.awt.event.ActionListener accion) {
        JPanel panel = new JPanel(new java.awt.BorderLayout(5, 5));
        panel.add(Estilo.campo(etiqueta, entrada), java.awt.BorderLayout.CENTER);
        JButton boton = new JButton(texto);
        boton.addActionListener(accion);
        panel.add(boton, java.awt.BorderLayout.EAST);
        return panel;
    }

    private void buscarCodigo(java.awt.event.ActionEvent evento) {
        try {
            Empleado empleado = controlador.buscarPorCodigo(Estilo.parseEntero(buscarCodigo.getText()));
            salida.accept(Collections.singletonList(empleado));
            mensaje.setText("Empleado encontrado.");
        } catch (IllegalArgumentException ex) {
            salida.accept(Collections.<Empleado>emptyList());
            mensaje.setText(ex.getMessage());
        }
    }

    private void buscarNombre(java.awt.event.ActionEvent evento) {
        String nombre = buscarNombre.getText().trim();
        if (nombre.isEmpty()) {
            salida.accept(Collections.<Empleado>emptyList());
            mensaje.setText("Escribe un nombre.");
            return;
        }
        List<Empleado> encontrados = controlador.buscarPorNombre(nombre);
        salida.accept(encontrados);
        mensaje.setText(encontrados.isEmpty() ? "No hay coincidencias." : "Búsqueda realizada.");
    }

    private void buscarCargo(java.awt.event.ActionEvent evento) {
        List<Empleado> encontrados = controlador.listarPorCargo((Cargo) buscarCargo.getSelectedItem());
        salida.accept(encontrados);
        mensaje.setText(encontrados.isEmpty() ? "No hay empleados en ese cargo." : "Búsqueda realizada.");
    }

    private void eliminar() {
        try {
            int codigo = Estilo.parseEntero(eliminarCodigo.getText());
            Empleado empleado = controlador.buscarPorCodigo(codigo);
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Eliminar a " + empleado.getNombre() + "?", "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                controlador.eliminarEmpleado(codigo);
                salida.accept(controlador.listarEmpleados());
                eliminarCodigo.setText("");
                mensaje.setText("Empleado eliminado.");
            }
        } catch (IllegalArgumentException | IllegalStateException ex) {
            mensaje.setText(ex.getMessage());
        }
    }
}
