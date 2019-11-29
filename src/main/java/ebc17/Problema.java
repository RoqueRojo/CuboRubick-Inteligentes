
package ebc17;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Problema {
    
    private EspacioDeEstados espacioDeEstados;
    private Estado estadoInicial;

    public Problema() throws IOException, FileNotFoundException, ParseException {
        this.espacioDeEstados = new EspacioDeEstados("cube.json");
        this.estadoInicial = espacioDeEstados.getEstado();
    }
    
    public boolean esObjetivo (Estado estado) {
        return estado.esObjetivo();
    }

    public EspacioDeEstados getEspacioDeEstados() {
        return espacioDeEstados;
    }

    public void setEspacioDeEstados(EspacioDeEstados espacioDeEstados) {
        this.espacioDeEstados = espacioDeEstados;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }
}
