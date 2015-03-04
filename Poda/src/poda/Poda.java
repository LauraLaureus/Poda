package poda;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;

public class Poda { 
   
    private class ParVertices{
        public Vertice origen;
        public Vertice destino;       
    }
    
    static ParVertices pv;
    static Vertice origen;
    static Vertice destino;
    
    public static void main(String[] args) {
        System.out.println("Cargando Grafo...");
        Grafo g = new Cargador("Entrada.txt").carga();
        System.out.println("Grafo Cargado con éxito.");
        
        leeOrigenDestino(g);
        ArbolExtendido arbol = new ArbolExtendido(g, origen, destino);
        Stack<Vertice> solucion = arbol.ejecutarPoda();
        salida(solucion, g);
    }

    public static void leeOrigenDestino(Grafo g){
    
        /*Lee de teclado dos números 
            Comprueba que son ids del grafo
            SI:crea y devuelve los vértices como origen y destino
            NO:mensaje de error y vuelve a empezar.
        */
        
        Scanner teclado = new Scanner(System.in);
        int lectura;
        while (true){
            System.out.println("Inserte el Número identificador del vértice Origen");
            lectura = teclado.nextInt();
            if(g.contiene(new Vertice(lectura))){
                origen = new Vertice(lectura);
                break;
            }
                System.out.println("El vértice especificado no está contenido"
                    + "en el grafo ");
            
        }
        
        while(true){
            System.out.println("Inserte el Número identificador del vértice Destino");
            lectura = teclado.nextInt();
            if(g.contiene(new Vertice(lectura))){
                destino = new Vertice(lectura);
                break;
            }
                System.out.println("El vértice especificado no está contenido"
                    + "en el grafo ");
            
        }
              
    }
   
    private static void salida(Stack<Vertice> solucion, Grafo g) {
        
        System.out.println("Este es el grafo leído.");
        for (Entry vertice : g.getConjunto().entrySet()) {
            System.out.println("Vertice: " + vertice.getKey());
            System.out.println("Tiene la siguiente descendencia: ");
            System.out.println(descendenciaToString(g.dameVertice((Integer) vertice.getKey())));
        }
        
        System.out.println("Esta es la solución obtenida: ");
        for (Vertice v : solucion) {
            System.out.println(v.getId());
        }
        
    }
    
    private static String descendenciaToString(Vertice dameVertice) {
        String vecinos = "";
        for (Integer hijo : dameVertice.getPosibilidades().keySet()) {
            vecinos += hijo.toString();
        }
        
        return vecinos;
    }

}
