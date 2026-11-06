package proyectoEmpresa.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;
import proyectoEmpresa.modelo.enums.Cargo;

/** Ventana Swing sencilla con todos los controles en un solo archivo. */
public class VentanaPrincipal extends JFrame {

    private final Controlador controlador;
    private final JTextField codigoCrear = new JTextField();
    private final JTextField nombreCrear = new JTextField();
    private final JTextField salarioCrear = new JTextField();
    private final JComboBox<Cargo> cargoCrear = new JComboBox<>(Cargo.values());
    private final JTextField codigoActualizar = new JTextField();
    private final JTextField salarioActualizar = new JTextField();
    private final JTextField codigoBuscar = new JTextField();
    private final JTextField nombreBuscar = new JTextField();
    private final JComboBox<Cargo> cargoBuscar = new JComboBox<>(Cargo.values());
    private final JTextField codigoEliminar = new JTextField();
    private final JLabel mensaje = new JLabel(" ");
    private final JLabel nomina = new JLabel();
    private final JLabel promedio = new JLabel();
    private final JLabel mayor = new JLabel();
    private final JLabel cantidad = new JLabel();
    private final List<DefaultTableModel> modelos = new ArrayList<>();

    public VentanaPrincipal(Controlador controlador) {
        super("Gestión de empleados");
        this.controlador = controlador;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 700);
        setLocationRelativeTo(null);
        setContentPane(crearVentana());
        actualizarFinanzas();
        mostrarResultados(controlador.listarEmpleados());
    }

    private JPanel crearVentana() {
        JPanel principal = new JPanel(new BorderLayout(5, 5));
        principal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        principal.add(new JLabel("Sistema de gestión de empleados"), BorderLayout.NORTH);
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Gestión", crearGestion());
        pestanas.addTab("Consultas", crearConsultas());
        pestanas.addTab("Finanzas", crearFinanzas());
        pestanas.addTab("Salarios", crearSalarios());
        principal.add(pestanas, BorderLayout.CENTER);
        return principal;
    }

    private JPanel crearGestion() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel formularios = new JPanel(new GridLayout(1, 2, 5, 5));
        formularios.add(crearFormularioEmpleado());
        formularios.add(crearFormularioActualizar());
        panel.add(formularios, BorderLayout.NORTH);
        panel.add(mensaje, BorderLayout.SOUTH);
        panel.add(crearTabla(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearFormularioEmpleado() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Crear empleado"));
        panel.add(new JLabel("Código:")); panel.add(codigoCrear);
        panel.add(new JLabel("Nombre:")); panel.add(nombreCrear);
        panel.add(new JLabel("Salario:")); panel.add(salarioCrear);
        panel.add(new JLabel("Cargo:")); panel.add(cargoCrear);
        JButton crear = new JButton("Crear empleado");
        crear.addActionListener(e -> crearEmpleado());
        JButton limpiar = new JButton("Limpiar");
        limpiar.addActionListener(e -> {
            codigoCrear.setText(""); nombreCrear.setText(""); salarioCrear.setText("");
        });
        panel.add(crear); panel.add(limpiar);
        return panel;
    }

    private JPanel crearFormularioActualizar() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Actualizar salario"));
        panel.add(new JLabel("Código:")); panel.add(codigoActualizar);
        panel.add(new JLabel("Nuevo salario:")); panel.add(salarioActualizar);
        JButton actualizar = new JButton("Actualizar");
        actualizar.addActionListener(e -> actualizarSalario());
        JButton limpiar = new JButton("Limpiar");
        limpiar.addActionListener(e -> {
            codigoActualizar.setText(""); salarioActualizar.setText("");
        });
        panel.add(actualizar); panel.add(limpiar);
        return panel;
    }

    private JPanel crearConsultas() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel consultas = new JPanel(new GridLayout(2, 2, 5, 5));
        consultas.add(crearBusquedaCodigo());
        consultas.add(crearBusquedaNombre());
        consultas.add(crearBusquedaCargo());
        consultas.add(crearEliminacion());
        panel.add(consultas, BorderLayout.NORTH);
        panel.add(crearTabla(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearBusquedaCodigo() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Buscar por código"));
        panel.add(new JLabel("Código:")); panel.add(codigoBuscar);
        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(e -> {
            try {
                mostrarResultados(Collections.singletonList(controlador.buscarPorCodigo(Integer.parseInt(codigoBuscar.getText().trim()))));
            } catch (RuntimeException ex) { mensaje.setText(ex.getMessage()); }
        });
        panel.add(buscar);
        return panel;
    }

    private JPanel crearBusquedaNombre() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Buscar por nombre"));
        panel.add(new JLabel("Nombre contiene:")); panel.add(nombreBuscar);
        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(e -> {
            String texto = nombreBuscar.getText().trim().toLowerCase();
            List<Empleado> lista = new ArrayList<>();
            for (Empleado empleado : controlador.listarEmpleados()) {
                if (!texto.isEmpty() && empleado.getNombre().toLowerCase().contains(texto)) lista.add(empleado);
            }
            mostrarResultados(lista);
        });
        panel.add(buscar);
        return panel;
    }

    private JPanel crearBusquedaCargo() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Buscar por cargo"));
        panel.add(new JLabel("Cargo:")); panel.add(cargoBuscar);
        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(e -> mostrarResultados(controlador.listarPorCargo((Cargo) cargoBuscar.getSelectedItem())));
        JButton todos = new JButton("Listar todos");
        todos.addActionListener(e -> mostrarResultados(controlador.listarEmpleados()));
        panel.add(buscar); panel.add(todos);
        return panel;
    }

    private JPanel crearEliminacion() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Eliminar empleado"));
        panel.add(new JLabel("Código:")); panel.add(codigoEliminar);
        JButton eliminar = new JButton("Eliminar");
        eliminar.addActionListener(e -> eliminarEmpleado());
        panel.add(eliminar);
        return panel;
    }

    private JPanel crearFinanzas() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel datos = new JPanel(new GridLayout(0, 2, 5, 5));
        datos.setBorder(BorderFactory.createTitledBorder("Resumen financiero"));
        datos.add(new JLabel("Nómina total:")); datos.add(nomina);
        datos.add(new JLabel("Promedio salarial:")); datos.add(promedio);
        datos.add(new JLabel("Mayor salario:")); datos.add(mayor);
        datos.add(new JLabel("Cantidad de empleados:")); datos.add(cantidad);
        panel.add(datos, BorderLayout.NORTH);
        panel.add(crearTabla(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearSalarios() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel botones = new JPanel(new GridLayout(0, 1, 5, 5));
        botones.setBorder(BorderFactory.createTitledBorder("Consultas de salarios"));
        JButton sobrePromedio = new JButton("Empleados sobre el promedio");
        sobrePromedio.addActionListener(e -> mostrarResultados(controlador.listarQueSuperanPromedio()));
        JButton minimo = new JButton("Empleados con salario mínimo");
        minimo.addActionListener(e -> mostrarResultados(controlador.listarGananMinimo()));
        JButton todos = new JButton("Todos los empleados");
        todos.addActionListener(e -> mostrarResultados(controlador.listarEmpleados()));
        botones.add(sobrePromedio); botones.add(minimo); botones.add(todos);
        panel.add(botones, BorderLayout.NORTH);
        panel.add(crearTabla(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearTabla() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Cargo", "Salario"}, 0) {
            @Override public boolean isCellEditable(int fila, int columna) { return false; }
        };
        modelos.add(modelo);
        JTable tabla = new JTable(modelo);
        return new JPanel(new BorderLayout()) {{ add(new JScrollPane(tabla), BorderLayout.CENTER); }};
    }

    private void crearEmpleado() {
        try {
            controlador.registrarEmpleado(nombreCrear.getText().trim(), Integer.parseInt(codigoCrear.getText().trim()),
                    Float.parseFloat(salarioCrear.getText().trim()), (Cargo) cargoCrear.getSelectedItem());
            mensaje.setText("Empleado creado."); mostrarResultados(controlador.listarEmpleados()); actualizarFinanzas();
        } catch (RuntimeException ex) { mensaje.setText(ex.getMessage()); }
    }

    private void actualizarSalario() {
        try {
            controlador.actualizarSalario(Integer.parseInt(codigoActualizar.getText().trim()),
                    Float.parseFloat(salarioActualizar.getText().trim()));
            mensaje.setText("Salario actualizado."); mostrarResultados(controlador.listarEmpleados()); actualizarFinanzas();
        } catch (RuntimeException ex) { mensaje.setText(ex.getMessage()); }
    }

    private void eliminarEmpleado() {
        try {
            int codigo = Integer.parseInt(codigoEliminar.getText().trim());
            Empleado empleado = controlador.buscarPorCodigo(codigo);
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Eliminar a " + empleado.getNombre() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                controlador.eliminarEmpleado(codigo); mensaje.setText("Empleado eliminado.");
                mostrarResultados(controlador.listarEmpleados()); actualizarFinanzas();
            }
        } catch (RuntimeException ex) { mensaje.setText(ex.getMessage()); }
    }

    private void actualizarFinanzas() {
        nomina.setText(String.valueOf(controlador.calcularValorNomina()));
        promedio.setText(String.valueOf(controlador.calcularPromedioSalarios()));
        Empleado empleado = controlador.obtenerEmpleadoQueMasGana();
        mayor.setText(empleado == null ? "0" : String.valueOf(empleado.getSalario()));
        cantidad.setText(String.valueOf(controlador.listarEmpleados().size()));
    }

    private void mostrarResultados(List<Empleado> empleados) {
        for (DefaultTableModel modelo : modelos) {
            modelo.setRowCount(0);
            for (Empleado empleado : empleados) {
                modelo.addRow(new Object[]{empleado.getCodigo(), empleado.getNombre(), empleado.getCargo(), empleado.getSalario()});
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal(new Controlador()).setVisible(true));
    }
}
