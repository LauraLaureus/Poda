package poda;

import java.util.HashMap;
import java.util.Objects;

public class Vertice {

    private final Integer id;
    private final HashMap<Integer, Arista> posibilidades;

    public Vertice(Integer id) {
        this.id = id;
        posibilidades = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public void enlazar(Vertice v) {
        if (!posibilidades.containsKey(v.getId())) {
            posibilidades.put(v.getId(), v);
        }
    }

    public HashMap<Integer, Arista> getPosibilidades() {
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
