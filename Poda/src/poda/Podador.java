package poda;

import java.util.Stack;

class Podador {
    private final Grafo grafo;
    private final Vertice origen;
    private final Vertice destino;
    Vertice verticeActual;
    Stack<Vertice> caminoActual;
    Stack<Vertice> solucionParcial;

    public Podador(Grafo grafo, Vertice origen, Vertice destino) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
    }
    
    private void ramificarVértice(Vertice verticeActual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vertice[] ejecutarPoda() {
        Vertice verticeActual = grafo.dameVertice(origen.getId());

        ramificarVértice(verticeActual);
        return solucionParcial;
    }
}
