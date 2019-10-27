package ebc17;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class App {
    public static void main( String[] args ) throws IOException, FileNotFoundException, ParseException{
        
        long iniciof,finf,iniciof2,finf2;
        Frontera frontera = new Frontera();
        frontera.CrearFronteraVacia(); 
        iniciof=System.nanoTime();
        for (int i=0;i<10000;i++)
            frontera.Insertar(new NodoArbol(null, null, 0, null, 0, (int) (Math.random() * 50 + 1)));
        finf=System.nanoTime();
        iniciof2=System.nanoTime();
        Frontera2 frontera2 = new Frontera2();
        frontera2.CrearFronteraVacia(); 
        for (int i=0;i<10000;i++)
            frontera2.Insertar(new NodoArbol(null, null, 0, null, 0, (int) (Math.random() * 50 + 1)));
        finf2=System.nanoTime();
        
        long duracionf=finf-iniciof;
        long duracionf2=finf2-iniciof2;
        System.out.println(duracionf +"  "+duracionf2);

        
    }
}
