package proyectoEmpresa.controlador;

import java.io.IOException;
import java.util.List;
import proyectoEmpresa.modelo.entidad.Empleado;
import proyectoEmpresa.modelo.entidad.Empresa;
import proyectoEmpresa.modelo.enums.Cargo;
import proyectoEmpresa.persistencia.ArchiEmpresa;

public class Controlador {

    private Empresa empresa;
    private final ArchiEmpresa archivo = new ArchiEmpresa();

    public Controlador() {
        try {
            empresa = archivo.cargarEmpresa("empresa.obj");
        } catch (IOException | ClassNotFoundException e) {
            empresa = new Empresa();
        }
    }

    private void guardar() {
        try {
            archivo.guardarEmpresa(empresa);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo guardar: " + e.getMessage());
        }
    }

    public void registrarEmpleado(String nombre, Integer codigo, float salario, Cargo cargo) {
        Empleado empleado = new Empleado(nombre, codigo, salario, cargo);
        empresa.registrarEmpleado(empleado);
        guardar();
    }

    public void actualizarSalario(Integer codigo, float nuevoSalario) {
        empresa.actualizarSalario(codigo, nuevoSalario);
        guardar();
    }

    public void eliminarEmpleado(Integer codigo) {
        empresa.eliminarEmpleado(codigo);
        guardar();
    }

    public List<Empleado> listarEmpleados() {
        return empresa.listarEmpleados();
    }

    public Empleado buscarPorCodigo(Integer codigo) {
        return empresa.buscarPorCodigo(codigo);
    }

    public List<Empleado> buscarPorNombre(String nombre) {
        return empresa.buscarPorNombre(nombre);
    }

    public List<Empleado> listarPorCargo(Cargo cargo) {
        return empresa.listarPorCargo(cargo);
    }

    public float calcularValorNomina() {
        return empresa.calcularValorNomina();
    }

    public Empleado obtenerEmpleadoQueMasGana() {
        return empresa.obtenerEmpleadoQueMasGana();
    }

    public float calcularPromedioSalarios() {
        return empresa.calcularPromedioSalarios();
    }

    public List<Empleado> listarQueSuperanPromedio() {
        return empresa.listarEmpleadosQueSuperanPromedio();
    }

    public List<Empleado> listarGananMinimo() {
        return empresa.listarEmpleadosGananMinimo();
    }
}
