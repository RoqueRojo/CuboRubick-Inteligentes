
package ebc17;

import java.util.ArrayList;

public class Frontera2 {
     private ArrayList<NodoArbol> arrayList = new ArrayList<NodoArbol>();
    
    public void CrearFronteraVacia (){
        arrayList.clear();
    }   
    public void Insertar (NodoArbol n){
        arrayList.add(n);
    }   
    public void Eliminar (){
        arrayList.remove(0);
    }
    public boolean EstaVacia(){
        return arrayList.isEmpty();
    }
    
}
