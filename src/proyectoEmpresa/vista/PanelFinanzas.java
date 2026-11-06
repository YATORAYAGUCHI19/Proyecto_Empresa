package proyectoEmpresa.vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;

/** Muestra los indicadores financieros de la empresa. */
public class PanelFinanzas extends JPanel {

    private final Controlador controlador;
    private final JLabel nomina = new JLabel();
    private final JLabel promedio = new JLabel();
    private final JLabel mayor = new JLabel();
    private final JLabel cantidad = new JLabel();

    public PanelFinanzas(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new java.awt.BorderLayout(5, 5));

        JPanel datos = new JPanel(new GridLayout(4, 2, 5, 5));
        datos.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen financiero"));
        datos.add(new JLabel("Nómina total"));
        datos.add(nomina);
        datos.add(new JLabel("Promedio salarial"));
        datos.add(promedio);
        datos.add(new JLabel("Mayor salario"));
        datos.add(mayor);
        datos.add(new JLabel("Cantidad de empleados"));
        datos.add(cantidad);

        JButton actualizar = new JButton("Actualizar datos");
        actualizar.addActionListener(e -> actualizar());
        add(datos, java.awt.BorderLayout.NORTH);
        add(actualizar, java.awt.BorderLayout.SOUTH);
        actualizar();
    }

    public void actualizar() {
        nomina.setText(Estilo.dinero(controlador.calcularValorNomina()));
        promedio.setText(Estilo.dinero(controlador.calcularPromedioSalarios()));
        Empleado empleado = controlador.obtenerEmpleadoQueMasGana();
        mayor.setText(empleado == null ? Estilo.dinero(0) : Estilo.dinero(empleado.getSalario()));
        cantidad.setText(String.valueOf(controlador.listarEmpleados().size()));
    }
}
