package ebc17;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class App 
{
    public static void main( String[] args ) throws IOException, FileNotFoundException, ParseException
    {
        Cubo cubo = new Cubo (10); 
      //  cubo.mostrarCara("LEFT");
//        cubo.mostrarCara("UP");
//        cubo.mostrarCara("DOWN");
//        cubo.mostrarCara("BACK");
//        cubo.mostrarCara("RIGHT");
//        cubo.mostrarCara("FRONT");
        cubo.movel(3);
        cubo.moveD(1);
        cubo.movel(1);
        cubo.moved(0);
        cubo.moveB(0);
        cubo.moveb(5);
        cubo.movel(2);
        cubo.moved(1);
        
        cubo.moveD(1);
        cubo.moveL(2);
        cubo.moveB(5);
        cubo.moveb(0);
        cubo.moveD(0);
        cubo.moveL(1);
        cubo.moved(1);
        cubo.moveL(3);
        cubo.mostrarCara("LEFT");
        cubo.mostrarCara("UP");
        cubo.mostrarCara("DOWN");
        cubo.mostrarCara("BACK");
        cubo.mostrarCara("RIGHT");
        cubo.mostrarCara("FRONT");

    }
}
