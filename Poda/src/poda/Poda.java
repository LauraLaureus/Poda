package poda;

public class Poda {
    
    
    public static void main(String[] args) {
        Vertice origen;
        Vertice destino;
        Podador podador = new Podador(new Cargador("entrada.txt").carga(), origen, destino);
        Vertice[] solucion = podador.ejecutarPoda();
    }

}
