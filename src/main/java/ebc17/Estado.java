package ebc17;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Estado implements Cloneable {

    private int N; // numero filas del cubo
    private int LEFT[][]; //Cara izquierda del cubo
    private int DOWN[][]; //Cara inferior
    private int RIGHT[][]; //Cara derecha
    private int UP[][]; // cara superior
    private int BACK[][];// cara trasera
    private int FRONT[][];// cara inferior

    public Estado(String fileJSON) throws IOException, FileNotFoundException, ParseException {
        this.N = getN(fileJSON);
        this.LEFT = new int[N][N];
        this.DOWN = new int[N][N];
        this.RIGHT = new int[N][N];
        this.UP = new int[N][N];
        this.BACK = new int[N][N];
        this.FRONT = new int[N][N];
        leerJSON(fileJSON); //leemos el fichero json pasandole el nombre del fichero o la cadena
    }

    //metodo que clona un cubo
    public Object clone() {
        Estado obj = null;
        try {
            obj = (Estado) super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("El cubo no se ha podido duplicar");
        }
        //copiamos la cara izquierda
        obj.LEFT = (int[][]) obj.LEFT.clone();
        for (int i = 0; i < obj.LEFT.length; i++) {
            obj.LEFT[i] = (int[]) obj.LEFT[i].clone();
        }
        //copiamos la cara de abajo
        obj.DOWN = (int[][]) obj.DOWN.clone();
        for (int i = 0; i < obj.DOWN.length; i++) {
            obj.DOWN[i] = (int[]) obj.DOWN[i].clone();
        }
        //copiamos la cara derecha
        obj.RIGHT = (int[][]) obj.RIGHT.clone();
        for (int i = 0; i < obj.RIGHT.length; i++) {
            obj.RIGHT[i] = (int[]) obj.RIGHT[i].clone();
        }
        //copiamos la cara de arriba
        obj.UP = (int[][]) obj.UP.clone();
        for (int i = 0; i < obj.UP.length; i++) {
            obj.UP[i] = (int[]) obj.UP[i].clone();
        }
        //copiamos la cara de atras
        obj.BACK = (int[][]) obj.BACK.clone();
        for (int i = 0; i < obj.BACK.length; i++) {
            obj.BACK[i] = (int[]) obj.BACK[i].clone();
        }
        //copiamos la cara de enfrente
        obj.FRONT = (int[][]) obj.FRONT.clone();
        for (int i = 0; i < obj.FRONT.length; i++) {
            obj.FRONT[i] = (int[]) obj.FRONT[i].clone();
        }
        return obj; //devolvemos una copia exacta del cubo
    }
//obtenemos el tamaño del cubo, para automatizarlo mas
    public int getN(String fileJSON) throws FileNotFoundException, IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fileJSON));
        JSONObject jo = (JSONObject) obj;
        JSONArray general = ((JSONArray) jo.get("LEFT"));
        return general.size();
    }

    //metodo que lee el fichero json proporcionado.
    public void leerJSON(String fileJSON) throws FileNotFoundException, IOException, ParseException {
        String caras[] = {"LEFT", "DOWN", "RIGHT", "UP", "BACK", "FRONT"};
        int fila = 0;

        JSONArray lista = null;
        Object obj = new JSONParser().parse(new FileReader(fileJSON));
        JSONObject jo = (JSONObject) obj;

        for (int k = 0; k < caras.length; k++) {
            JSONArray general = ((JSONArray) jo.get(caras[k]));
            fila = 0;
            Iterator iterator = general.iterator();
            while (iterator.hasNext()) {
                lista = (JSONArray) iterator.next();
                for (int i = 0; i < lista.size(); i++) {
                    //lo va añadiendo segun la cara que le corresponda
                    switch (caras[k]) {
                        case "LEFT":
                            this.LEFT[fila][i] = (int) ((long) lista.get(i));
                            break;
                        case "DOWN":
                            this.DOWN[fila][i] = (int) ((long) lista.get(i));
                            break;
                        case "RIGHT":
                            this.RIGHT[fila][i] = (int) ((long) lista.get(i));
                            break;
                        case "UP":
                            this.UP[fila][i] = (int) ((long) lista.get(i));
                            break;
                        case "BACK":
                            this.BACK[fila][i] = (int) ((long) lista.get(i));
                            break;
                        case "FRONT":
                            this.FRONT[fila][i] = (int) ((long) lista.get(i));
                            break;
                    }
                }
                fila++;
            }
        }
    }

    public String getID() { //crea un String con los valores de las casillas del cubo luego llamada al metodo del md5 con ese String y el metodo lo cifra
        String ID = "";
        String caras[] = {"BACK", "DOWN", "FRONT", "LEFT", "RIGHT", "UP"};
        for (int k = 0; k < caras.length; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    switch (caras[k]) {
                        case "LEFT":
                            ID += LEFT[i][j];
                            break;
                        case "DOWN":
                            ID += DOWN[i][j];
                            break;
                        case "RIGHT":
                            ID += RIGHT[i][j];
                            break;
                        case "UP":
                            ID += UP[i][j];
                            break;
                        case "BACK":
                            ID += BACK[i][j];
                            break;
                        case "FRONT":
                            ID += FRONT[i][j];
                            break;
                    }
                }
            }
        }
        return getMD5(ID); //devuelve el ID cigrado
    }

    public void mostrarCara(String cara) { //muestra la cara del cubo que seleccionemos
        System.out.println(cara);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                switch (cara) {
                    case "LEFT":
                        System.out.print(LEFT[i][j] + " ");
                        break;
                    case "DOWN":
                        System.out.print(DOWN[i][j] + " ");
                        break;
                    case "RIGHT":
                        System.out.print(RIGHT[i][j] + " ");
                        break;
                    case "UP":
                        System.out.print(UP[i][j] + " ");
                        break;
                    case "BACK":
                        System.out.print(BACK[i][j] + " ");
                        break;
                    case "FRONT":
                        System.out.print(FRONT[i][j] + " ");
                        break;
                }
            }
            System.out.println();
        }
    }
