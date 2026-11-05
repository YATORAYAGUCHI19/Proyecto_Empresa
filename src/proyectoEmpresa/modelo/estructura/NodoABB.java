package proyectoEmpresa.modelo.estructura;

import java.io.Serializable;

public class NodoABB<E> implements Serializable{
    public E dato;
    public NodoABB<E> izq;
    public NodoABB<E> der;

    public NodoABB(E dato) {
        this.dato = dato;
    }
    
}
