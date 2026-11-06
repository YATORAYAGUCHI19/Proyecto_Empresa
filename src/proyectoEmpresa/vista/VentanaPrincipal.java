package proyectoEmpresa.vista;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import proyectoEmpresa.controlador.Controlador;
import proyectoEmpresa.modelo.entidad.Empleado;
import proyectoEmpresa.modelo.enums.Cargo;

/**
 * Ventana principal generada con el formulario de NetBeans.
 */
public class VentanaPrincipal extends JFrame {

    private final Controlador controlador;

    public VentanaPrincipal(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
        actualizarFinanzas();
        mostrarResultados(controlador.listarEmpleados());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        panelPrincipal = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        pestanas = new javax.swing.JTabbedPane();
        panelGestion = new javax.swing.JPanel();
        panelCrear = new javax.swing.JPanel();
        etiquetaCodigoCrear = new javax.swing.JLabel();
        codigoCrear = new javax.swing.JTextField();
        etiquetaNombreCrear = new javax.swing.JLabel();
        nombreCrear = new javax.swing.JTextField();
        etiquetaSalarioCrear = new javax.swing.JLabel();
        salarioCrear = new javax.swing.JTextField();
        etiquetaCargoCrear = new javax.swing.JLabel();
        cargoCrear = new javax.swing.JComboBox<>();
        botonCrear = new javax.swing.JButton();
        botonLimpiarCrear = new javax.swing.JButton();
        panelActualizar = new javax.swing.JPanel();
        etiquetaCodigoActualizar = new javax.swing.JLabel();
        codigoActualizar = new javax.swing.JTextField();
        etiquetaSalarioActualizar = new javax.swing.JLabel();
        salarioActualizar = new javax.swing.JTextField();
        botonActualizar = new javax.swing.JButton();
        botonLimpiarActualizar = new javax.swing.JButton();
        scrollGestion = new javax.swing.JScrollPane();
        tablaGestion = new javax.swing.JTable();
        panelConsultas = new javax.swing.JPanel();
        panelBusquedaCodigo = new javax.swing.JPanel();
        etiquetaCodigoBuscar = new javax.swing.JLabel();
        codigoBuscar = new javax.swing.JTextField();
        botonBuscarCodigo = new javax.swing.JButton();
        panelBusquedaNombre = new javax.swing.JPanel();
        etiquetaNombreBuscar = new javax.swing.JLabel();
        nombreBuscar = new javax.swing.JTextField();
        botonBuscarNombre = new javax.swing.JButton();
        panelBusquedaCargo = new javax.swing.JPanel();
        etiquetaCargoBuscar = new javax.swing.JLabel();
        cargoBuscar = new javax.swing.JComboBox<>();
        botonBuscarCargo = new javax.swing.JButton();
        botonListarTodos = new javax.swing.JButton();
        panelEliminar = new javax.swing.JPanel();
        etiquetaCodigoEliminar = new javax.swing.JLabel();
        codigoEliminar = new javax.swing.JTextField();
        botonEliminar = new javax.swing.JButton();
        scrollConsultas = new javax.swing.JScrollPane();
        tablaConsultas = new javax.swing.JTable();
        panelFinanzas = new javax.swing.JPanel();
        panelIndicadores = new javax.swing.JPanel();
        etiquetaNomina = new javax.swing.JLabel();
        nomina = new javax.swing.JLabel();
        etiquetaPromedio = new javax.swing.JLabel();
        promedio = new javax.swing.JLabel();
        etiquetaMayor = new javax.swing.JLabel();
        mayor = new javax.swing.JLabel();
        etiquetaCantidad = new javax.swing.JLabel();
        cantidad = new javax.swing.JLabel();
        scrollFinanzas = new javax.swing.JScrollPane();
        tablaFinanzas = new javax.swing.JTable();
        panelSalarios = new javax.swing.JPanel();
        botonSobrePromedio = new javax.swing.JButton();
        botonSalarioMinimo = new javax.swing.JButton();
        botonTodosSalarios = new javax.swing.JButton();
        scrollSalarios = new javax.swing.JScrollPane();
        tablaSalarios = new javax.swing.JTable();
        mensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de empleados");
        setMinimumSize(new java.awt.Dimension(900, 600));

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 18));
        titulo.setText("Sistema de gestión de empleados");

        panelCrear.setBorder(javax.swing.BorderFactory.createTitledBorder("Crear empleado"));
        etiquetaCodigoCrear.setText("Código:");
        etiquetaNombreCrear.setText("Nombre:");
        etiquetaSalarioCrear.setText("Salario:");
        etiquetaCargoCrear.setText("Cargo:");
        cargoCrear.setModel(new DefaultComboBoxModel<>(Cargo.values()));
        botonCrear.setText("Crear empleado");
        botonCrear.addActionListener(this::botonCrearActionPerformed);
        botonLimpiarCrear.setText("Limpiar");
        botonLimpiarCrear.addActionListener(evt -> {
            codigoCrear.setText("");
            nombreCrear.setText("");
            salarioCrear.setText("");
        });
        javax.swing.GroupLayout panelCrearLayout = new javax.swing.GroupLayout(panelCrear);
        panelCrear.setLayout(panelCrearLayout);
        panelCrearLayout.setHorizontalGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearLayout.createSequentialGroup().addContainerGap()
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaCodigoCrear).addComponent(etiquetaNombreCrear)
                    .addComponent(etiquetaSalarioCrear).addComponent(etiquetaCargoCrear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codigoCrear).addComponent(nombreCrear).addComponent(salarioCrear).addComponent(cargoCrear, 0, 170, Short.MAX_VALUE)
                    .addGroup(panelCrearLayout.createSequentialGroup().addComponent(botonCrear).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonLimpiarCrear)))
                .addContainerGap()));
        panelCrearLayout.setVerticalGroup(panelCrearLayout.createSequentialGroup().addContainerGap()
            .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaCodigoCrear).addComponent(codigoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaNombreCrear).addComponent(nombreCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaSalarioCrear).addComponent(salarioCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaCargoCrear).addComponent(cargoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(botonCrear).addComponent(botonLimpiarCrear))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        panelActualizar.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar salario"));
        etiquetaCodigoActualizar.setText("Código:");
        etiquetaSalarioActualizar.setText("Nuevo salario:");
        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(this::botonActualizarActionPerformed);
        botonLimpiarActualizar.setText("Limpiar");
        botonLimpiarActualizar.addActionListener(evt -> {
            codigoActualizar.setText("");
            salarioActualizar.setText("");
        });
        javax.swing.GroupLayout panelActualizarLayout = new javax.swing.GroupLayout(panelActualizar);
        panelActualizar.setLayout(panelActualizarLayout);
        panelActualizarLayout.setHorizontalGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActualizarLayout.createSequentialGroup().addContainerGap()
                .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(etiquetaCodigoActualizar).addComponent(etiquetaSalarioActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(codigoActualizar).addComponent(salarioActualizar)
                    .addGroup(panelActualizarLayout.createSequentialGroup().addComponent(botonActualizar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonLimpiarActualizar)))
                .addContainerGap()));
        panelActualizarLayout.setVerticalGroup(panelActualizarLayout.createSequentialGroup().addContainerGap()
            .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaCodigoActualizar).addComponent(codigoActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaSalarioActualizar).addComponent(salarioActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(botonActualizar).addComponent(botonLimpiarActualizar))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        configurarTabla(tablaGestion);
        scrollGestion.setViewportView(tablaGestion);
        javax.swing.GroupLayout panelGestionLayout = new javax.swing.GroupLayout(panelGestion);
        panelGestion.setLayout(panelGestionLayout);
        panelGestionLayout.setHorizontalGroup(panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGestionLayout.createSequentialGroup().addContainerGap().addComponent(panelCrear, 0, 0, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(panelActualizar, 0, 0, Short.MAX_VALUE).addContainerGap())
            .addComponent(scrollGestion, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE));
        panelGestionLayout.setVerticalGroup(panelGestionLayout.createSequentialGroup()
            .addGroup(panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelCrear).addComponent(panelActualizar))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(scrollGestion, 0, 330, Short.MAX_VALUE));

        configurarBusquedaCodigo();
        configurarBusquedaNombre();
        configurarBusquedaCargo();
        configurarEliminacion();
        configurarTabla(tablaConsultas);
        scrollConsultas.setViewportView(tablaConsultas);
        javax.swing.GroupLayout consultasLayout = new javax.swing.GroupLayout(panelConsultas);
        panelConsultas.setLayout(consultasLayout);
        consultasLayout.setHorizontalGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultasLayout.createSequentialGroup().addContainerGap().addComponent(panelBusquedaCodigo, 0, 0, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(panelBusquedaNombre, 0, 0, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(panelBusquedaCargo, 0, 0, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(panelEliminar, 0, 0, Short.MAX_VALUE).addContainerGap())
            .addComponent(scrollConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE));
        consultasLayout.setVerticalGroup(consultasLayout.createSequentialGroup()
            .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelBusquedaCodigo).addComponent(panelBusquedaNombre).addComponent(panelBusquedaCargo).addComponent(panelEliminar))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(scrollConsultas, 0, 330, Short.MAX_VALUE));

        configurarFinanzas();
        configurarTabla(tablaFinanzas);
        scrollFinanzas.setViewportView(tablaFinanzas);
        javax.swing.GroupLayout finanzasLayout = new javax.swing.GroupLayout(panelFinanzas);
        panelFinanzas.setLayout(finanzasLayout);
        finanzasLayout.setHorizontalGroup(finanzasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelIndicadores, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE).addComponent(scrollFinanzas, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE));
        finanzasLayout.setVerticalGroup(finanzasLayout.createSequentialGroup().addComponent(panelIndicadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(scrollFinanzas, 0, 380, Short.MAX_VALUE));

        botonSobrePromedio.setText("Empleados sobre el promedio");
        botonSobrePromedio.addActionListener(evt -> mostrarResultados(controlador.listarQueSuperanPromedio()));
        botonSalarioMinimo.setText("Empleados con salario mínimo");
        botonSalarioMinimo.addActionListener(evt -> mostrarResultados(controlador.listarGananMinimo()));
        botonTodosSalarios.setText("Todos los empleados");
        botonTodosSalarios.addActionListener(evt -> mostrarResultados(controlador.listarEmpleados()));
        configurarTabla(tablaSalarios);
        scrollSalarios.setViewportView(tablaSalarios);
        javax.swing.GroupLayout salariosLayout = new javax.swing.GroupLayout(panelSalarios);
        panelSalarios.setLayout(salariosLayout);
        salariosLayout.setHorizontalGroup(salariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salariosLayout.createSequentialGroup().addContainerGap().addComponent(botonSobrePromedio).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonSalarioMinimo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonTodosSalarios).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scrollSalarios, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE));
        salariosLayout.setVerticalGroup(salariosLayout.createSequentialGroup().addGroup(salariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(botonSobrePromedio).addComponent(botonSalarioMinimo).addComponent(botonTodosSalarios)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(scrollSalarios, 0, 450, Short.MAX_VALUE));

        pestanas.addTab("Gestión", panelGestion);
        pestanas.addTab("Consultas", panelConsultas);
        pestanas.addTab("Finanzas", panelFinanzas);
        pestanas.addTab("Salarios", panelSalarios);
        javax.swing.GroupLayout principalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(principalLayout);
        principalLayout.setHorizontalGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(principalLayout.createSequentialGroup().addContainerGap().addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pestanas).addComponent(titulo).addComponent(mensaje)).addContainerGap()));
        principalLayout.setVerticalGroup(principalLayout.createSequentialGroup().addContainerGap().addComponent(titulo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pestanas, 0, 530, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(mensaje).addContainerGap());
        setContentPane(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>

    private void configurarTabla(javax.swing.JTable tabla) {
        tabla.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Código", "Nombre", "Cargo", "Salario"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tabla.setAutoCreateRowSorter(true);
    }

    private void configurarBusquedaCodigo() {
        panelBusquedaCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar por código"));
        etiquetaCodigoBuscar.setText("Código:");
        botonBuscarCodigo.setText("Buscar");
        botonBuscarCodigo.addActionListener(this::botonBuscarCodigoActionPerformed);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelBusquedaCodigo);
        panelBusquedaCodigo.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(codigoBuscar).addComponent(etiquetaCodigoBuscar).addComponent(botonBuscarCodigo)).addContainerGap()));
        layout.setVerticalGroup(layout.createSequentialGroup().addContainerGap().addComponent(etiquetaCodigoBuscar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(codigoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonBuscarCodigo).addContainerGap());
    }

    private void configurarBusquedaNombre() {
        panelBusquedaNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar por nombre"));
        etiquetaNombreBuscar.setText("Nombre contiene:");
        botonBuscarNombre.setText("Buscar");
        botonBuscarNombre.addActionListener(evt -> mostrarResultados(controlador.buscarPorNombre(nombreBuscar.getText().trim())));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelBusquedaNombre);
        panelBusquedaNombre.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(nombreBuscar).addComponent(etiquetaNombreBuscar).addComponent(botonBuscarNombre)).addContainerGap()));
        layout.setVerticalGroup(layout.createSequentialGroup().addContainerGap().addComponent(etiquetaNombreBuscar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(nombreBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonBuscarNombre).addContainerGap());
    }

    private void configurarBusquedaCargo() {
        panelBusquedaCargo.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar por cargo"));
        etiquetaCargoBuscar.setText("Cargo:");
        cargoBuscar.setModel(new DefaultComboBoxModel<>(Cargo.values()));
        botonBuscarCargo.setText("Buscar");
        botonBuscarCargo.addActionListener(evt -> mostrarResultados(controlador.listarPorCargo((Cargo) cargoBuscar.getSelectedItem())));
        botonListarTodos.setText("Listar todos");
        botonListarTodos.addActionListener(evt -> mostrarResultados(controlador.listarEmpleados()));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelBusquedaCargo);
        panelBusquedaCargo.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(cargoBuscar, 0, 150, Short.MAX_VALUE).addComponent(etiquetaCargoBuscar).addGroup(layout.createSequentialGroup().addComponent(botonBuscarCargo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonListarTodos))).addContainerGap()));
        layout.setVerticalGroup(layout.createSequentialGroup().addContainerGap().addComponent(etiquetaCargoBuscar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cargoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(botonBuscarCargo).addComponent(botonListarTodos)).addContainerGap());
    }

    private void configurarEliminacion() {
        panelEliminar.setBorder(javax.swing.BorderFactory.createTitledBorder("Eliminar empleado"));
        etiquetaCodigoEliminar.setText("Código:");
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(this::botonEliminarActionPerformed);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelEliminar);
        panelEliminar.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(codigoEliminar).addComponent(etiquetaCodigoEliminar).addComponent(botonEliminar)).addContainerGap()));
        layout.setVerticalGroup(layout.createSequentialGroup().addContainerGap().addComponent(etiquetaCodigoEliminar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(codigoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonEliminar).addContainerGap());
    }

    private void configurarFinanzas() {
        panelIndicadores.setBorder(javax.swing.BorderFactory.createTitledBorder("Indicadores financieros"));
        etiquetaNomina.setText("Nómina total:");
        etiquetaPromedio.setText("Promedio salarial:");
        etiquetaMayor.setText("Mayor salario:");
        etiquetaCantidad.setText("Cantidad de empleados:");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelIndicadores);
        panelIndicadores.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(etiquetaNomina).addComponent(etiquetaPromedio).addComponent(etiquetaMayor).addComponent(etiquetaCantidad)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(nomina).addComponent(promedio).addComponent(mayor).addComponent(cantidad)).addContainerGap(500, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaNomina).addComponent(nomina)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaPromedio).addComponent(promedio)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaMayor).addComponent(mayor)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(etiquetaCantidad).addComponent(cantidad)).addContainerGap());
    }

    private void botonCrearActionPerformed(ActionEvent evt) {
        try {
            controlador.registrarEmpleado(nombreCrear.getText().trim(), Integer.parseInt(codigoCrear.getText().trim()), Float.parseFloat(salarioCrear.getText().trim()), (Cargo) cargoCrear.getSelectedItem());
            mensaje.setText("Empleado creado.");
            refrescar();
        } catch (RuntimeException ex) {
            mensaje.setText(ex.getMessage());
        }
    }

    private void botonActualizarActionPerformed(ActionEvent evt) {
        try {
            controlador.actualizarSalario(Integer.parseInt(codigoActualizar.getText().trim()), Float.parseFloat(salarioActualizar.getText().trim()));
            mensaje.setText("Salario actualizado.");
            refrescar();
        } catch (RuntimeException ex) {
            mensaje.setText(ex.getMessage());
        }
    }

    private void botonBuscarCodigoActionPerformed(ActionEvent evt) {
        try {
            mostrarResultados(java.util.Collections.singletonList(controlador.buscarPorCodigo(Integer.parseInt(codigoBuscar.getText().trim()))));
        } catch (RuntimeException ex) {
            mensaje.setText(ex.getMessage());
        }
    }

    private void botonEliminarActionPerformed(ActionEvent evt) {
        try {
            int codigo = Integer.parseInt(codigoEliminar.getText().trim());
            Empleado empleado = controlador.buscarPorCodigo(codigo);
            if (JOptionPane.showConfirmDialog(this, "¿Eliminar a " + empleado.getNombre() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                controlador.eliminarEmpleado(codigo);
                mensaje.setText("Empleado eliminado.");
                refrescar();
            }
        } catch (RuntimeException ex) {
            mensaje.setText(ex.getMessage());
        }
    }

    private void refrescar() {
        mostrarResultados(controlador.listarEmpleados());
        actualizarFinanzas();
    }

    private void actualizarFinanzas() {
        nomina.setText(String.valueOf(controlador.calcularValorNomina()));
        promedio.setText(String.valueOf(controlador.calcularPromedioSalarios()));
        Empleado empleado = controlador.obtenerEmpleadoQueMasGana();
        mayor.setText(empleado == null ? "0" : String.valueOf(empleado.getSalario()));
        cantidad.setText(String.valueOf(controlador.listarEmpleados().size()));
    }

    private void mostrarResultados(List<Empleado> empleados) {
        actualizarTabla(tablaGestion, empleados);
        actualizarTabla(tablaConsultas, empleados);
        actualizarTabla(tablaFinanzas, empleados);
        actualizarTabla(tablaSalarios, empleados);
    }

    private void actualizarTabla(javax.swing.JTable tabla, List<Empleado> empleados) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (Empleado empleado : empleados) {
            modelo.addRow(new Object[]{empleado.getCodigo(), empleado.getNombre(), empleado.getCargo(), empleado.getSalario()});
        }
    }

    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonBuscarCargo;
    private javax.swing.JButton botonBuscarCodigo;
    private javax.swing.JButton botonBuscarNombre;
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonLimpiarActualizar;
    private javax.swing.JButton botonLimpiarCrear;
    private javax.swing.JButton botonListarTodos;
    private javax.swing.JButton botonSalarioMinimo;
    private javax.swing.JButton botonSobrePromedio;
    private javax.swing.JButton botonTodosSalarios;
    private javax.swing.JLabel cantidad;
    private javax.swing.JComboBox<Cargo> cargoBuscar;
    private javax.swing.JComboBox<Cargo> cargoCrear;
    private javax.swing.JTextField codigoActualizar;
    private javax.swing.JTextField codigoBuscar;
    private javax.swing.JTextField codigoCrear;
    private javax.swing.JTextField codigoEliminar;
    private javax.swing.JLabel etiquetaCargoBuscar;
    private javax.swing.JLabel etiquetaCargoCrear;
    private javax.swing.JLabel etiquetaCantidad;
    private javax.swing.JLabel etiquetaCodigoActualizar;
    private javax.swing.JLabel etiquetaCodigoBuscar;
    private javax.swing.JLabel etiquetaCodigoCrear;
    private javax.swing.JLabel etiquetaCodigoEliminar;
    private javax.swing.JLabel etiquetaMayor;
    private javax.swing.JLabel etiquetaNombreBuscar;
    private javax.swing.JLabel etiquetaNombreCrear;
    private javax.swing.JLabel etiquetaNomina;
    private javax.swing.JLabel etiquetaPromedio;
    private javax.swing.JLabel etiquetaSalarioActualizar;
    private javax.swing.JLabel etiquetaSalarioCrear;
    private javax.swing.JLabel mayor;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTextField nombreBuscar;
    private javax.swing.JTextField nombreCrear;
    private javax.swing.JLabel nomina;
    private javax.swing.JPanel panelActualizar;
    private javax.swing.JPanel panelBusquedaCargo;
    private javax.swing.JPanel panelBusquedaCodigo;
    private javax.swing.JPanel panelBusquedaNombre;
    private javax.swing.JPanel panelConsultas;
    private javax.swing.JPanel panelCrear;
    private javax.swing.JPanel panelEliminar;
    private javax.swing.JPanel panelFinanzas;
    private javax.swing.JPanel panelGestion;
    private javax.swing.JPanel panelIndicadores;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelSalarios;
    private javax.swing.JTabbedPane pestanas;
    private javax.swing.JLabel promedio;
    private javax.swing.JTextField salarioActualizar;
    private javax.swing.JTextField salarioCrear;
    private javax.swing.JScrollPane scrollConsultas;
    private javax.swing.JScrollPane scrollFinanzas;
    private javax.swing.JScrollPane scrollGestion;
    private javax.swing.JScrollPane scrollSalarios;
    private javax.swing.JTable tablaConsultas;
    private javax.swing.JTable tablaFinanzas;
    private javax.swing.JTable tablaGestion;
    private javax.swing.JTable tablaSalarios;
    private javax.swing.JLabel titulo;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal(new Controlador()).setVisible(true));
    }
}
