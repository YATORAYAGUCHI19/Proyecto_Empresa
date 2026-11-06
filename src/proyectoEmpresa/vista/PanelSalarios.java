package proyectoEmpresa.vista;

import java.util.List;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;

/** Consultas de empleados según su salario. */
public class PanelSalarios extends JPanel {

    private final JLabel mensaje = Estilo.etiquetaMensaje();

    public PanelSalarios(Controlador controlador, Consumer<List<Empleado>> salida) {
        setLayout(new java.awt.BorderLayout(5, 5));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas salariales"));

        JButton sobrePromedio = new JButton("Sobre el promedio");
        JButton minimo = new JButton("Salario mínimo");
        JButton todos = new JButton("Todos");
        sobrePromedio.addActionListener(e -> mostrar(controlador.listarQueSuperanPromedio(), salida,
                "Empleados sobre el promedio."));
        minimo.addActionListener(e -> mostrar(controlador.listarGananMinimo(), salida,
                "Empleados con salario mínimo."));
        todos.addActionListener(e -> mostrar(controlador.listarEmpleados(), salida,
                "Todos los empleados."));

        add(Estilo.filaBotones(sobrePromedio, minimo, todos), java.awt.BorderLayout.NORTH);
        add(mensaje, java.awt.BorderLayout.SOUTH);
    }

    private void mostrar(List<Empleado> lista, Consumer<List<Empleado>> salida, String texto) {
        salida.accept(lista);
        mensaje.setText(lista.isEmpty() ? "No hay resultados." : texto);
    }
}
