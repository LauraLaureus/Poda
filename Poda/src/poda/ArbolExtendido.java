package poda;

import java.util.HashMap;
import java.util.Stack;

public class ArbolExtendido {
    private final Grafo grafo;
    private final Vertice origen;
    private final Vertice destino;
    Stack<Vertice> arbolExtendido;
    Stack<Vertice> caminoActual;
    Stack<Vertice> solucionParcial;
    Integer pesoActual;
    Integer pesoSolucionParcial;

    public ArbolExtendido(Grafo grafo, Vertice origen, Vertice destino) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        Stack<Vertice> arbolExtendido = new Stack<>();
        Stack<Vertice> caminoActual = new Stack<>();
        Stack<Vertice> SolucionParcial = new Stack<>();
    }
    
    private void ramificarVértice(Vertice verticeActual) {
        if (verticeActual.equals(destino)){
            solucionParcial = (Stack<Vertice>) caminoActual.clone();
            pesoSolucionParcial = pesoActual;
            arbolExtendido.pop();
        }
        else{
            HashMap<Integer, Integer> posibilidades = verticeActual.getPosibilidades();
            //borrar datos de la lista cerrada
            
            ordenaPosibilidades(posibilidades);
            
            Vertice hijoMenorPeso = arbolExtendido.pop();
            while (hijoMenorPeso != verticeActual){
                
                pesoActual = pesoActual + posibilidades.get(hijoMenorPeso.getId());
                
                if (null != pesoSolucionParcial && pesoActual <= pesoSolucionParcial){
                    caminoActual.push(hijoMenorPeso);
                    
                    ramificarVértice(hijoMenorPeso);
                    
                } else{
                    podarRamas(verticeActual); //saca de la pila hasta encontrar verticeActual
                }
                hijoMenorPeso = arbolExtendido.pop();
            }
        }
        caminoActual.pop();
    }

    public Stack<Vertice> ejecutarPoda() {
        Vertice verticeActual = grafo.dameVertice(origen.getId());
        arbolExtendido.add(verticeActual);
        caminoActual.add(verticeActual);
        pesoActual = 0;
        ramificarVértice(verticeActual);
        
        return solucionParcial; //pasar el stack a Vertice[]
    }

    private void ordenaPosibilidades(HashMap<Integer, Integer> posibilidades) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void podarRamas(Vertice verticeActual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
