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
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;
import proyectoEmpresa.modelo.enums.Cargo;

/** Interfaz completa de la aplicaciÃ³n en una sola clase Swing bÃ¡sica. */
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
    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"CÃ³digo", "Nombre", "Cargo", "Salario"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);

    public VentanaPrincipal(Controlador controlador) {
        super("GestiÃ³n de empleados");
        this.controlador = controlador;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setContentPane(crearVentana());
        actualizarFinanzas();
        mostrarResultados(controlador.listarEmpleados());
    }

    private JPanel crearVentana() {
        JPanel principal = new JPanel(new BorderLayout(5, 5));
        principal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        principal.add(new JLabel("Sistema de gestiÃ³n de empleados"), BorderLayout.NORTH);

        JTabbedPane pestaÃ±as = new JTabbedPane();
        pestaÃ±as.addTab("GestiÃ³n", crearGestion());
        pestaÃ±as.addTab("Consultas", crearConsultas());
        pestaÃ±as.addTab("Finanzas", crearFinanzas());
        pestaÃ±as.addTab("Salarios", crearSalarios());
        pestaÃ±as.addTab("Resultados", crearResultados());
        pestaÃ±as.addChangeListener(e -> {
            actualizarFinanzas();
            if (pestaÃ±as.getSelectedIndex() == 4) {
                mostrarResultados(controlador.listarEmpleados());
            }
        });
        principal.add(pestaÃ±as, BorderLayout.CENTER);
        return principal;
    }

    private JPanel crearGestion() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel formularios = new JPanel(new GridLayout(1, 2, 5, 5));
        formularios.add(crearFormularioEmpleado());
        formularios.add(crearFormularioActualizar());
        panel.add(formularios, BorderLayout.NORTH);
        panel.add(mensaje, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearFormularioEmpleado() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Crear empleado"));
        panel.add(new JLabel("CÃ³digo:"));
        panel.add(codigoCrear);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreCrear);
        panel.add(new JLabel("Salario:"));
        panel.add(salarioCrear);
        panel.add(new JLabel("Cargo:"));
        panel.add(cargoCrear);
        JButton crear = new JButton("Crear empleado");
        crear.addActionListener(e -> crearEmpleado());
        JButton limpiar = new JButton("Limpiar");
        limpiar.addActionListener(e -> {
            codigoCrear.setText("");
            nombreCrear.setText("");
            salarioCrear.setText("");
            mensaje.setText("Campos limpiados.");
        });
        panel.add(crear);
        panel.add(limpiar);
        return panel;
    }

    private JPanel crearFormularioActualizar() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Actualizar salario"));
        panel.add(new JLabel("CÃ³digo:"));
        panel.add(codigoActualizar);
        panel.add(new JLabel("Nuevo salario:"));
        panel.add(salarioActualizar);
        JButton actualizar = new JButton("Actualizar");
        actualizar.addActionListener(e -> actualizarSalario());
        JButton limpiar = new JButton("Limpiar");
        limpiar.addActionListener(e -> {
            codigoActualizar.setText("");
            salarioActualizar.setText("");
            mensaje.setText("Campos limpiados.");
        });
        panel.add(actualizar);
        panel.add(limpiar);
        return panel;
    }

    private JPanel crearConsultas() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(crearBusquedaCodigo());
        panel.add(crearBusquedaNombre());
        panel.add(crearBusquedaCargo());
        panel.add(crearEliminacion());
        return panel;
    }

    private JPanel crearBusquedaCodigo() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Buscar por cÃ³digo"));
        panel.add(new JLabel("CÃ³digo:"));
        panel.add(codigoBuscar);
        JButton boton = new JButton("Buscar");
        boton.addActionListener(e -> {
            try {
                mostrarResultados(Collections.singletonList(controlador.buscarPorCodigo(Integer.parseInt(codigoBuscar.getText().trim()))));
            } catch (NumberFormatException ex) {
                mostrarResultados(Collections.<Empleado>emptyList());
                mensaje.setText(ex.getMessage());
            }
        });
        panel.add(boton);
        return panel;
    }

    private JPanel crearBusquedaNombre() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Buscar por nombre"));
        panel.add(new JLabel("Nombre contiene:"));
        panel.add(nombreBuscar);
        JButton boton = new JButton("Buscar");
        boton.addActionListener(e -> {
            String texto = nombreBuscar.getText().trim().toLowerCase();
            List<Empleado> lista = new ArrayList<>();
            for (Empleado empleado : controlador.listarEmpleados()) {
                if (!texto.isEmpty() && empleado.getNombre().toLowerCase().contains(texto)) {
                    lista.add(empleado);
                }
            }
            mostrarResultados(lista);
        });
        panel.add(boton);
        return panel;
    }

    private JPanel crearBusquedaCargo() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Buscar por cargo"));
        panel.add(new JLabel("Cargo:"));
        panel.add(cargoBuscar);
        JButton boton = new JButton("Buscar");
        boton.addActionListener(e -> mostrarResultados(controlador.listarPorCargo((Cargo) cargoBuscar.getSelectedItem())));
        panel.add(boton);
        JButton todos = new JButton("Listar todos");
        todos.addActionListener(e -> mostrarResultados(controlador.listarEmpleados()));
        panel.add(todos);
        return panel;
    }

    private JPanel crearEliminacion() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Eliminar empleado"));
        panel.add(new JLabel("CÃ³digo:"));
        panel.add(codigoEliminar);
        JButton boton = new JButton("Eliminar");
        boton.addActionListener(e -> eliminarEmpleado());
        panel.add(boton);
        return panel;
    }

    private JPanel crearFinanzas() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Resumen financiero"));
        panel.add(new JLabel("NÃ³mina total:"));
        panel.add(nomina);
        panel.add(new JLabel("Promedio salarial:"));
        panel.add(promedio);
        panel.add(new JLabel("Mayor salario:"));
        panel.add(mayor);
        panel.add(new JLabel("Cantidad de empleados:"));
        panel.add(cantidad);
        return panel;
    }

    private JPanel crearSalarios() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Consultas de salarios"));
        JButton sobrePromedio = new JButton("Empleados sobre el promedio");
        sobrePromedio.addActionListener(e -> mostrarResultados(controlador.listarQueSuperanPromedio()));
        JButton minimo = new JButton("Empleados con salario mÃ­nimo");
        minimo.addActionListener(e -> mostrarResultados(controlador.listarGananMinimo()));
        JButton todos = new JButton("Todos los empleados");
        todos.addActionListener(e -> mostrarResultados(controlador.listarEmpleados()));
        panel.add(sobrePromedio);
        panel.add(minimo);
        panel.add(todos);
        return panel;
    }

    private JPanel crearResultados() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    private void crearEmpleado() {
        try {
            controlador.registrarEmpleado(nombreCrear.getText().trim(), Integer.parseInt(codigoCrear.getText().trim()),
                    Float.parseFloat(salarioCrear.getText().trim()), (Cargo) cargoCrear.getSelectedItem());
            mensaje.setText("Empleado creado.");
            mostrarResultados(controlador.listarEmpleados());
            actualizarFinanzas();
        } catch (NumberFormatException ex) {
            mensaje.setText(ex.getMessage());
        }
    }

    private void actualizarSalario() {
        try {
            controlador.actualizarSalario(Integer.parseInt(codigoActualizar.getText().trim()),
                    Float.parseFloat(salarioActualizar.getText().trim()));
            mensaje.setText("Salario actualizado.");
            mostrarResultados(controlador.listarEmpleados());
            actualizarFinanzas();
        } catch (NumberFormatException ex) {
            mensaje.setText(ex.getMessage());
        }
    }

    private void eliminarEmpleado() {
        try {
            int codigo = Integer.parseInt(codigoEliminar.getText().trim());
            Empleado empleado = controlador.buscarPorCodigo(codigo);
            int respuesta = JOptionPane.showConfirmDialog(this, "Â¿Eliminar a " + empleado.getNombre() + "?",
                    "Confirmar eliminaciÃ³n", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                controlador.eliminarEmpleado(codigo);
                mensaje.setText("Empleado eliminado.");
                mostrarResultados(controlador.listarEmpleados());
                actualizarFinanzas();
            }
        } catch (NumberFormatException ex) {
            mensaje.setText(ex.getMessage());
        }
    }

    private void actualizarFinanzas() {
        nomina.setText(String.valueOf(controlador.calcularValorNomina()));
        promedio.setText(String.valueOf(controlador.calcularPromedioSalarios()));
        Empleado empleado = controlador.obtenerEmpleadoQueMasGana();
        mayor.setText(empleado == null ? "0" : String.valueOf(empleado.getSalario()));
        cantidad.setText(String.valueOf(controlador.listarEmpleados().size()));
    }

    private void mostrarResultados(List<Empleado> empleados) {
        modeloTabla.setRowCount(0);
        for (Empleado empleado : empleados) {
            modeloTabla.addRow(new Object[]{empleado.getCodigo(), empleado.getNombre(), empleado.getCargo(), empleado.getSalario()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal(new Controlador()).setVisible(true));
    }
}
