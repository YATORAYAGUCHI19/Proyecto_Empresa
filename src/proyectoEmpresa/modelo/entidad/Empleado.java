package proyectoEmpresa.modelo.entidad;

import java.io.Serializable;
import java.util.Objects;
import proyectoEmpresa.modelo.enums.Cargo;

public class Empleado implements Comparable<Empleado>, Serializable {

    public static final float SALARIO_MINIMO = 2000000;

    private String nombre;
    private Integer codigo;
    private float salario;
    private Cargo cargo;

    public Empleado() {
    }

    public Empleado(String nombre, Integer codigo, float salario, Cargo cargo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;

        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a 0");
        }
        this.codigo = codigo;

        if (salario < SALARIO_MINIMO) {
            throw new IllegalArgumentException("El salario no puede ser menor a $2.000.000");
        }
        this.salario = salario;

        if (cargo == null) {
            throw new IllegalArgumentException("Debe seleccionar un cargo");
        }
        this.cargo = cargo;
    }

    public Empleado(Integer codigo) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a 0");
        }
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

    public void setSalario(float salario) {
        if (salario < SALARIO_MINIMO) {
            throw new IllegalArgumentException("El salario no puede ser menor a $2.000.000");
        }
        this.salario = salario;
    }

    @Override
    public int compareTo(Empleado x) {
        return this.codigo.compareTo(x.codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Empleado)) {
            return false;
        }
        return Objects.equals(codigo, ((Empleado) o).codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Empleado: \n"
                + "codigo = " + codigo
                + "\nnombre = " + nombre
                + "\nsalario = " + salario
                + "\ncargo = " + cargo;
    }
}
