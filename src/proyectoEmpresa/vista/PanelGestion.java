package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.enums.Cargo;

/** Operaciones para crear empleados y actualizar salarios. */
public class PanelGestion extends JPanel {

    private final Controlador controlador;
    private final JTextField crearCodigo = Estilo.campoTexto();
    private final JComboBox<Cargo> crearCargo = new JComboBox<>(Cargo.values());
    private final JTextField crearNombre = Estilo.campoTexto();
    private final JTextField crearSalario = Estilo.campoTexto();
    private final JLabel crearMensaje = Estilo.etiquetaMensaje();
    private final JTextField actCodigo = Estilo.campoTexto();
    private final JTextField actSalario = Estilo.campoTexto();
    private final JLabel actMensaje = Estilo.etiquetaMensaje();

    public PanelGestion(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));

        JPanel formularios = new JPanel(new GridLayout(1, 2, 10, 10));
        formularios.add(crearFormulario());
        formularios.add(actualizarFormulario());
        add(formularios, BorderLayout.NORTH);
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Crear empleado"));

        JPanel campos = new JPanel(new GridLayout(4, 1, 5, 5));
        campos.add(Estilo.campo("Código", crearCodigo));
        campos.add(Estilo.campo("Nombre completo", crearNombre));
        campos.add(Estilo.campo("Cargo", crearCargo));
        campos.add(Estilo.campo("Salario mensual", crearSalario));

        JButton crear = new JButton("Crear");
        JButton limpiar = new JButton("Limpiar");
        crear.addActionListener(e -> crearEmpleado());
        limpiar.addActionListener(e -> {
            crearCodigo.setText("");
            crearNombre.setText("");
            crearSalario.setText("");
            Estilo.mensaje(crearMensaje, "Campos limpiados.", true);
        });

        panel.add(campos, BorderLayout.CENTER);
        JPanel acciones = new JPanel(new BorderLayout());
        acciones.add(Estilo.filaBotones(crear, limpiar), BorderLayout.NORTH);
        acciones.add(crearMensaje, BorderLayout.SOUTH);
        panel.add(acciones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel actualizarFormulario() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar salario"));

        JPanel campos = new JPanel(new GridLayout(2, 1, 5, 5));
        campos.add(Estilo.campo("Código del empleado", actCodigo));
        campos.add(Estilo.campo("Nuevo salario mensual", actSalario));

        JButton actualizar = new JButton("Actualizar");
        JButton limpiar = new JButton("Limpiar");
        actualizar.addActionListener(e -> actualizarSalario());
        limpiar.addActionListener(e -> {
            actCodigo.setText("");
            actSalario.setText("");
            Estilo.mensaje(actMensaje, "Campos limpiados.", true);
        });

        panel.add(campos, BorderLayout.NORTH);
        panel.add(Estilo.filaBotones(actualizar, limpiar), BorderLayout.CENTER);
        panel.add(actMensaje, BorderLayout.SOUTH);
        return panel;
    }

    private void crearEmpleado() {
        try {
            if (crearCodigo.getText().trim().isEmpty() || crearNombre.getText().trim().isEmpty()
                    || crearSalario.getText().trim().isEmpty()) {
                Estilo.mensaje(crearMensaje, "Completa código, nombre y salario.", false);
                return;
            }
            controlador.registrarEmpleado(crearNombre.getText().trim(),
                    Estilo.parseEntero(crearCodigo.getText()), Estilo.parseSalario(crearSalario.getText()),
                    (Cargo) crearCargo.getSelectedItem());
            Estilo.mensaje(crearMensaje, "Empleado creado.", true);
            crearCodigo.setText("");
            crearNombre.setText("");
            crearSalario.setText("");
        } catch (NumberFormatException ex) {
            Estilo.mensaje(crearMensaje, "Código y salario deben ser numéricos.", false);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            Estilo.mensaje(crearMensaje, ex.getMessage(), false);
        }
    }

    private void actualizarSalario() {
        try {
            if (actCodigo.getText().trim().isEmpty() || actSalario.getText().trim().isEmpty()) {
                Estilo.mensaje(actMensaje, "Completa el código y el nuevo salario.", false);
                return;
            }
            controlador.actualizarSalario(Estilo.parseEntero(actCodigo.getText()),
                    Estilo.parseSalario(actSalario.getText()));
            Estilo.mensaje(actMensaje, "Salario actualizado.", true);
            actSalario.setText("");
        } catch (NumberFormatException ex) {
            Estilo.mensaje(actMensaje, "Código y salario deben ser numéricos.", false);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            Estilo.mensaje(actMensaje, ex.getMessage(), false);
        }
    }
}
