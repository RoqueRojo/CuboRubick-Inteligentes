
package ebc17;


public class Accion {
    private enum movimientos {L,B,D};
    private char movimiento; 
    private int posicion;

    public Accion (char movimiento, int posicion){
        this.movimiento=movimiento;       
        this.posicion=posicion;
    } 

    public char getMovimiento() {
        return movimiento;
    }

    public int getPosicion() {
        return posicion;
    }

    @Override
    public String toString() {
        return "Accion:" + " movimiento= " + movimiento + ", posicion= " + posicion;
    }
    
}
