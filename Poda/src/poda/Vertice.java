package poda;

import java.util.HashMap;
import java.util.Objects;

public class Vertice {

    private final Integer id;
    private final HashMap<Integer, Integer> posibilidades;

    public Vertice(Integer id) {
        this.id = id;
        posibilidades = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }
    //Modificado para que tenga que pasar el peso de la arista que referencia a este vértice
    public void enlazar(Vertice v, int peso) {
        if (!posibilidades.containsKey(v.getId())) {
            posibilidades.put(v.getId(), peso);
        }
    }

    public HashMap<Integer, Integer> getPosibilidades() {
        return posibilidades;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertice other = (Vertice) obj;
        return Objects.equals(this.id, other.id);
    }

}
