
package ebc17;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;


public class EspacioDeEstados {
    private Estado estado;
    public EspacioDeEstados(String fileJSON) throws IOException, FileNotFoundException, ParseException{
        estado = new Estado(fileJSON);
    }
    
    public List<Sucesor> getSucesores(Estado estado){ //metodo que obtiene una lista de los sucesores a partir de cierto estado.
        List<Sucesor> sucesores = new ArrayList<Sucesor>();
        List<Accion> acciones = new ArrayList<Accion>(estado.getAcciones());
        for(Accion accion : acciones){
            sucesores.add(new Sucesor (accion,estado.getEstado(accion),1));
        }
        return sucesores;    
    }

    public Estado getEstado() {
        return estado;
    }
    
    
    
}