//metodo que cifra un String segun un md5
    public static String getMD5(String input) { 
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
//movimiento L
    public void moveL(int col) {
        int aux[] = new int[N];
        for (int i = 0; i < N; i++) {
            aux[i] = FRONT[i][col];
            FRONT[i][col] = UP[N - i - 1][N - col - 1];
            UP[N - i - 1][N - col - 1] = BACK[i][col];
            BACK[i][col] = DOWN[i][col];
            DOWN[i][col] = aux[i];
        }
        //cuando se hace en una posición que es el limite superior o inferior del cubo rota hacia la derecha
        if (col == 0) {
            copiarMatriz(rotarMatrizDer(LEFT), LEFT);
        }
        if (col == N - 1) {
            copiarMatriz(rotarMatrizDer(RIGHT), RIGHT);
        }
    }
//movimiento l
    public void movel(int col) {
        int aux[] = new int[N];
        for (int i = 0; i < N; i++) {
            aux[i] = BACK[i][col];
            BACK[i][col] = UP[N - i - 1][N - col - 1];
            UP[N - i - 1][N - col - 1] = FRONT[i][col];
            FRONT[i][col] = DOWN[i][col];
            DOWN[i][col] = aux[i];
        }
        //cuando se hace en una posición que es el limite superior o inferior del cubo rota hacia la izquierda
        if (col == 0) {
            copiarMatriz(rotarMatrizIzq(LEFT), LEFT);
        }
        if (col == N - 1) {
            copiarMatriz(rotarMatrizIzq(RIGHT), RIGHT);
        }
    }
//movimiento D
    public void moveD(int dentro) {
        int aux[] = new int[N];
        for (int i = 0; i < N; i++) {
            aux[i] = FRONT[dentro][i];
            FRONT[dentro][i] = RIGHT[N - 1 - i][dentro];
            RIGHT[N - 1 - i][dentro] = BACK[N - 1 - dentro][N - 1 - i];
            BACK[N - 1 - dentro][N - 1 - i] = LEFT[i][N - 1 - dentro];
            LEFT[i][N - 1 - dentro] = aux[i];
        }
        //cuando se hace en una posición que es el limite superior o inferior del cubo rota hacia la derecha
        if (dentro == 0) {
            copiarMatriz(rotarMatrizDer(DOWN), DOWN);
        }
        if (dentro == N - 1) {
            copiarMatriz(rotarMatrizDer(UP), UP);
        }
    }
//movimiento d
    public void moved(int dentro) {
        int aux[] = new int[N];
        for (int i = 0; i < N; i++) {
            aux[i] = FRONT[dentro][i];
            FRONT[dentro][i] = LEFT[i][N - 1 - dentro];
            LEFT[i][N - 1 - dentro] = BACK[N - 1 - dentro][N - 1 - i];
            BACK[N - 1 - dentro][N - 1 - i] = RIGHT[N - 1 - i][dentro];
            RIGHT[N - 1 - i][dentro] = aux[i];
        }
        //cuando se hace en una posición que es el limite superior o inferior del cubo rota hacia la izquierda
        if (dentro == 0) {
            copiarMatriz(rotarMatrizIzq(DOWN), DOWN);
        }
        if (dentro == N - 1) {
            copiarMatriz(rotarMatrizIzq(UP), UP);
        }
    }
//movimiento B
    public void moveB(int fil) {
        int aux[] = new int[N];
        for (int i = 0; i < N; i++) {
            aux[i] = DOWN[fil][i];
            DOWN[fil][i] = LEFT[fil][i];
            LEFT[fil][i] = UP[fil][i];
            UP[fil][i] = RIGHT[fil][i];
            RIGHT[fil][i] = aux[i];
        }
        //cuando se hace en una posición que es el limite superior o inferior del cubo rota hacia la derecha
        if (fil == 0) {
            copiarMatriz(rotarMatrizDer(BACK), BACK);
        }
        if (fil == N - 1) {
            copiarMatriz(rotarMatrizDer(FRONT), FRONT);
        }
    }
//movimiento b
    public void moveb(int fil) {
        int aux[] = new int[N];
        for (int i = 0; i < N; i++) {
            aux[i] = DOWN[fil][i];
            DOWN[fil][i] = RIGHT[fil][i];
            RIGHT[fil][i] = UP[fil][i];
            UP[fil][i] = LEFT[fil][i];
            LEFT[fil][i] = aux[i];
        }
        //cuando se hace en una posición que es el limite superior o inferior del cubo rota hacia la izquierda
        if (fil == 0) {
            copiarMatriz(rotarMatrizIzq(BACK), BACK);
        }
        if (fil == N - 1) {
            copiarMatriz(rotarMatrizIzq(FRONT), FRONT);
        }
    }
//metodo que gira una cara hacia la izquierda 
    public int[][] rotarMatrizIzq(int matriz[][]) {
        int[][] otherMatriz = new int[matriz[0].length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            int k = 0;
            for (int j = matriz[0].length - 1; j >= 0; j--) {
                otherMatriz[j][i] = matriz[i][k];
                k++;
            }
        }
        return otherMatriz;
    }
//metodo que rota una cara hacia la derecha
    public int[][] rotarMatrizDer(int matriz[][]) {
        int[][] otherMatriz = new int[matriz[0].length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            int k = 0;
            for (int j = matriz[0].length - 1; j >= 0; j--) {
                otherMatriz[i][k] = matriz[j][i];
                k++;
            }
        }
        return otherMatriz;
    }
//metodo que copia una matriz en otra
    public void copiarMatriz(int origen[][], int destino[][]) {
        for (int i = 0; i < destino.length; i++) {
            for (int j = 0; j < destino[i].length; j++) {
                destino[i][j] = origen[i][j];
            }
        }
    }
//metodo que ontiene el estado de un clon del cubo despues de realizar una accion
    public Estado getEstado(Accion a) { 
        Estado estado = (Estado) this.clone();
        switch (a.getMovimiento()) {
            case 'L':
                estado.moveL(a.getPosicion());
                break;
            case 'B':
                estado.moveB(a.getPosicion());
                break;
            case 'D':
                estado.moveD(a.getPosicion());
                break;
            case 'l':
                estado.movel(a.getPosicion());
                break;
            case 'b':
                estado.moveb(a.getPosicion());
                break;
            case 'd':
                estado.moved(a.getPosicion());
                break;
        }
        return estado;
    }
//metodo que obtiene una lista con todas las acciones posibles, segun la N del cubo
    public List<Accion> getAcciones() { 
        List<Accion> acciones = new ArrayList<Accion>();
        char [] movimientos = {'B','b','D','d','L','l'};
        for (int i = 0; i < movimientos.length; i++) {
            for (int k = 0; k < N; k++) {
                acciones.add(new Accion(movimientos[i],k));
            }
        }
        return acciones;
    }
    // función que nos dice si hemos llegado a un cubo que es objetivo
    public boolean esObjetivo() {    
        
//        boolean correcto = true;
//        String caras[] = {"LEFT", "DOWN", "RIGHT", "UP", "BACK", "FRONT"};
//        for (int k = 0; k < caras.length && correcto; k++) {
//            for (int i = 0; i < LEFT.length && correcto; i++) {
//                for (int j = 0; j < LEFT.length && correcto; j++) {
//                    switch (caras[k]) {
//                        case "LEFT":
//                            if (LEFT[i][j] != LEFT[0][0]) {
//                                correcto = false;
//                            }
//                            break;
//                        case "DOWN":
//                            if (DOWN[i][j] != DOWN[0][0]) {
//                                correcto = false;
//                            }
//                            break;
//                        case "RIGHT":
//                            if (RIGHT[i][j] != RIGHT[0][0]) {
//                                correcto = false;
//                            }
//                            break;
//                        case "UP":
//                            if (UP[i][j] != UP[0][0]) {
//                                correcto = false;
//                            }
//                            break;
//                        case "BACK":
//                            if (BACK[i][j] != BACK[0][0]) {
//                                correcto = false;
//                            }
//                            break;
//                        case "FRONT":
//                            if (FRONT[i][j] != FRONT[0][0]) {
//                                correcto = false;
//                            }
//                            break;
//                    }
//                }
//            }
//        }
        return getHeuristica()==0;
    }
    //funcion estandar de un logaritmo de base N para utilizarla en el calculo de la heuristica
    public double log(double num, int base) {
        return (Math.log(num) / Math.log(base));
    }
    //metodo que calcula la heuristica de un estado, devolviendo el valor absoluto de la misma.
    public double getHeuristica() {
        double entropia = 0;
        String caras[] = {"LEFT", "DOWN", "RIGHT", "UP", "BACK", "FRONT"};
        double contador[] = new double[caras.length];
        for (int k = 0; k < caras.length; k++) {
            contador = contarColoresCara(caras[k], contador);
            for (int i = 0; i < caras.length; i++) {
                if (contador[i] > 0.0) {
                    entropia = entropia + contador[i] / (N * N) * log(contador[i] / (N * N), 6);
                }
            }
            vaciarContador(contador);
        }
        return Math.abs(entropia);
    }
    //metodo que genera un vector con la cantidad de numeros que hay en una cara, correspondiendo con su posicion en el mismo
    public double[] contarColoresCara(String cara, double[] contador) {
        for (int k = 0; k < contador.length; k++) {
            for (int i = 0; i < LEFT.length; i++) {
                for (int j = 0; j < LEFT.length; j++) {
                    switch (cara) {
                        case "LEFT":
                            if (LEFT[i][j] == k) {
                                contador[k]++;
                            }
                            break;
                        case "DOWN":
                            if (DOWN[i][j] == k) {
                                contador[k]++;
                            }
                            break;
                        case "RIGHT":
                            if (RIGHT[i][j] == k) {
                                contador[k]++;
                            }
                            break;
                        case "UP":
                            if (UP[i][j] == k) {
                                contador[k]++;
                            }
                            break;
                        case "BACK":
                            if (BACK[i][j] == k) {
                                contador[k]++;
                            }
                            break;
                        case "FRONT":
                            if (FRONT[i][j] == k) {
                                contador[k]++;
                            }
                            break;
                    }
                }
            }
        }
        return contador;
    }
    //metodo que vuelve a iniciar el vector de contar colores a 0   
    public void vaciarContador(double[] contador) {
        for (int i = 0; i < contador.length; i++) {
            contador[i] = 0.0;
        }
    }

    public String toString() {
        String s = "";
        s += "LEFT                    DOWN                    "
                + "RIGHT                   UP                       BACK                    FRONT\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += LEFT[i][j] + " ";
            }
            s += "\t";
            for (int j = 0; j < N; j++) {
                s += DOWN[i][j] + " ";
            }
            s += "\t";
            for (int j = 0; j < N; j++) {
                s += RIGHT[i][j] + " ";
            }
            s += "\t";
            for (int j = 0; j < N; j++) {
                s += UP[i][j] + " ";
            }
            s += "\t";
            for (int j = 0; j < N; j++) {
                s += BACK[i][j] + " ";
            }
            s += "\t";
            for (int j = 0; j < N; j++) {
                s += FRONT[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}
