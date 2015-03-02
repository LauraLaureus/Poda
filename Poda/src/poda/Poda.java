package poda;

import java.util.Scanner;

public class Poda {
   
    private class ParVertices{
        public Vertice origen;
        public Vertice destino;       
    }
    
    static ParVertices pv;
    
    public static void main(String[] args) {
        System.out.println("Cargando Grafo...");
        Grafo g = new Cargador("Entrada.txt").carga();
        System.out.println("Grafo Cargado con éxito.");
        
        ArbolExtendido podador = new ArbolExtendido(g, pv.origen, pv.destino);
        Vertice[] solucion = podador.ejecutarPoda();
    }

    public static void leeOrigenDestino(Grafo g){
    
        /*Lee de teclado dos números 
            Comprueba que son ids del grafo
            SI:crea y devuelve los vértices como origen y destino
            NO:mensaje de error y vuelve a empezar.
        */
        
        Vertice origen,destino;
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
        
        if(origen != null && destino != null) {
            pv.origen = origen;
            pv.destino = destino;
        }
           
      
    }
   
}
