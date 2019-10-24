
package ebc17;


public class Sucesor {
    private Accion accion;
    private Estado estado;
    private double coste;

    public Sucesor(Accion accion, Estado estado, double coste) {
        this.accion = accion;
        this.estado = estado;
        this.coste = coste;
    }  
    
    public Accion getAccion() {
        return accion;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getCoste() {
        return coste;
    }
    
}
