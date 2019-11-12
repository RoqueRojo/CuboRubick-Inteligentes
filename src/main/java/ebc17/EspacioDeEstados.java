
package ebc17;

import java.util.ArrayList;
import java.util.List;


public class EspacioDeEstados {
    //TOMA COMO ENTRADA EL NOMBRE DEL FICHERO JSON
    public EspacioDeEstados(){}
    
    public List<Sucesor> getSucesores(Estado estado){ //metodo que obtiene una lista de los sucesores a partir de cierto estado.
        List<Sucesor> sucesores = new ArrayList<Sucesor>();
        List<Accion> acciones = new ArrayList<Accion>(estado.getAcciones());
        for(Accion accion : acciones){
            sucesores.add(new Sucesor (accion,estado.getEstado(accion),1));
        }
        return sucesores;    
    }

}
