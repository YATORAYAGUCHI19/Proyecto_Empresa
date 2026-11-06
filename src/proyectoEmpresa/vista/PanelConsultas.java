package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;
import proyectoEmpresa.modelo.enums.Cargo;

/** Vista "Consultas": buscar por código / nombre / cargo, eliminar y listar. */
public class PanelConsultas extends JPanel {

    private final Controlador controlador;
    private final Consumer<List<Empleado>> salida;

    private final JTextField buscarCodigo = Estilo.campoTexto("Ej. 1042");
    private final JTextField buscarNombre = Estilo.campoTexto("Ej. Laura");
    private final JComboBox<Cargo> buscarCargo = new JComboBox<>(Cargo.values());
    private final JTextField eliminarCodigo = Estilo.campoTexto("Ej. 1056");

    private final JLabel msgCodigo = Estilo.etiquetaMensaje();
    private final JLabel msgNombre = Estilo.etiquetaMensaje();
    private final JLabel msgCargo = Estilo.etiquetaMensaje();
    private final JLabel msgEliminar = Estilo.etiquetaMensaje();

    public PanelConsultas(Controlador controlador, Consumer<List<Empleado>> salida) {
        this.controlador = controlador;
        this.salida = salida;
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel tarjetas = new JPanel(new GridLayout(1, 4, 12, 0));
        tarjetas.setOpaque(false);
        tarjetas.add(tarjetaCodigo());
        tarjetas.add(tarjetaNombre());
        tarjetas.add(tarjetaCargo());
        tarjetas.add(tarjetaEliminar());

        Estilo.Boton todos = Estilo.boton("Listar todos los empleados", Estilo.Tipo.NORMAL);
        todos.addActionListener(e -> {
            salida.accept(controlador.listarEmpleados());
            Estilo.mensaje(msgCargo, "Listado completo de empleados.", true);
        });

        JPanel contenido = new JPanel(new BorderLayout(0, 12));
        contenido.setOpaque(false);
        contenido.add(tarjetas, BorderLayout.NORTH);
        contenido.add(Estilo.filaBotones(todos), BorderLayout.CENTER);
        add(Estilo.conTitulo("Administrativo", "Consultas", contenido), BorderLayout.CENTER);
    }

    private JPanel tarjetaCodigo() {
        Estilo.Boton b = Estilo.boton("Buscar por código", Estilo.Tipo.PRIMARIO);
        b.addActionListener(e -> {
            try {
                Empleado emp = controlador.buscarPorCodigo(Estilo.parseEntero(buscarCodigo.getText()));
                salida.accept(Collections.singletonList(emp));
                Estilo.mensaje(msgCodigo, "Empleado encontrado.", true);
            } catch (NumberFormatException ex) {
                salida.accept(Collections.<Empleado>emptyList());
                Estilo.mensaje(msgCodigo, "El código debe ser un número entero.", false);
            } catch (IllegalArgumentException ex) {
                salida.accept(Collections.<Empleado>emptyList());
                Estilo.mensaje(msgCodigo, ex.getMessage(), false);
            }
        });
        return tarjeta("Buscar por código", "Identifica un empleado exacto",
                "Código del empleado", buscarCodigo, b, msgCodigo);
    }

    private JPanel tarjetaNombre() {
        Estilo.Boton b = Estilo.boton("Buscar por nombre", Estilo.Tipo.PRIMARIO);
        b.addActionListener(e -> {
            String nombre = buscarNombre.getText().trim().toLowerCase();
            List<Empleado> encontrados = new ArrayList<>();
            if (!nombre.isEmpty()) {
                for (Empleado emp : controlador.listarEmpleados()) {
                    if (emp.getNombre().toLowerCase().contains(nombre)) {
                        encontrados.add(emp);
                    }
                }
            }
            salida.accept(encontrados);
            if (nombre.isEmpty()) {
                Estilo.mensaje(msgNombre, "Escribe un nombre para buscar.", false);
            } else if (encontrados.isEmpty()) {
                Estilo.mensaje(msgNombre, "No hay empleados con ese nombre.", false);
            } else {
                Estilo.mensaje(msgNombre, "Coincidencias encontradas.", true);
            }
        });
        return tarjeta("Buscar por nombre", "Coincidencia parcial de nombre",
                "Nombre del empleado", buscarNombre, b, msgNombre);
    }

    private JPanel tarjetaCargo() {
        Estilo.estilizar(buscarCargo);
        Estilo.Boton b = Estilo.boton("Buscar por cargo", Estilo.Tipo.PRIMARIO);
        b.addActionListener(e -> {
            List<Empleado> lista = controlador.listarPorCargo((Cargo) buscarCargo.getSelectedItem());
            salida.accept(lista);
            Estilo.mensaje(msgCargo, lista.isEmpty() ? "No hay empleados en ese cargo."
                    : "Consulta por cargo aplicada.", !lista.isEmpty());
        });
        return tarjeta("Buscar por cargo", "Lista empleados de un cargo",
                "Cargo", buscarCargo, b, msgCargo);
    }

    private JPanel tarjetaEliminar() {
        Estilo.Boton b = Estilo.boton("Eliminar empleado", Estilo.Tipo.PELIGRO);
        b.addActionListener(e -> eliminar());
        return tarjeta("Eliminar por código", "Borra un registro del árbol",
                "Código del empleado", eliminarCodigo, b, msgEliminar);
    }

    private JPanel tarjeta(String titulo, String sub, String etiqueta,
            javax.swing.JComponent entrada, Estilo.Boton boton, JLabel mensaje) {
        JPanel cuerpo = Estilo.vertical();
        cuerpo.add(Estilo.campo(etiqueta, entrada));
        cuerpo.add(Estilo.espacio(18));
        cuerpo.add(Estilo.filaBotones(boton));
        cuerpo.add(Estilo.espacio(12));
        cuerpo.add(mensaje);
        return Estilo.tarjeta(titulo, sub, cuerpo);
    }

    private void eliminar() {
        try {
            int codigo = Estilo.parseEntero(eliminarCodigo.getText());
            Empleado emp = controlador.buscarPorCodigo(codigo);
            int resp = JOptionPane.showConfirmDialog(this,
                    "¿Eliminar a " + emp.getNombre() + " (código " + codigo + ")?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (resp != JOptionPane.YES_OPTION) {
                return;
            }
            controlador.eliminarEmpleado(codigo);
            salida.accept(controlador.listarEmpleados());
            eliminarCodigo.setText("");
            Estilo.mensaje(msgEliminar, "Empleado eliminado.", true);
        } catch (NumberFormatException ex) {
            Estilo.mensaje(msgEliminar, "El código debe ser un número entero.", false);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            Estilo.mensaje(msgEliminar, ex.getMessage(), false);
        }
    }
}
