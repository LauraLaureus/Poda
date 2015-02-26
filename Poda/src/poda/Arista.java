package poda;

public class Arista implements Comparable<Arista> {

    private final Vertice vertice;
    private final int peso;

    public Arista(Vertice vertice, int peso) {
        this.vertice = vertice;
        this.peso = peso;
    }

    public int getPeso() {
        return this.peso;
    }

    @Override//para usar en poda
    public int compareTo(Arista o) {
        if (this.peso == o.peso) {
            return 0;
        }
        return this.peso - o.peso;
    }

    @Override
    public boolean equals(Object arista) {
        /*if (!(arista instanceof Arista)) {
            return false;
        }
        Arista o = (Arista) arista;
        if (this.getU() == o.getU() && this.getV() == o.getV()) {
            return true;
        } else if (this.getU() == o.getV() && this.getV() == o.getU()) {
            return true;
        }*/
        return false;
    }

}
