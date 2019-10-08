package ebc17;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class App 
{
    public static void main( String[] args ) throws IOException, FileNotFoundException, ParseException
    {
        Cubo cubo = new Cubo (3);
        
        System.out.println("El identificador inical del cubo es de "+cubo.getID());
        cubo.mostrarCara("FRONT");
        cubo.moveL(0);
        cubo.moveB(2);
        cubo.movel(1);
        cubo.moveb(1);
        System.out.println("El identificador despues de realizar una serie de movimientos es de "+cubo.getID());
        cubo.mostrarCara("FRONT");
        cubo.moveB(1);
        cubo.moveL(1);
        cubo.moveb(2);
        cubo.movel(0);
        
               
        System.out.println("El identificador despues de realizar los movimientos es de "+cubo.getID());
        cubo.mostrarCara("FRONT");
        
    }
}
