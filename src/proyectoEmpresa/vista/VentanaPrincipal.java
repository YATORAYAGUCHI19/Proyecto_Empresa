package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import proyectoEmpresa.controlador.Controlador;

/** Ventana principal: barra lateral + contenido + tabla de resultados (maqueta.html). */
public class VentanaPrincipal extends JFrame {

    private final Controlador controlador;
    private final CardLayout cartas = new CardLayout();
    private final JPanel vistas = new JPanel(cartas);
    private final PanelResultados resultados = new PanelResultados();
    private final PanelFinanzas finanzas;
    private final JLabel titulo = Estilo.texto("", Estilo.FUENTE.deriveFont(Font.BOLD, 30f), Estilo.INK);
    private final JLabel subtitulo = Estilo.texto("", Estilo.FUENTE.deriveFont(14f), Estilo.MUTED);

    public VentanaPrincipal(Controlador controlador) {
        super("Panel de empleados");
        this.controlador = controlador;
        this.finanzas = new PanelFinanzas(controlador);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1360, 800);
        setMinimumSize(new Dimension(1200, 720));
        setLocationRelativeTo(null);

        vistas.setOpaque(false);
        vistas.add(new PanelGestion(controlador), "manage");
        vistas.add(new PanelConsultas(controlador, resultados::mostrar), "search");
        vistas.add(finanzas, "metrics");
        vistas.add(new PanelSalarios(controlador, resultados::mostrar), "salary");

        JPanel cuerpo = new JPanel(new BorderLayout());
        cuerpo.setOpaque(false);
        cuerpo.add(vistas, BorderLayout.NORTH);
        cuerpo.add(resultados, BorderLayout.CENTER);

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(Estilo.BG);
        principal.setBorder(new EmptyBorder(28, 34, 28, 34));
        principal.add(crearEncabezado(), BorderLayout.NORTH);
        principal.add(cuerpo, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(crearBarraLateral(), BorderLayout.WEST);
        getContentPane().add(principal, BorderLayout.CENTER);

        mostrar("manage");
    }

    private JPanel crearEncabezado() {
        JPanel p = Estilo.vertical();
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Estilo.LINE), new EmptyBorder(0, 0, 24, 0)));
        JLabel eyebrow = Estilo.texto("EMPRESA", Estilo.FUENTE.deriveFont(Font.BOLD, 11f), Estilo.BLUE);
        eyebrow.setAlignmentX(0f);
        titulo.setAlignmentX(0f);
        subtitulo.setAlignmentX(0f);
        p.add(eyebrow);
        p.add(Estilo.espacio(7));
        p.add(titulo);
        p.add(Estilo.espacio(7));
        p.add(subtitulo);
        return p;
    }

    private JPanel crearBarraLateral() {
        JPanel lateral = new JPanel();
        lateral.setLayout(new BoxLayout(lateral, BoxLayout.Y_AXIS));
        lateral.setBackground(Estilo.NAV);
        lateral.setBorder(new EmptyBorder(26, 17, 26, 17));
        lateral.setPreferredSize(new Dimension(230, 0));

        JPanel marca = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        marca.setOpaque(false);
        marca.add(new Logo());
        marca.add(Estilo.texto("Empresa", Estilo.FUENTE.deriveFont(Font.BOLD, 19f), Color.WHITE));
        marca.setAlignmentX(0f);
        marca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        lateral.add(marca);
        lateral.add(Box.createVerticalStrut(38));

        ButtonGroup grupo = new ButtonGroup();
        lateral.add(grupo("ADMINISTRATIVO"));
        lateral.add(nav("Gestión", "manage", grupo, true));
        lateral.add(Box.createVerticalStrut(5));
        lateral.add(nav("Consultas", "search", grupo, false));

        lateral.add(Box.createVerticalStrut(25));
        JPanel linea = new JPanel();
        linea.setBackground(new Color(0x385180));
        linea.setPreferredSize(new Dimension(1, 1));
        linea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        linea.setAlignmentX(0f);
        lateral.add(linea);
        lateral.add(Box.createVerticalStrut(25));

        lateral.add(grupo("FINANCIERO"));
        lateral.add(nav("Finanzas", "metrics", grupo, false));
        lateral.add(Box.createVerticalStrut(5));
        lateral.add(nav("Salarios", "salary", grupo, false));
        lateral.add(Box.createVerticalGlue());
        return lateral;
    }

    private JLabel grupo(String texto) {
        JLabel l = Estilo.texto(texto, Estilo.FUENTE.deriveFont(Font.BOLD, 11f), new Color(0x8797b8));
        l.setBorder(new EmptyBorder(0, 10, 9, 0));
        l.setAlignmentX(0f);
        return l;
    }

    private Estilo.BotonNav nav(String texto, String vista, ButtonGroup grupo, boolean activo) {
        Estilo.BotonNav b = new Estilo.BotonNav(texto);
        b.setSelected(activo);
        b.addActionListener(e -> mostrar(vista));
        grupo.add(b);
        return b;
    }

    private void mostrar(String vista) {
        cartas.show(vistas, vista);
        switch (vista) {
            case "manage":
                titulo.setText("Gestión de empleados");
                subtitulo.setText("Área Administrativa");
                break;
            case "search":
                titulo.setText("Consultas de empleados");
                subtitulo.setText("Búsqueda y listado de registros.");
                break;
            case "metrics":
                titulo.setText("Finanzas");
                subtitulo.setText("Resumen de salarios y nómina.");
                finanzas.actualizar();
                break;
            default:
                titulo.setText("Análisis salarial");
                subtitulo.setText("Consultas según salario.");
                break;
        }
        boolean conTabla = vista.equals("search") || vista.equals("salary");
        resultados.setVisible(conTabla);
        if (conTabla) {
            resultados.mostrar(controlador.listarEmpleados());
        }
    }

    /** Cuadro azul con la "A" de la marca. */
    private static class Logo extends JPanel {
        Logo() {
            setOpaque(false);
            setPreferredSize(new Dimension(34, 34));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(Estilo.BLUE);
            g2.fillRoundRect(0, 0, 34, 34, 10, 10);
            g2.setColor(Color.WHITE);
            g2.setFont(Estilo.FUENTE.deriveFont(Font.BOLD, 19f));
            int x = (34 - g2.getFontMetrics().stringWidth("E")) / 2;
            int y = (34 + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;
            g2.drawString("A", x, y);
            g2.dispose();
        }
    }
}
