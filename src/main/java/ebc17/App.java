package ebc17;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class App 
{
    public static void main( String[] args ) throws IOException, FileNotFoundException, ParseException
    {
        Cubo cubo = new Cubo (3);
       cubo.mostrarCara("LEFT");
        
    }
}
