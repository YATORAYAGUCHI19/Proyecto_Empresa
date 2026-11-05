package proyectoEmpresa.controlador;

import proyectoEmpresa.modelo.entidad.Empleado;
import proyectoEmpresa.modelo.entidad.Empresa;
import proyectoEmpresa.modelo.enums.Cargo;

public class Controlador {
    
    private Empresa empresa = new Empresa();
    
    public void registrarEmpleado(String nombre, Integer codigo, float salario, Cargo cargo){
        
        Empleado nuevo = new Empleado(nombre,codigo,salario,cargo);
        empresa.registrarEmpleado(nuevo);
        System.out.println(nuevo);
        System.out.println("proyectoEmpresa.controlador.Controlador.registrarEmpleado()");
    }
    
    public void actualizarSalario(float salario, Integer codigo){
        empresa.actualizarSalario(codigo, salario);
    }
}
