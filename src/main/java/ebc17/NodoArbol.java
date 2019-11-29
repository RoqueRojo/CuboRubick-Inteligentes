
package ebc17;


public class NodoArbol implements Comparable<NodoArbol> {
    //atributos basicos de un nodo del arbol de busqueda
    private NodoArbol padre;
    private int ID;
    private Estado estado;
    private double coste;
    private Accion accion;
    private int p;
    private double f;

    public NodoArbol(NodoArbol padre, Estado estado, double coste, Accion accion, int p, double f) {
        this.padre = padre;
        this.estado = estado;
        this.coste = coste;
        this.accion = accion;
        this.p = p;
        this.f = f;
    }

    public NodoArbol getPadre() {
        return padre;
    }

    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getID() {
        return ID;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }
    //forma en la que ordenamos el nodo de un arbol concreto segun la f y el Id (el id solo si el valor de la f coincide)
    public int compareTo(NodoArbol nodo){
        int r=0;
        if(nodo.getF()<getF()){
            r = +1;
        }else if (nodo.getF()>getF()){
            r = -1;        
        
        }else if (nodo.getF()==getF()){
            if(nodo.getID()<getID()){
                r=+1;
            }else{
                r=-1;
            }
        }
        return r;
    }

    @Override
    public String toString() {
        return "NodoArbol{" + "padre=" + padre + ", estado=" + estado + ", coste=" + coste + ", accion=" + accion + ", p=" + p + ", f=" + f + '}';
    }

}
