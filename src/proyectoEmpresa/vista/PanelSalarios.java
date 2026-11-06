package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;

/** Vista "Salarios": consultas según salario. El resultado va a la tabla única. */
public class PanelSalarios extends JPanel {

    private final JLabel mensaje = Estilo.etiquetaMensaje();

    public PanelSalarios(Controlador controlador, Consumer<List<Empleado>> salida) {
        setOpaque(false);
        setLayout(new BorderLayout());

        Estilo.Boton sobrePromedio = Estilo.boton("Sobre el promedio", Estilo.Tipo.PRIMARIO);
        Estilo.Boton minimo = Estilo.boton("Salario mínimo", Estilo.Tipo.NORMAL);
        Estilo.Boton todos = Estilo.boton("Todos", Estilo.Tipo.NORMAL);

        sobrePromedio.addActionListener(e -> {
            List<Empleado> l = controlador.listarQueSuperanPromedio();
            salida.accept(l);
            Estilo.mensaje(mensaje, l.isEmpty() ? "No hay empleados sobre el promedio."
                    : "Se muestran los empleados sobre el promedio.", !l.isEmpty());
        });
        minimo.addActionListener(e -> {
            List<Empleado> l = controlador.listarGananMinimo();
            salida.accept(l);
            Estilo.mensaje(mensaje, l.isEmpty() ? "Nadie gana el salario mínimo."
                    : "Se muestran los empleados con salario mínimo.", !l.isEmpty());
        });
        todos.addActionListener(e -> {
            salida.accept(controlador.listarEmpleados());
            Estilo.mensaje(mensaje, "Se muestran todos los empleados.", true);
        });

        JPanel cuerpo = Estilo.vertical();
        cuerpo.add(Estilo.filaBotones(sobrePromedio, minimo, todos));
        cuerpo.add(Estilo.espacio(12));
        cuerpo.add(mensaje);

        JPanel tarjeta = Estilo.tarjeta("Elegir consulta",
                "El resultado se muestra en la tabla única", cuerpo);
        tarjeta.setPreferredSize(new Dimension(520, tarjeta.getPreferredSize().height));
        JPanel izquierda = new JPanel(new BorderLayout());
        izquierda.setOpaque(false);
        izquierda.add(tarjeta, BorderLayout.WEST);
        add(Estilo.conTitulo("Financiero", "Salarios", izquierda), BorderLayout.CENTER);
    }
}
