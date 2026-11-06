package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** Colores, fuentes y componentes reutilizables (equivalente al CSS de maqueta.html). */
public final class Estilo {

    public static final Color INK = new Color(0x172033);
    public static final Color MUTED = new Color(0x6d7890);
    public static final Color LINE = new Color(0xe4e9f1);
    public static final Color BG = new Color(0xf4f6fa);
    public static final Color NAV = new Color(0x172746);
    public static final Color NAV_HOVER = new Color(0x2d4777);
    public static final Color BLUE = new Color(0x2f6fed);
    public static final Color SOFT = new Color(0xeaf1ff);
    public static final Color GREEN = new Color(0x2d936e);
    public static final Color RED = new Color(0xbd4d5b);
    public static final Color BORDE_CAMPO = new Color(0xccd4e1);

    public static final Font FUENTE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font NEGRITA = FUENTE.deriveFont(Font.BOLD);
    public static final Font PEQUENA = FUENTE.deriveFont(Font.PLAIN, 12f);
    public static final Font PEQUENA_NEGRITA = PEQUENA.deriveFont(Font.BOLD);

    public enum Tipo { PRIMARIO, NORMAL, PELIGRO }

    private static final DecimalFormat DINERO;

    static {
        DecimalFormatSymbols s = new DecimalFormatSymbols();
        s.setGroupingSeparator('.');
        DINERO = new DecimalFormat("#,##0", s);
    }

    private Estilo() {
    }

    // ---------- utilidades de datos ----------

    public static String dinero(double valor) {
        return "$" + DINERO.format(Math.round(valor));
    }

    public static int parseEntero(String texto) {
        return Integer.parseInt(texto.trim());
    }

    /** Acepta "2000000", "2.000.000" o "$2.000.000". */
    public static float parseSalario(String texto) {
        String s = texto.trim().replace("$", "").replace(" ", "").replace(".", "").replace(",", ".");
        if (!s.matches("\\d+(\\.\\d+)?")) {
            throw new NumberFormatException("Salario inválido");
        }
        return Float.parseFloat(s);
    }

    public static void mensaje(JLabel etiqueta, String texto, boolean ok) {
        etiqueta.setForeground(ok ? GREEN : RED);
        etiqueta.setText(texto);
    }

    // ---------- componentes ----------

    public static JLabel etiquetaMensaje() {
        JLabel l = new JLabel(" ");
        l.setFont(PEQUENA_NEGRITA);
        l.setForeground(GREEN);
        return l;
    }

    public static JLabel texto(String t, Font f, Color c) {
        JLabel l = new JLabel(t);
        l.setFont(f);
        l.setForeground(c);
        return l;
    }

    public static JTextField campoTexto(String sugerencia) {
        JTextField campo = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2.setColor(new Color(0xa3acbd));
                    g2.setFont(getFont());
                    int y = (getHeight() + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;
                    g2.drawString(sugerencia, getInsets().left, y);
                    g2.dispose();
                }
            }
        };
        estilizar(campo);
        return campo;
    }

    public static void estilizar(JComponent c) {
        c.setFont(FUENTE);
        c.setForeground(INK);
        c.setBackground(Color.WHITE);
        if (c instanceof JTextField) {
            c.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDE_CAMPO), new EmptyBorder(0, 10, 0, 10)));
        }
        c.setPreferredSize(new Dimension(100, 37));
    }

    /** Etiqueta encima del componente (div.field de la maqueta). */
    public static JPanel campo(String etiqueta, JComponent componente) {
        JPanel p = new JPanel(new BorderLayout(0, 6));
        p.setOpaque(false);
        p.add(texto(etiqueta, PEQUENA_NEGRITA, new Color(0x46536a)), BorderLayout.NORTH);
        p.add(componente, BorderLayout.CENTER);
        return p;
    }

    public static JPanel vertical() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        return p;
    }

    public static JPanel filaBotones(JButton... botones) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        p.setOpaque(false);
        for (JButton b : botones) {
            p.add(b);
        }
        return p;
    }

    public static JPanel espacio(int alto) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setPreferredSize(new Dimension(1, alto));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, alto));
        return p;
    }

    /** Tarjeta blanca con encabezado (título + subtítulo) y cuerpo. */
    public static JPanel tarjeta(String titulo, String subtitulo, JComponent cuerpo) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(LINE));

        JPanel head = vertical();
        head.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, LINE), new EmptyBorder(16, 17, 14, 17)));
        JLabel t = texto(titulo, FUENTE.deriveFont(Font.BOLD, 16f), INK);
        JLabel s = texto(subtitulo, PEQUENA, MUTED);
        t.setAlignmentX(0f);
        s.setAlignmentX(0f);
        head.add(t);
        head.add(espacio(4));
        head.add(s);
        card.add(head, BorderLayout.NORTH);

        cuerpo.setBorder(new EmptyBorder(16, 17, 16, 17));
        JPanel envoltura = new JPanel(new BorderLayout());
        envoltura.setOpaque(false);
        envoltura.add(cuerpo, BorderLayout.NORTH);
        card.add(envoltura, BorderLayout.CENTER);
        return card;
    }

    /** Título de sección: "Administrativo  Gestión". */
    public static JPanel conTitulo(String grupo, String vista, JComponent contenido) {
        JPanel p = new JPanel(new BorderLayout(0, 12));
        p.setOpaque(false);
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        titulo.setOpaque(false);
        titulo.setBorder(new EmptyBorder(24, 0, 0, 0));
        titulo.add(texto(grupo, FUENTE.deriveFont(Font.BOLD, 18f), INK));
        titulo.add(texto(vista, PEQUENA, MUTED));
        p.add(titulo, BorderLayout.NORTH);
        p.add(contenido, BorderLayout.CENTER);
        return p;
    }

    // ---------- botones ----------

    public static class Boton extends JButton {
        private final Tipo tipo;

        public Boton(String texto, Tipo tipo) {
            super(texto);
            this.tipo = tipo;
            setFont(NEGRITA);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);
            setRolloverEnabled(true);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(9, 14, 9, 14));
            setForeground(tipo == Tipo.PRIMARIO ? Color.WHITE
                    : tipo == Tipo.PELIGRO ? RED : new Color(0x43516a));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            boolean hover = getModel().isRollover();
            Color fondo = tipo == Tipo.PRIMARIO ? (hover ? BLUE.darker() : BLUE) : Color.WHITE;
            Color borde = tipo == Tipo.PRIMARIO ? BLUE
                    : tipo == Tipo.PELIGRO ? new Color(0xefcdd2)
                    : (hover ? BLUE : BORDE_CAMPO);
            g2.setColor(fondo);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
            g2.setColor(borde);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static Boton boton(String texto, Tipo tipo) {
        return new Boton(texto, tipo);
    }

    /** Botón de la barra lateral. */
    public static class BotonNav extends JToggleButton {
        public BotonNav(String texto) {
            super(texto);
            setFont(FUENTE);
            setHorizontalAlignment(SwingConstants.LEFT);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);
            setRolloverEnabled(true);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(10, 14, 10, 11));
            setAlignmentX(0f);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (isSelected() || getModel().isRollover()) {
                g2.setColor(NAV_HOVER);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
            }
            g2.dispose();
            setForeground(isSelected() ? Color.WHITE : new Color(0xc0cce2));
            super.paintComponent(g);
        }
    }
}
