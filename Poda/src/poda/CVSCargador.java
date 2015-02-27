package poda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CVSCargador {

    private BufferedReader lector = null;

    public CVSCargador(String ruta) {
        try {
            this.lector = new BufferedReader(new FileReader(ruta));
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontró el archivo");
        }
    }

    public Grafo carga() {

        String line = leerLinea();
        String[] lineaPartida;
        Arista arista;
        Vertice a, b;
        ContenedorAristas contAristas = new ContenedorAristas();
        Grafo vertices = new Grafo();

        while (line != null) {
            //si comienza por # es un comentario y nos lo saltamos
            if ((line.length() > 0) && (line.charAt(0) != '#')) {
                lineaPartida = line.split(",");
                a = traduceAVertice(lineaPartida, 0);
                b = traduceAVertice(lineaPartida, 1);

                if (a != null && b != null) {
                    if (vertices.contiene(a)) {
                        a = vertices.dameVertice(a.getId());
                    }
                    if (vertices.contiene(b)) {
                        b = vertices.dameVertice(b.getId());
                    }
                    a.enlazar(b);
                    b.enlazar(a);
                }
                vertices.añadeVértice(a);
                vertices.añadeVértice(b);

                arista = creaArista(lineaPartida);
                if (arista != null) {
                    contAristas.añadirArista(arista);
                }
            }

            line = leerLinea();
        }

        Grafo resultado = new Grafo(vertices.size());
        resultado.añadirContenedorAristas(contAristas);
        resultado.añadirVertices(vertices);

        return resultado;
    }

    private String leerLinea() {
        String line;
        try {
            line = lector.readLine();
        } catch (IOException ex) {
            line = null;
        }

        return line;
    }

    private Integer[] traduceLinea(String[] lineaPartida) {

        //si es Inf entonces no existe arista pero existen los vértices
        for (int i = 0; i < lineaPartida.length; i++) {
            if (lineaPartida[i].equalsIgnoreCase("Inf")) {
                lineaPartida[i] = "-1";
            }
        }

        Integer[] resultado = {
            Integer.parseInt(lineaPartida[0]),
            Integer.parseInt(lineaPartida[1]),
            Integer.parseInt(lineaPartida[2])
        };

        if (resultado[0] < 0) {
            resultado[0] = null;
        } else if (resultado[1] < 0) {
            resultado[1] = null;
        } else if (resultado[2] < 0) {
            resultado[2] = null;
        }

        return resultado;
    }

    private Vertice traduceAVertice(String[] lineaPartida, int i) {

        if (Character.isDigit(lineaPartida[i].charAt(0))) {
            return new Vertice(Integer.parseInt(lineaPartida[i]));
        }
        return null;

    }

    private Arista creaArista(String[] lineaPartida) {
        Integer[] lineaTraducida = traduceLinea(lineaPartida);
        if (lineaTraducida[0] != null
                && lineaTraducida[1] != null
                && lineaTraducida[2] != null) {
            return new Arista(
                    lineaTraducida[0],
                    lineaTraducida[1],
                    lineaTraducida[2]
            );
        }
        return null;
    }
}
