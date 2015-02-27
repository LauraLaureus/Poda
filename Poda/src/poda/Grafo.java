package poda;

import java.util.HashMap;

public class Grafo {

    final private HashMap<Integer, Vertice> conjunto;
    
    public Grafo() {
        this.conjunto = new HashMap<>();
    }

    public void añadeVértice(Vertice v) {
        if (v == null) {
            return;
        }
        if (!conjunto.containsKey(v.getId())) {
            conjunto.put(v.getId(), v);
        }
    }

    public boolean esConexo() {
        return new EstructuraConexion(conjunto).estaTotalmenteConectado();
    }

    public boolean contiene(Vertice v) {
        return conjunto.containsValue(v);
    }

    public void enlaza(Vertice a, Vertice aAñadir, int peso) {
        conjunto.get(a.getId()).enlazar(aAñadir, peso);

    }

    public int size() {
        return this.conjunto.size();
    }

    public Vertice dameVertice(Integer id) {
        return conjunto.get(id);
    }
}
