/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec111;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;

/**
 *
 * @author @EdilbertoPozo
 */
public class Cubo {

    private byte[][][] matriz;
    private int n;

    /**
     * Constructor con matriz como parametro
     *
     * @param matriz
     */
    public Cubo(byte[][][] matriz, int n) {
        this.matriz = matriz;
        this.n = n;
    }

    /**
     * Constructor con ruta del JSON como parametro
     *
     * @param ruta del JSON
     */
    public Cubo(String ruta) throws FileNotFoundException {
        FileReader archivo = new FileReader(new File(ruta));
        //TO-DO
    }

    /**
     * Metodo que crea un cubo de NxN resuelto
     *
     * @param n Dimension del cubo
     * @return Cubo de NxN hecho
     */
    public static Cubo cuboNN(int n) { //no se si está bien TO-DO revisar
        byte[][][] m = new byte[6][n][n];

        for (byte i = 0; i < 6; i++) {
            for (byte j = 0; j < n; j++) {
                for (byte k = 0; k < n; k++) {
                    m[i][j][k] = i;
                }
            }
        }
        Cubo rtn = new Cubo(m, n);
        return rtn;
    }

    /**
     * Metodo que clona el cubo
     *
     * @return un cubo igual al que tenemos
     */
    public Cubo clonar() {
        return (new Cubo(this.matriz, this.n));
    }

    /**
     * Metodo para realizar un movimiento
     *
     * @param movimiento String con el movimiento en el formato "L2" donde L es
     * el eje y 2 "la fila/columna" a mover
     */
    public void mover(String movimiento) {

        if (movimiento.length() != 2) { // comprobamos que el movimiento tiene dos caracteres
            System.out.println("Formato de Movimiento incorrecto");
        }
        char tipo = movimiento.charAt(0);
        int num = -1;
        try {num = Integer.parseInt(movimiento.substring(1));}catch (NumberFormatException e) {}
        
        if (num < 0 || num >= n) {
            System.out.println("Movimiento incorrecto");
        } else {

            switch (tipo) {
                case 'L':
                    moverL(true, num);
                    break;
                case 'l':
                    moverL(false, num);
                    break;
                case 'B':
                    moverB(true, num);
                    break;
                case 'b':
                    moverB(false, num);
                    break;
                case 'D':
                    moverD(true, num);
                    break;
                case 'd':
                    moverD(false, num);
                    break;
                default:
                    System.out.println("Movimiento incorrecto");
                    break;
            }
        }
    }

    /**
     *
     * @param sentido booleano que indica si el sentido es horario o no
     * @param num
     */
    private void moverL(boolean sentido, int num) {
        //TO-DO
    }

    /**
     *
     * @param sentido booleano que indica si el sentido es horario o no
     * @param num
     */
    private void moverB(boolean sentido, int num) {
        //TO-DO
    }

    /**
     *
     * @param sentido booleano que indica si el sentido es horario o no
     * @param num
     */
    private void moverD(boolean sentido, int num) {
        //TO-DO
    }

}
