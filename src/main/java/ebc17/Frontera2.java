/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebc17;

import java.util.ArrayList;

/**
 *
 * @author Roque
 */
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
