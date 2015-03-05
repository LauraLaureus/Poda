package poda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Stack;

public class ArbolExtendido {
    private final Grafo grafo;
    private final Vertice origen;
    private final Vertice destino;
    Stack<Vertice> arbolExtendido;
    Stack<Vertice> caminoActual;
    Stack<Vertice> solucionParcial;
    ArrayList<Vertice> listaCerrada;
    Integer pesoSolucionParcial;

    public ArbolExtendido(Grafo grafo, Vertice origen, Vertice destino) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        listaCerrada = new ArrayList<>();
        arbolExtendido = new Stack<>();
        caminoActual = new Stack<>();
        solucionParcial = new Stack<>();
    }
    
    private void ramificarVértice(Vertice verticeActual, int pesoCaminoActual) {
        
        caminoActual.push(verticeActual);
        
        if (verticeActual.equals(destino)){
            solucionParcial = (Stack<Vertice>) caminoActual.clone();
            pesoSolucionParcial = pesoCaminoActual;
            
        }
        else{
            //Añado el vértice actual a la lista cerrada
            listaCerrada.add(verticeActual);
            
            //Se sacan los hijos y se meten en el "arbolExtendido", quitando los que se encuentren en lista cerrada
            HashMap<Integer, Integer> posibilidades = verticeActual.getPosibilidades();
            borrarDatosListaCerrada(posibilidades);
            ordenaPosibilidades(posibilidades);
            
            //inicializo bucle, cogiendo el Vértice del inicio de "arbolExtendido", que es el hijo de menor peso o el propio vertice
            Vertice hijoMenorPeso = arbolExtendido.peek();
            Integer pesoHijoMenor;
            
            while (hijoMenorPeso != verticeActual){ 

                pesoHijoMenor = pesoCaminoActual + posibilidades.get(hijoMenorPeso.getId());
                
                //Si encuentra un hijo con camino más optimo que la solucion, entra en el
                if (null == pesoSolucionParcial || pesoHijoMenor <= pesoSolucionParcial){
                    ramificarVértice(hijoMenorPeso, pesoHijoMenor);
                } else{//si no, poda los hijos
                    podarRamas(verticeActual);
                }
                hijoMenorPeso = arbolExtendido.peek(); //se lee el nuevo vertice de arbolExpandido a tratar (hijo/verticeActual)
            }
        }
        //Saco el verticeActual del arbolExtendido, que está en la parte top
        arbolExtendido.pop();
        //También saco el verticeActual del camino, porque retrocedemos
        caminoActual.pop();
    }
    public Stack<Vertice> ejecutarPoda() {
        Vertice verticeActual = grafo.dameVertice(origen.getId());
        arbolExtendido.add(verticeActual);
        ramificarVértice(verticeActual, 0);
        
        return solucionParcial;
    }

    private void ordenaPosibilidades(HashMap<Integer, Integer> posibilidades) {
        /*Meter en árbol extendido ordenados por peso*/
        ArrayList<Entry<Integer,Integer>> mapEntries = new ArrayList<>();
        
        for (Entry<Integer, Integer> entrySet : posibilidades.entrySet()) {
            mapEntries.add(entrySet);
        }
        
        Collections.sort( mapEntries, Collections.reverseOrder(new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return (Integer) o1.getValue() - (Integer) o2.getValue();
            }
        }));

        for (Entry mapEntry : mapEntries) {
            arbolExtendido.add(grafo.dameVertice((Integer) mapEntry.getKey()));
        }
        
    }

    private void podarRamas(Vertice verticeActual) {
        while (arbolExtendido.peek() != verticeActual) arbolExtendido.pop();
    }

    private void borrarDatosListaCerrada(HashMap<Integer, Integer> posibilidades) {
        for (Vertice vertice : listaCerrada) {
            if (posibilidades.containsKey(vertice.getId()))
                posibilidades.remove(vertice.getId());
        }
    }
}
