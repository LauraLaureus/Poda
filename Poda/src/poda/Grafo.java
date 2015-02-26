package poda;

public class Grafo {

    private final int numVertices;
    private ConjuntoVertices vertices;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
    }

    public int getNumVertices() {
        return numVertices;
    }


    public void añadirVertices(ConjuntoVertices vertices) {
        this.vertices = vertices;
    }

    public boolean esConexo() {
        return vertices.esConexo();
    }
}
