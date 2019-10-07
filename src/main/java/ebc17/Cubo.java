package ebc17;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Roque
 */
public class Cubo  implements Cloneable{
    private int N; // numero filas del cubo
    private int LEFT [][];
    private int DOWN [][];
    private int RIGHT [][];
    private int UP [][];
    private int BACK [][];
    private int FRONT [][];
    
    public Cubo (int N) throws IOException, FileNotFoundException, ParseException {
        this.LEFT= new int [N][N];
        this.DOWN= new int [N][N];
        this.RIGHT= new int [N][N];
        this.UP= new int [N][N];
        this.BACK= new int [N][N];
        this.FRONT= new int [N][N];
        this.N=N;
        leerJSON("cube.json.txt");
    }
    //metodo que clona un cubo
    public Object clone(){
        Cubo obj =null;
        try{
            obj=(Cubo)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("El cubo no se ha podido duplicar");
        }
        //copiamos la cara izquierda
        obj.LEFT=(int[][])obj.LEFT.clone();
        for(int i =0;i<obj.LEFT.length;i++){
            obj.LEFT[i]=(int[])obj.LEFT[i].clone();
        }
        //copiamos la cara de abajo
        obj.DOWN=(int[][])obj.DOWN.clone();
        for(int i=0; i<obj.DOWN.length; i++){
            obj.DOWN[i]=(int[])obj.DOWN[i].clone();
        }
        //copiamos la cara derecha
        obj.RIGHT=(int[][])obj.RIGHT.clone();
        for(int i=0; i<obj.RIGHT.length; i++){
            obj.RIGHT[i]=(int[])obj.RIGHT[i].clone();
        }
        //copiamos la cara de arriba
        obj.UP=(int[][])obj.UP.clone();
        for(int i=0; i<obj.UP.length; i++){
            obj.UP[i]=(int[])obj.UP[i].clone();
        }
        //copiamos la cara de atras
        obj.BACK=(int[][])obj.BACK.clone();
        for(int i=0; i<obj.BACK.length; i++){
            obj.BACK[i]=(int[])obj.BACK[i].clone();
        }
        //copiamos la cara de enfrente
         obj.FRONT=(int[][])obj.FRONT.clone();
        for(int i=0; i<obj.FRONT.length; i++){
            obj.FRONT[i]=(int[])obj.FRONT[i].clone();
        }
        return obj; //devolvemos un clon del cubo
    }
    
    public void leerJSON (String fileJSON) throws FileNotFoundException, IOException, ParseException{
        String caras []={"LEFT","DOWN","RIGHT","UP","BACK","FRONT"};
        int fila =0;
        
        JSONArray lista = null;
        Object obj = new JSONParser().parse(new FileReader(fileJSON));
        JSONObject jo = (JSONObject) obj;
        
        for (int k =0;k<caras.length;k++){
            JSONArray general = ((JSONArray)jo.get(caras[k]));
            fila =0;
            Iterator iterator = general.iterator();
            while(iterator.hasNext()){
                lista =(JSONArray)iterator.next();
                for (int i =0;i<lista.size();i++){
                    
                    switch(caras[k]){
                        case "LEFT":
                            this.LEFT[fila][i]=(int)((long) lista.get(i));
                            break;
                            case "DOWN":
                            this.DOWN[fila][i]=(int)((long) lista.get(i));
                            break;
                        case "RIGHT":
                            this.RIGHT[fila][i]=(int)((long) lista.get(i));
                            break;
                        case "UP":
                            this.UP[fila][i]=(int)((long) lista.get(i));
                            break;
                        case "BACK":
                            this.BACK[fila][i]=(int)((long) lista.get(i));
                            break;
                        case "FRONT":
                            this.FRONT[fila][i]=(int)((long) lista.get(i));
                            break;    
                    }
                }
                fila++;
            }
        }
    }
    public void mostrarCara(String cara){
        for (int i=0;i<N;i++) {  
            for (int j=0;j<N;j++) { 
                switch (cara) {
                    case "LEFT":
                        System.out.print(LEFT[i][j]);
                        break;
                    case "DOWN":
                        System.out.print(DOWN[i][j]);
                        break;
                    case "RIGHT":
                        System.out.print(RIGHT[i][j]);
                        break;
                    case "UP":
                        System.out.print(UP[i][j]);
                        break;
                    case "BACK":
                        System.out.print(BACK[i][j]);
                        break;
                    case "FRONT":
                        System.out.print(FRONT[i][j]);
                        break;        
                }          
            }
            System.out.println();
        }
    }
}
