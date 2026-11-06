package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Utilidades pequeñas para construir la interfaz con componentes Swing estándar. */
public final class Estilo {

    private static final DecimalFormat DINERO;

    static {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        DINERO = new DecimalFormat("#,##0", simbolos);
    }

    private Estilo() {
    }

    public static String dinero(double valor) {
        return "$" + DINERO.format(Math.round(valor));
    }

    public static int parseEntero(String texto) {
        return Integer.parseInt(texto.trim());
    }

    public static float parseSalario(String texto) {
        String salario = texto.trim().replace("$", "").replace(" ", "")
                .replace(".", "").replace(",", ".");
        if (!salario.matches("\\d+(\\.\\d+)?")) {
            throw new NumberFormatException("Salario inválido");
        }
        return Float.parseFloat(salario);
    }

    public static void mensaje(JLabel etiqueta, String texto, boolean correcto) {
        etiqueta.setText(texto);
    }

    public static JLabel etiquetaMensaje() {
        return new JLabel(" ");
    }

    public static JTextField campoTexto() {
        return new JTextField(12);
    }

    public static JPanel campo(String etiqueta, JComponent componente) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(etiqueta), BorderLayout.NORTH);
        panel.add(componente, BorderLayout.CENTER);
        return panel;
    }

    public static JPanel filaBotones(JButton... botones) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (JButton boton : botones) {
            panel.add(boton);
        }
        return panel;
    }

    public static JPanel espacio(int alto) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1, alto));
        return panel;
    }
}
