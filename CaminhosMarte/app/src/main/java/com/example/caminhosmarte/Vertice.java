package com.example.caminhosmarte;

public class Vertice {
    public boolean foiVisitado;
    public String rotulo;
    private boolean estaAtivo;

    public Vertice(String label)
    {
        rotulo = label;
        foiVisitado = false;
        estaAtivo = true;
    }
}
