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
    Integer pesoActual;
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
    
    private void ramificarVértice(Vertice verticeActual) {
        if (verticeActual.equals(destino)){
            solucionParcial = (Stack<Vertice>) caminoActual.clone();
            pesoSolucionParcial = pesoActual;
            arbolExtendido.pop();
        }
        else{
            listaCerrada.add(verticeActual);
            HashMap<Integer, Integer> posibilidades = verticeActual.getPosibilidades();
            borrarDatosListaCerrada(posibilidades);
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
        
        return solucionParcial;
    }

    private void ordenaPosibilidades(HashMap<Integer, Integer> posibilidades) {
        /*Meter en árbol extendido ordenados por peso*/
        ArrayList<Entry<Integer,Integer>> mapEntries = new ArrayList<>();
        
        for (Entry<Integer, Integer> entrySet : posibilidades.entrySet()) {
            mapEntries.add(entrySet);
        }
        
        Collections.reverseOrder( new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                 return (Integer) o1.getValue() - (Integer) o2.getValue();
            }
        });

        for (Entry mapEntry : mapEntries) {
            arbolExtendido.add(grafo.dameVertice((Integer) mapEntry.getKey()));
        }
        
    }

    private void podarRamas(Vertice verticeActual) {
        for (Vertice v : arbolExtendido) {
            if(Objects.equals(v.getId(), verticeActual.getId())) break;
            else{
                arbolExtendido.pop();
            }
        }
    }

    private void borrarDatosListaCerrada(HashMap<Integer, Integer> posibilidades) {
        for (Vertice vertice : listaCerrada) {
            if (posibilidades.containsKey(vertice.getId()))
                posibilidades.remove(vertice.getId());
        }
    }
}
