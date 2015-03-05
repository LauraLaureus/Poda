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
        if (verticeActual.equals(destino)){
            System.out.println("solucion parcial encontrada");///
            solucionParcial = (Stack<Vertice>) caminoActual.clone();
            pesoSolucionParcial = pesoCaminoActual;
        }
        else{
            listaCerrada.add(verticeActual);
            HashMap<Integer, Integer> posibilidades = verticeActual.getPosibilidades();
            borrarDatosListaCerrada(posibilidades);
            ordenaPosibilidades(posibilidades);
            
            Vertice hijoMenorPeso = arbolExtendido.peek();
            Integer pesoHijoMenor;
            while (hijoMenorPeso != verticeActual){ 
                System.out.println("Analizando hijo: "+hijoMenorPeso.getId()+ " del padre: " + verticeActual.getId());////
                pesoHijoMenor = pesoCaminoActual + posibilidades.get(hijoMenorPeso.getId());
                
                if (null == pesoSolucionParcial || pesoHijoMenor <= pesoSolucionParcial){
                    caminoActual.push(hijoMenorPeso);
                    
                    ramificarVértice(hijoMenorPeso, pesoHijoMenor);
                    arbolExtendido.pop();
                    hijoMenorPeso = arbolExtendido.peek();
                } else{//eto ta bien
                    podarRamas(verticeActual);
                    hijoMenorPeso = arbolExtendido.pop();
                }
            }
        }
        caminoActual.pop();
    }
    public Stack<Vertice> ejecutarPoda() {
        Vertice verticeActual = grafo.dameVertice(origen.getId());
        arbolExtendido.add(verticeActual);
        caminoActual.add(verticeActual);
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
