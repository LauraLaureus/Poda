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
        Vertice a, b;
        Integer peso;
        Grafo g = new Grafo();

        while (line != null) {
            //NOTAS de DEBUG: no admite la creación de vértices sin la arista
            //si comienza por # es un comentario y nos lo saltamos
            if ((line.length() > 0) && (line.charAt(0) != '#')) {
                lineaPartida = line.split(",");
                a = traduceAVertice(lineaPartida, 0);
                b = traduceAVertice(lineaPartida, 1);
                peso = traduceAEntero(lineaPartida,2);
                
                if(a != null && b != null){
                    if(g.contiene(a)) a = g.dameVertice(a.getId());
                    if(g.contiene(b)) b = g.dameVertice(b.getId());
                    
                    a.enlazar(b, peso);
                    b.enlazar(a, peso);
                    
                    g.añadeVértice(a);
                    g.añadeVértice(b);
                }
                
            }

            line = leerLinea();
        }


        return g;
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


    private Integer traduceAEntero(String[] lineaPartida, int indice){
        if(lineaPartida[indice].equalsIgnoreCase("Inf")) return null;
        return Integer.parseInt(lineaPartida[indice]);
    }
    
    private Vertice traduceAVertice(String[] lineaPartida, int i) {

        if (Character.isDigit(lineaPartida[i].charAt(0))) {
            return new Vertice(Integer.parseInt(lineaPartida[i]));
        }
        return null;

    }


}
