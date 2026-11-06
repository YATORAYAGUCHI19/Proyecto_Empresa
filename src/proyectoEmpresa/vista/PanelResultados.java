package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import proyectoEmpresa.modelo.entidad.Empleado;

/** Tabla única de resultados (section#view-results de la maqueta). */
public class PanelResultados extends JPanel {

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Código", "Empleado", "Cargo", "Salario", "Estado"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JLabel conteo = Estilo.texto("0 empleados", Estilo.PEQUENA, Estilo.MUTED);

    public PanelResultados() {
        setLayout(new BorderLayout(0, 12));
        setOpaque(false);
        setBorder(new EmptyBorder(26, 0, 0, 0));

        JPanel encabezado = Estilo.vertical();
        JLabel titulo = Estilo.texto("Resultado", Estilo.FUENTE.deriveFont(Font.BOLD, 18f), Estilo.INK);
        titulo.setAlignmentX(0f);
        conteo.setAlignmentX(0f);
        encabezado.add(titulo);
        encabezado.add(Estilo.espacio(4));
        encabezado.add(conteo);
        add(encabezado, BorderLayout.NORTH);

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(38);
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(new Color(0xedf0f5));
        tabla.setSelectionBackground(Estilo.SOFT);
        tabla.setSelectionForeground(Estilo.INK);
        tabla.setFillsViewportHeight(true);
        tabla.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setPreferredSize(new java.awt.Dimension(100, 40));

        DefaultTableCellRenderer celda = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean sel,
                    boolean foco, int fila, int col) {
                super.getTableCellRendererComponent(t, v, sel, false, fila, col);
                setBorder(new EmptyBorder(0, 15, 0, 15));
                setBackground(sel ? Estilo.SOFT : Color.WHITE);
                if (col == 0) {
                    setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
                    setForeground(Estilo.BLUE);
                } else if (col == 1) {
                    setFont(Estilo.NEGRITA);
                    setForeground(Estilo.INK);
                } else {
                    setFont(Estilo.FUENTE);
                    setForeground(new Color(0x344158));
                }
                return this;
            }
        };
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(celda);
        }

        DefaultTableCellRenderer cabecera = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean sel,
                    boolean foco, int fila, int col) {
                super.getTableCellRendererComponent(t, v == null ? "" : v.toString().toUpperCase(),
                        false, false, fila, col);
                setOpaque(true);
                setBackground(new Color(0xfafbfc));
                setForeground(Estilo.MUTED);
                setFont(Estilo.PEQUENA_NEGRITA);
                setHorizontalAlignment(SwingConstants.LEFT);
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xedf0f5)),
                        new EmptyBorder(0, 15, 0, 15)));
                return this;
            }
        };
        tabla.getTableHeader().setDefaultRenderer(cabecera);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(Estilo.LINE));
        scroll.getViewport().setBackground(Color.WHITE);
        add(scroll, BorderLayout.CENTER);
    }

    public void mostrar(List<Empleado> lista) {
        modelo.setRowCount(0);
        for (Empleado e : lista) {
            modelo.addRow(new Object[]{e.getCodigo(), e.getNombre(), e.getCargo(),
                Estilo.dinero(e.getSalario()), "Registrado"});
        }
        conteo.setText(lista.size() + (lista.size() == 1 ? " empleado" : " empleados"));
    }
}
