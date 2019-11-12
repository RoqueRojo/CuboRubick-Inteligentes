package ebc17;

import java.util.PriorityQueue;

public class Frontera {

    private PriorityQueue<NodoArbol> colaNodos = new PriorityQueue<NodoArbol>();

    public Frontera() {
    }

    public void CrearFronteraVacia() {
        colaNodos.clear();
    }

    public void Insertar(NodoArbol n) {
        colaNodos.add(n);
    }

    public NodoArbol Eliminar() {
        return colaNodos.remove();
    }

    public boolean EstaVacia() {
        return colaNodos.isEmpty();
    }

}
