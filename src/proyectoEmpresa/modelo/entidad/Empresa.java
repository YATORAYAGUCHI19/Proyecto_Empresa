package proyectoEmpresa.modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import proyectoEmpresa.modelo.enums.Cargo;
import proyectoEmpresa.modelo.estructura.ArbolBB;
import proyectoEmpresa.modelo.estructura.NodoABB;

public class Empresa implements Serializable {

    private ArbolBB<Empleado> empleados;

    public Empresa() {
        this.empleados = new ArbolBB<>();
    }

    // 1. Registrar
    public void registrarEmpleado(Empleado e) {
        if (e == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        }
        try {
            empleados.insertar(e);
        } catch (IllegalArgumentException ex) {
            if (e != null && e.getCodigo() != null && empleados.buscar(e) != null) {
                throw new IllegalArgumentException("Ya existe un empleado con el código " + e.getCodigo());
            }
            throw ex;
        }
    }

    // 2. Listar todos
    public List<Empleado> listarEmpleados() {
        return empleados.listarInorden();
    }

    // 3. Dado código decir empleado
    public Empleado buscarPorCodigo(Integer codigo) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException(
                    "El código del empleado no puede ser null y debe ser mayor que 0"
            );
        }
        NodoABB<Empleado> nodo = empleados.buscar(new Empleado(codigo));
        if (nodo == null) {
            throw new IllegalArgumentException("No existe ningún empleado con el código: " + codigo);
        }
        return nodo.dato;
    }

    // 4. Dado nombre decir empleado
    public List<Empleado> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");
        }
        List<Empleado> encontrados = new ArrayList<>();
        if (nombre == null) {
            return encontrados;
        }
        for (Empleado e : empleados.listarInorden()) {
            if (e.getNombre() != null && e.getNombre().equalsIgnoreCase(nombre.trim())) {
                encontrados.add(e);
            }
        }
        return encontrados;
    }

    // 5. Borrar por código
    public void eliminarEmpleado(Integer codigo) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException(
                    "El código del empleado no puede ser null y debe ser mayor que 0"
            );
        }
        if (!empleados.eliminar(new Empleado(codigo))) {
            throw new IllegalArgumentException("No se encontró el empleado a eliminar");
        }
    }

    // 6. Listar por cargo
    public List<Empleado> listarPorCargo(Cargo cargo) {
        if (cargo == null) {
            throw new IllegalArgumentException("El cargo no puede ser nulo");
        }
        List<Empleado> todos = empleados.listarInorden();
        List<Empleado> filtrados = new ArrayList<>();
        for (Empleado e : todos) {
            if (e.getCargo() == cargo) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }

    // 7. Valor de la nómina
    public float calcularValorNomina() {
        float total = 0;
        for (Empleado e : empleados.listarInorden()) {
            total += e.getSalario();
        }
        return total;
    }

    // 8. Empleado que más gana
    public Empleado obtenerEmpleadoQueMasGana() {
        List<Empleado> todos = empleados.listarInorden();
        if (todos.isEmpty()) {
            return null;
        }
        Empleado max = todos.get(0);
        for (Empleado e : todos) {
            if (e.getSalario() > max.getSalario()) {
                max = e;
            }
        }
        return max;
    }

    // 9. Promedio de salarios
    public float calcularPromedioSalarios() {
        List<Empleado> todos = empleados.listarInorden();
        if (todos.isEmpty()) {
            return 0;
        }
        float total = 0;
        for (Empleado e : todos) {
            total += e.getSalario();
        }
        return total / todos.size();
    }

    // 10. Empleados que superan el promedio
    public List<Empleado> listarEmpleadosQueSuperanPromedio() {
        List<Empleado> todos = empleados.listarInorden();
        float promedio = calcularPromedioSalarios();
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado e : todos) {
            if (e.getSalario() > promedio) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // 11. Empleados que ganan el mínimo
    public List<Empleado> listarEmpleadosGananMinimo() {
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado e : empleados.listarInorden()) {
            if (e.getSalario() == Empleado.SALARIO_MINIMO) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // 12. Actualizar salario por código
    public void actualizarSalario(Integer codigo, float nuevoSalario) {
        Empleado e = buscarPorCodigo(codigo);
        e.setSalario(nuevoSalario);
    }
}
