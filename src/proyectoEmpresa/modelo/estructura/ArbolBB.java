package proyectoEmpresa.modelo.estructura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArbolBB<E extends Comparable<E>> implements Serializable {

    private NodoABB<E> raiz;

    public NodoABB<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoABB<E> raiz) {
        this.raiz = raiz;
    }

    public void insertar(E x) {
        if (raiz == null) {
            raiz = new NodoABB<>(x);
        } else {
            insertar(raiz, x);
        }
    }

    private void insertar(NodoABB<E> r, E x) {
        if (x.compareTo(r.dato) < 0) { //menores
            if (r.izq == null) {
                r.izq = new NodoABB<>(x);
            } else {
                insertar(r.izq, x);
            }
        } else if (x.compareTo(r.dato) > 0) {
            if (r.der == null) {
                r.der = new NodoABB<>(x);
            } else {
                insertar(r.der, x);
            }
        } else {
            throw new IllegalArgumentException("No es posible insertar - Datos iguales");
        }

    }

    @Override
    public String toString() {
        String lis = "";
        lis = inorden(raiz);
        return lis;
    }

    private String inorden(NodoABB<E> r) {
        if (r == null) {
            return "";
        } else {
            return inorden(r.izq) + " " + r.dato.toString() + " " + inorden(r.der);
        }
    }

    private E mayor(NodoABB<E> r) {
        if (r.der == null) {
            return r.dato;
        } else {
            return mayor(r.der);
        }
    }

    public NodoABB<E> buscar(E x) {
        return buscar(raiz, x);
    }

    private NodoABB<E> buscar(NodoABB<E> r, E x) {
        if (r == null) {
            return null;
        } else {
            if (r.dato.compareTo(x) == 0) {
                return r;
            }
            if (x.compareTo(r.dato) < 0) {
                return buscar(r.izq, x);
            } else {
                return buscar(r.der, x);
            }
        }
    }

    public boolean eliminar(E x) {
        if (raiz == null) {
            return false;
        }
        if (buscar(x) != null) {
            raiz = eliminar(raiz, x);
            return true;
        }
        return false;
    }

    private NodoABB<E> eliminar(NodoABB<E> r, E x) {
        if (r.dato.compareTo(x) == 0) {
            return borrar(r);
        } else {
            if (x.compareTo(r.dato) < 0) {
                r.izq = eliminar(r.izq, x);
            } else {
                r.der = eliminar(r.der, x);
            }
            return r;
        }
    }

    private NodoABB<E> borrar(NodoABB<E> r) {
        if (r.izq == null && r.der == null) {
            return null;
        } else if (r.izq == null) {
            return r.der;
        } else {
            if (r.der == null) {
                return r.izq;
            } else {
                E dato = mayor(r.izq);
                r.dato = dato;
                r.izq = eliminar(r.izq, dato);
                return r;
            }
        }
    }

    public List<E> listarInorden() {
        List<E> lista = new ArrayList<>();
        listarInorden(raiz, lista);
        return lista;
    }

    private void listarInorden(NodoABB<E> r, List<E> lista) {
        if (r != null) {
            listarInorden(r.izq, lista);
            lista.add(r.dato);
            listarInorden(r.der, lista);
        }
    }
}
