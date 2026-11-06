package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;

/** Ventana principal con una pestaña independiente para cada apartado. */
public class VentanaPrincipal extends JFrame {

    private final Controlador controlador;
    private final PanelResultados resultados = new PanelResultados();
    private final PanelFinanzas finanzas;

    public VentanaPrincipal(Controlador controlador) {
        super("Gestión de empleados");
        this.controlador = controlador;
        finanzas = new PanelFinanzas(controlador);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 650);
        setMinimumSize(new Dimension(800, 500));
        setLocationRelativeTo(null);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Gestión", new PanelGestion(controlador));
        pestañas.addTab("Consultas", new PanelConsultas(controlador, this::mostrarResultados));
        pestañas.addTab("Finanzas", finanzas);
        pestañas.addTab("Salarios", new PanelSalarios(controlador, this::mostrarResultados));
        pestañas.addTab("Resultados", resultados);

        JPanel contenido = new JPanel(new BorderLayout(5, 5));
        contenido.add(new JLabel("Sistema de gestión de empleados"), BorderLayout.NORTH);
        contenido.add(pestañas, BorderLayout.CENTER);
        setContentPane(contenido);
    }

    private void mostrarResultados(List<Empleado> empleados) {
        resultados.mostrar(empleados);
    }
}
