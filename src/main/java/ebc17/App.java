package ebc17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

public class App {

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {

        String opcion;
        boolean correcto, hayPoda, solucion = false;
        int Prof_Max = 100, Inc_Prof = 1;
        Scanner sc = new Scanner(System.in);
        Problema prob = new Problema();
//        System.out.println(prob.getEstadoInicial());
//        System.out.println(prob.getEstadoInicial().getID()+" "+prob.getEstadoInicial().getHeuristica());
//        prob.getEstadoInicial().moveb(0);
//       
//        System.out.println(prob.getEstadoInicial());
//        System.out.println(prob.getEstadoInicial().getID()+" "+prob.getEstadoInicial().getHeuristica());
//        prob.getEstadoInicial().moveD(0);
//        System.out.println(prob.getEstadoInicial());
//        System.out.println(prob.getEstadoInicial().getID()+" "+prob.getEstadoInicial().getHeuristica());
//        prob.getEstadoInicial().moved(1);
//     
//        System.out.println(prob.getEstadoInicial());
//        System.out.println(prob.getEstadoInicial().getID()+" "+prob.getEstadoInicial().getHeuristica());
////        prob.getEstadoInicial().movel(1);
////        System.out.println(prob.getEstadoInicial());
//        System.out.println(prob.getEstadoInicial().getID()+" "+prob.getEstadoInicial().getHeuristica());
//        prob.getEstadoInicial().moveD(0);
//        System.out.println(prob.getEstadoInicial());
//        System.out.println(prob.getEstadoInicial().getID()+" "+prob.getEstadoInicial().getHeuristica());
//        prob.getEstadoInicial().moveb(2);
//        System.out.println(prob.getEstadoInicial());
//        System.out.println(prob.getEstadoInicial().getID()+" "+prob.getEstadoInicial().getHeuristica());

//        List<Accion> LA = prob.getEstadoInicial().getAcciones();
//        for (int i = 0; i < LA.size(); i++) {
//            System.out.println(LA.get(i));
//        }

        System.out.println("Sistemas Inteligentes-Cubo Rubick");
        do {
            System.out.print("¿Desea Utilizar Poda?\n\t1. Con Poda \n\t2. Sin Poda\n(Elija 1 o 2): ");
            opcion = sc.next();
            correcto = opcion.equals("1") || opcion.equals("2");
            if (!correcto) {
                System.out.println("Opcion no Correcta.\n");
            }
        } while (!correcto);

        hayPoda = opcion.equals("1") ? true : false;

//         pedimos al usuario la estrategia de búsqueda
        do {
            System.out.print("Seleccione la Estrategia que desea seguir\n\t1. Anchura\n\t2. Profundidad simple\n\t3. Profundidad Acotada\n\t4. Profundidad Iterativa"
                    + "\n\t5. Coste Uniforme\n\t6. Voraz\n\t7. A Asterisco\n(Escribe opcion 1-7): ");
            opcion = sc.next();
            correcto = opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4") || opcion.equals("5")
                    || opcion.equals("6") || opcion.equals("7");
            if (!correcto) {
                System.out.println("Opcion no Correcta.");
            }
        } while (!correcto);

//         Para cualquier opcion distinta de la profundidad simple elegimos la profundidad máxima acotada
        if (!opcion.equals("2")) {
            do {
                correcto = true;
                System.out.print("Escribe profundida máxima: ");
                try {
                    Prof_Max = Integer.parseInt(sc.next());
                } catch (Exception e) {
                    System.out.println("Valor no númerico");
                    correcto = false;
                }
            } while (!correcto);
        }

//         Si la opcion es la profuncidad iterativa pedimos al usuario el incremento por iteración
        if (opcion.equals("4")) {
            do {
                correcto = true;
                System.out.print("Escribe Incremento Profundidad Iterativa: ");
                try {
                    Inc_Prof = Integer.parseInt(sc.next());
                } catch (Exception e) {
                    System.out.println("Valor no númerico");
                    correcto = false;
                }
            } while (!correcto);
        }

        switch (opcion) {
            case "1":
                solucion = Busqueda_Acotada(prob, "Anchura", Prof_Max, hayPoda); //anchura
                break;
            case "2":
                solucion = Busqueda_Acotada(prob, "ProfundidadSimple", 10000, hayPoda); //Profundidad simple, utilizada prof infinita
                break;
            case "3":
                solucion = Busqueda_Acotada(prob, "ProfundidadAcotada", Prof_Max, hayPoda); // Profundidad acotada
                break;
            case "4":
                solucion = Busqueda(prob, "ProfundidadIterativa", Prof_Max, Inc_Prof, hayPoda); // Profundidad iterativa
                break;
            case "5":
                solucion = Busqueda_Acotada(prob, "CosteUniforme", Prof_Max, hayPoda); // Coste uniforme
                break;
            case "6":
                solucion = Busqueda_Acotada(prob, "Voraz", Prof_Max, hayPoda); // Voraz
                break;
            case "7":
                solucion = Busqueda_Acotada(prob, "A", Prof_Max, hayPoda); // A asterisco
                break;

        }

        if (!solucion) {
            System.out.println("Solucion no encontrada en la profundidad maxima dada");
        }

    }

