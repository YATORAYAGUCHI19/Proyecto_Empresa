package proyectoEmpresa.modelo.entidad;

import java.io.Serializable;
import proyectoEmpresa.modelo.enums.Cargo;

public class Empleado implements Comparable<Empleado>, Serializable {
   private String nombre;
   private Integer codigo;
   private float salario;
   private Cargo cargo;

    public Empleado() {
    }

    public Empleado(String nombre, Integer codigo, float salario, Cargo cargo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.salario = salario;
        this.cargo = cargo;
    }

    public Empleado(Integer codigo) {
        this.codigo = codigo;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public float getSalario() {
        return salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

 public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public void setCodigo(Integer codigo) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a 0");
        }
        this.codigo = codigo;
    }

    public void setSalario(float salario) {
        if (salario < 2000000) {
            throw new IllegalArgumentException("El salario no puede ser menor a $2.000.0000");
        }
        this.salario = salario;
    }

    public void setCargo(Cargo cargo) {
        if (cargo == null) {
            throw new IllegalArgumentException("Debe seleccionar un cargo");
        }
        this.cargo = cargo;
    }

    @Override
    public int compareTo(Empleado x) {
        return this.codigo.compareTo(x.codigo);
    }

    @Override
    public String toString() {
        return "Empleado: \n" +
                "codigo = " + codigo +
                "\nnombre = " + nombre +
                "\nsalario = " + salario +
                "\ncargo = " + cargo
                ;
    }
}
