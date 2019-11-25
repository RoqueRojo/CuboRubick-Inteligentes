
package ebc17;


public class Accion {
    
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
        return movimiento+""+posicion;
    }
    
}
