package proyectoEmpresa.vista;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.enums.Cargo;

/** Vista "Gestión": crear empleado y actualizar salario. */
public class PanelGestion extends JPanel {

    private final Controlador controlador;

    private final JTextField crearCodigo = Estilo.campoTexto("Ej. 1061");
    private final JComboBox<Cargo> crearCargo = new JComboBox<>(Cargo.values());
    private final JTextField crearNombre = Estilo.campoTexto("Ej. Daniela Gómez");
    private final JTextField crearSalario = Estilo.campoTexto("Mínimo $2.000.000");
    private final JLabel crearMensaje = Estilo.etiquetaMensaje();

    private final JTextField actCodigo = Estilo.campoTexto("Ej. 1042");
    private final JTextField actSalario = Estilo.campoTexto("Mínimo $2.000.000");
    private final JLabel actMensaje = Estilo.etiquetaMensaje();

    public PanelGestion(Controlador controlador) {
        this.controlador = controlador;
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        JPanel tarjetas = new JPanel(new GridLayout(1, 2, 16, 0));
        tarjetas.setOpaque(false);
        tarjetas.add(tarjetaCrear());
        tarjetas.add(tarjetaActualizar());
        add(Estilo.conTitulo("Administrativo", "Gestión", tarjetas), java.awt.BorderLayout.CENTER);
    }

    private JPanel tarjetaCrear() {
        Estilo.estilizar(crearCargo);

        JPanel fila = new JPanel(new GridLayout(1, 2, 14, 0));
        fila.setOpaque(false);
        fila.add(Estilo.campo("Código", crearCodigo));
        fila.add(Estilo.campo("Cargo", crearCargo));

        Estilo.Boton crear = Estilo.boton("Crear empleado", Estilo.Tipo.PRIMARIO);
        Estilo.Boton limpiar = Estilo.boton("Limpiar campos", Estilo.Tipo.NORMAL);
        crear.addActionListener(e -> crearEmpleado());
        limpiar.addActionListener(e -> {
            crearCodigo.setText("");
            crearNombre.setText("");
            crearSalario.setText("");
            Estilo.mensaje(crearMensaje, "Campos limpiados.", true);
        });

        JPanel cuerpo = Estilo.vertical();
        cuerpo.add(fila);
        cuerpo.add(Estilo.espacio(14));
        cuerpo.add(Estilo.campo("Nombre completo", crearNombre));
        cuerpo.add(Estilo.espacio(14));
        cuerpo.add(Estilo.campo("Salario mensual", crearSalario));
        cuerpo.add(Estilo.espacio(18));
        cuerpo.add(Estilo.filaBotones(crear, limpiar));
        cuerpo.add(Estilo.espacio(12));
        cuerpo.add(crearMensaje);
        return Estilo.tarjeta("Crear empleado", "Solicita todos los datos del nuevo registro", cuerpo);
    }

    private JPanel tarjetaActualizar() {
        Estilo.Boton actualizar = Estilo.boton("Actualizar empleado", Estilo.Tipo.PRIMARIO);
        Estilo.Boton limpiar = Estilo.boton("Limpiar campos", Estilo.Tipo.NORMAL);
        actualizar.addActionListener(e -> actualizarSalario());
        limpiar.addActionListener(e -> {
            actCodigo.setText("");
            actSalario.setText("");
            Estilo.mensaje(actMensaje, "Campos limpiados.", true);
        });

        JPanel cuerpo = Estilo.vertical();
        cuerpo.add(Estilo.campo("Código del empleado", actCodigo));
        cuerpo.add(Estilo.espacio(14));
        cuerpo.add(Estilo.campo("Nuevo salario mensual", actSalario));
        cuerpo.add(Estilo.espacio(18));
        cuerpo.add(Estilo.filaBotones(actualizar, limpiar));
        cuerpo.add(Estilo.espacio(12));
        cuerpo.add(actMensaje);
        return Estilo.tarjeta("Actualizar salario",
                "Solo requiere identificar al empleado y escribir el nuevo salario", cuerpo);
    }

    private void crearEmpleado() {
        try {
            if (crearCodigo.getText().trim().isEmpty() || crearNombre.getText().trim().isEmpty()
                    || crearSalario.getText().trim().isEmpty()) {
                Estilo.mensaje(crearMensaje, "Completa código, nombre y salario.", false);
                return;
            }
            int codigo = Estilo.parseEntero(crearCodigo.getText());
            float salario = Estilo.parseSalario(crearSalario.getText());
            controlador.registrarEmpleado(crearNombre.getText().trim(), codigo, salario,
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
            int codigo = Estilo.parseEntero(actCodigo.getText());
            float salario = Estilo.parseSalario(actSalario.getText());
            controlador.actualizarSalario(codigo, salario);
            Estilo.mensaje(actMensaje, "Salario actualizado.", true);
            actSalario.setText("");
        } catch (NumberFormatException ex) {
            Estilo.mensaje(actMensaje, "Código y salario deben ser numéricos.", false);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            Estilo.mensaje(actMensaje, ex.getMessage(), false);
        }
    }
}
