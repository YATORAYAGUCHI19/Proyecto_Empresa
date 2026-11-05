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

    public ArbolBB<Empleado> getEmpleados() {
        return empleados;
    }

    public boolean registrarEmpleado(Empleado e) {
        try {
            empleados.insertar(e);
            return true;
        } catch (Exception x) {
            return false;
        }
    }

    public List<Empleado> listarEmpleados() {
        return empleados.listarInorden();
    }

    public Empleado buscarPorCodigo(Integer codigo) {
        Empleado buscador = new Empleado(codigo);
        NodoABB<Empleado> nodo = empleados.buscar(empleados.getRaiz(), buscador);
        if (nodo ==  null) {
            throw new IllegalArgumentException("No existe ningun empleado con el codigo: " + codigo);
        }
        return nodo.dato;
    }

    //DESDE AQUI ESTA BUENO Y REVISADO, DE AQUI SIGUE CON EL MONITOR
    // 4. Dado nombre decir empleado
    public List<Empleado> buscarPorNombre(String nombre) {
        List<Empleado> todos = empleados.listarInorden();
        List<Empleado> encontrados = new ArrayList<>();
        for (Empleado e : todos) {
            if (e.getNombre().equalsIgnoreCase(nombre.trim())) {
                encontrados.add(e);
            }
        }
        return encontrados;
    }

    // 5. Dado código de 1 empleado borrarlo
    public boolean eliminarEmpleado(Integer codigo) {
        Empleado buscador = new Empleado(codigo);
        boolean elimino = empleados.eliminar(buscador);
        if (elimino) {
            System.out.println("El empleado fue eliminado de manera correcta");
            return true;
        }
        throw new IllegalArgumentException("No se encontro el empleado a eliminar");
    }

    // 6. Listar todos los empleados de Cargo X
    public List<Empleado> listarPorCargo(Cargo cargo) {
        List<Empleado> todos = empleados.listarInorden();
        List<Empleado> filtrados = new ArrayList<>();
        for (Empleado e : todos) {
            if (e.getCargo() == cargo) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }

    // 7. Valor de la nómina (sumar salarios)
    public float calcularValorNomina() {
        List<Empleado> todos = empleados.listarInorden();
        float total = 0;
        for (Empleado e : todos) {
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
        return calcularValorNomina() / todos.size();
    }

    // 10. Listado de empleados que superan el promedio
    public List<Empleado> listarEmpleadosQueSuperanPromedio() {
        float promedio = calcularPromedioSalarios();
        List<Empleado> todos = empleados.listarInorden();
        List<Empleado> mayorPromedio = new ArrayList<>();
        for (Empleado e : todos) {
            if (e.getSalario() > promedio) {
                mayorPromedio.add(e);
            }
        }
        return mayorPromedio;
    }

    // 11. Hallar los empleados que ganan el mínimo
    public List<Empleado> listarEmpleadosGananMinimo() {
        List<Empleado> todos = empleados.listarInorden();
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado e : todos) {
            if (e.getSalario() == 2000000) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // 12. Dado el código de un empleado actualizar salario
    public boolean actualizarSalario(Integer codigo, float nuevoSalario) {
        Empleado e = buscarPorCodigo(codigo);
        if (e != null) {
            e.setSalario(nuevoSalario);
            return true;
        }
        return false;
    }
}
