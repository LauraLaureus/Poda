package poda;

import java.util.HashMap;
import java.util.HashSet;

class EstructuraConexion {

    private final HashSet<Integer> conectados;
    private final HashMap<Integer, Vertice> conjunto;

    public EstructuraConexion(HashMap<Integer, Vertice> conjunto) {
        conectados = new HashSet<>();
        this.conjunto = conjunto;
    }

    public void conecta(Integer v) {
        conectados.add(v);
    }

    public boolean estaTotalmenteConectado() {
        exploraGrafo(this.conjunto.entrySet().iterator().next().getValue());
        return conectados.size() == conjunto.size();
    }

    private void exploraGrafo(Vertice v) {
        if (!estaVerticeConectado(v.getId())) {
            conecta(v.getId());
            for (Integer value : v.getPosibilidades().values()) {
                //PON TU CODIGO AQUI XD
            }
        }
    }

    public boolean estaVerticeConectado(Integer v) {
        return conectados.contains(v);
    }

}