    public static boolean Busqueda_Acotada(Problema prob, String estrategia, int Prof_Max, boolean conPoda) throws IOException {

        int total = 0;
        Frontera frontera = new Frontera();
        frontera.CrearFronteraVacia();
        NodoArbol n_inicial = new NodoArbol(null, prob.getEstadoInicial(), 0, null, 0, 0);//he puesto null
        frontera.Insertar(n_inicial);
        boolean solucion = false;
        NodoArbol n_actual = null;
        Map<String, Double> nodosVisitados = new HashMap(); // HashMap para la poda de estados repetidos
        Deque<NodoArbol> nodosSolucion = new LinkedList(); // cola doble para almacenar la solución generada

        while (!solucion && !frontera.EstaVacia()) {

            n_actual = frontera.Eliminar();
            n_actual.setID(total);
            if (prob.esObjetivo(n_actual.getEstado())) {
                solucion = true;
            } else {

                List<Sucesor> LS = prob.getEspacioDeEstados().getSucesores(n_actual.getEstado());
                List<NodoArbol> LN = CreaListaNodosArbol(LS, n_actual, Prof_Max, estrategia);

                for (NodoArbol nodo : LN) {

                    if (conPoda) { // si se ha elegido poda no se insertan en la frontera los estados repetidos                   
                        String nodoString = nodo.getEstado().getID();
                        if (nodosVisitados.containsKey(nodoString)) {
                            if ((nodo.getF() < nodosVisitados.get(nodoString).doubleValue())) {
                                if ((nodo.getF() < nodosVisitados.get(nodoString).doubleValue() && !estrategia.contains("Profundidad"))
                                      || (nodo.getF() > nodosVisitados.get(nodoString).doubleValue() && estrategia.contains("Profundidad"))) {
                                    nodo.setID(++total);
                                    frontera.Insertar(nodo);
                                    nodosVisitados.replace(nodoString, nodo.getF());
                                }
                            }
                        } else {
                            nodo.setID(++total);
                            nodosVisitados.put(nodoString, nodo.getF());
                            frontera.Insertar(nodo);
                        }
                    } else {
                        nodo.setID(++total);
                        frontera.Insertar(nodo);
                    }
                }
            }
        }
        if (solucion) {
//             si encontramos solucion la introducimos en la cola doble nodosSolucion            
            while (n_actual.getPadre() != null) {
                nodosSolucion.addFirst(n_actual);
                n_actual = n_actual.getPadre();
            }
//             se inserta el nodo inicial y se genera el fichero en la carpeta de la solución
            nodosSolucion.addFirst(n_inicial);
            escribirConsola(nodosSolucion, estrategia, total);
            generarFichero(nodosSolucion, estrategia, total);
            System.out.println("\nSE HA ESCRITO EL FICHERO EN LA CARPETA DEL PROYECTO");

        }
        return solucion;
    }

