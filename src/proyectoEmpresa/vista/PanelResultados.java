package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyectoEmpresa.modelo.entidad.Empleado;

/** Tabla de resultados de las consultas. */
public class PanelResultados extends JPanel {

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Código", "Empleado", "Cargo", "Salario"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JLabel conteo = new JLabel("0 empleados");

    public PanelResultados() {
        setLayout(new BorderLayout(5, 5));
        add(conteo, BorderLayout.NORTH);
        JTable tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void mostrar(List<Empleado> lista) {
        modelo.setRowCount(0);
        for (Empleado empleado : lista) {
            modelo.addRow(new Object[]{empleado.getCodigo(), empleado.getNombre(),
                empleado.getCargo(), Estilo.dinero(empleado.getSalario())});
        }
        conteo.setText(lista.size() + (lista.size() == 1 ? " empleado" : " empleados"));
    }
}
