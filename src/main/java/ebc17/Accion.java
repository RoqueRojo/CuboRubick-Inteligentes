
package ebc17;


public class Accion {
    
    private char movimiento; //tipo de movimiento
    private int posicion; // fila o columna en la que realizamos el movimiento

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