    public static List<NodoArbol> CreaListaNodosArbol(List<Sucesor> LS, NodoArbol n_actual, int Prof_Max, String estrategia) {
        List<NodoArbol> LN = new ArrayList();
        if (n_actual.getP() < Prof_Max) { // si aún podemos seguir iterando por no alcanzar la profundidad máxima
            NodoArbol aux = null;
            for (Sucesor sucesor : LS) {
//                dependiendo de la estrategia generamos los nodos
                switch (estrategia) {
                    case "Anchura":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getP() + 1);
                        break;
                    case "ProfundidadSimple":
                    case "ProfundidadAcotada":
                    case "ProfundidadIterativa":

                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, Prof_Max - (n_actual.getP() + 1));
                        break;
                    case "CosteUniforme":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getCoste() + sucesor.getCoste());
                        break;
                    case "Voraz":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, sucesor.getEstado().getHeuristica());
                        break;
                    case "A":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getCoste() + sucesor.getCoste() + sucesor.getEstado().getHeuristica());
                        break;
                }
                LN.add(aux);

            }
        }
        return LN;
    }
//     metodo utilizado para la busqueda de profundidad iterativa, que recibe ademas el incremento de profundidad 

    public static boolean Busqueda(Problema prob, String estrategia, int Prof_Max, int Inc_Prof, boolean conPoda) throws IOException {

        int Prof_Actual = Inc_Prof;
        boolean solucion = false;
        while (!solucion && Prof_Actual <= Prof_Max) {
            solucion = Busqueda_Acotada(prob, estrategia, Prof_Actual, conPoda);
            Prof_Actual += Inc_Prof;
        }
        return solucion;
    }

    public static void generarFichero(Deque<NodoArbol> camino, String estrategia, int total) throws IOException {
        File archivo = new File("Path" + estrategia + ".txt");
        FileWriter file = new FileWriter(archivo);
        PrintWriter pw = new PrintWriter(file);
        pw.println("La soluci\u00f3n es: ");
        pw.println("Estrategia: " + estrategia.toUpperCase());
        pw.println("Total Nodos Generados: " + total);
        pw.println("Profundidad: " + (camino.getLast().getP() + 1));
        pw.println("Costo: " + (camino.getLast().getCoste()));
        pw.println("");
        int i = 0;
        for (NodoArbol nodoarbol : camino) {
            if (i == 0) {
                if (estrategia.equals("Voraz") || estrategia.equals("A")) {
                    pw.println("None " + nodoarbol.getEstado().getHeuristica() + " " + nodoarbol.getP() + " " + nodoarbol.getCoste());
                } else {
                    pw.println("None " + nodoarbol.getF() + " " + nodoarbol.getP() + " " + nodoarbol.getCoste());
                }
                pw.println(nodoarbol.getEstado());
            } else {
                pw.println(nodoarbol.getAccion() + " " + nodoarbol.getF() + " " + nodoarbol.getP() + " " + nodoarbol.getCoste());
                pw.println(nodoarbol.getEstado().getID());
            }
            pw.println("");
            i++;
        }
        pw.close();
    }

//     método para mostrar la solución por consola
    public static void escribirConsola(Deque<NodoArbol> camino, String estrategia, int total) {
        
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("\nLa soluci\u00f3n es: ");
        System.out.println("Estrategia: " + estrategia.toUpperCase());
        System.out.println("Total Nodos Generados: " + total);
        System.out.println("Profundidad: " + (camino.getLast().getP() + 1));
        System.out.println("Costo: " + camino.getLast().getCoste());
        System.out.println("");
        int i = 0;
        for (NodoArbol nodoarbol : camino) {
            if (i == 0) {
                if (estrategia.equals("Voraz") || estrategia.equals("A")) {
                    System.out.println(nodoarbol.getID() + "None " + df.format(nodoarbol.getEstado().getHeuristica()) + " " + nodoarbol.getP() + " " + nodoarbol.getCoste());
                } else {
                    System.out.println("None " + df.format(nodoarbol.getF()) + " " + nodoarbol.getP() + " " + nodoarbol.getCoste());
                }
                System.out.println(nodoarbol.getEstado().getID());
            } else {
                System.out.println("[" + nodoarbol.getID() + "]" + nodoarbol.getAccion() + " f=" + df.format(nodoarbol.getF()) + " P=" + nodoarbol.getP() + " C=" + nodoarbol.getCoste());
                System.out.println(nodoarbol.getEstado().getID());
            }
            System.out.println("");
            i++;
        }
    }

}
