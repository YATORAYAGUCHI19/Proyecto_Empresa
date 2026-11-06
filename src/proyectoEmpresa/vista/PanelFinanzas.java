package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;

/** Vista "Finanzas": indicadores calculados por el modelo. */
public class PanelFinanzas extends JPanel {

    private final Controlador controlador;
    private final JLabel nomina = valor();
    private final JLabel promedio = valor();
    private final JLabel mayor = valor();
    private final JLabel cantidad = valor();

    public PanelFinanzas(Controlador controlador) {
        this.controlador = controlador;
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel hechos = Estilo.vertical();
        hechos.add(fila("Nómina total", nomina, true));
        hechos.add(fila("Promedio salarial", promedio, true));
        hechos.add(fila("Mayor salario", mayor, true));
        hechos.add(fila("Cantidad de empleados", cantidad, false));

        JPanel tarjeta = Estilo.tarjeta("Resumen financiero",
                "Indicadores calculados sobre los datos de la Empresa", hechos);
        tarjeta.setPreferredSize(new Dimension(520, tarjeta.getPreferredSize().height));

        JPanel izquierda = new JPanel(new BorderLayout());
        izquierda.setOpaque(false);
        izquierda.add(tarjeta, BorderLayout.WEST);
        add(Estilo.conTitulo("Financiero", "Finanzas", izquierda), BorderLayout.CENTER);
        actualizar();
    }

    private static JLabel valor() {
        return Estilo.texto("-", Estilo.PEQUENA_NEGRITA, Estilo.INK);
    }

    private static JPanel fila(String etiqueta, JLabel valor, boolean linea) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, linea ? 1 : 0, 0, new Color(0xedf0f5)),
                new EmptyBorder(11, 0, 11, 0)));
        p.add(Estilo.texto(etiqueta, Estilo.PEQUENA, Estilo.MUTED), BorderLayout.WEST);
        p.add(valor, BorderLayout.EAST);
        return p;
    }

    /** Recalcula los indicadores con los datos actuales. */
    public void actualizar() {
        nomina.setText(Estilo.dinero(controlador.calcularValorNomina()));
        promedio.setText(Estilo.dinero(controlador.calcularPromedioSalarios()));
        Empleado top = controlador.obtenerEmpleadoQueMasGana();
        mayor.setText(top == null ? Estilo.dinero(0) : Estilo.dinero(top.getSalario()));
        cantidad.setText(String.valueOf(controlador.listarEmpleados().size()));
    }
}
