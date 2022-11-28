package com.example.caminhosmarte;

public class Movimento implements Comparable<Movimento> {
    // onde estou, para onde vou
    private int origem, destino,distacia;

    public Movimento(int or, int dest,int dist) {
        origem = or;
        destino = dest;
        distacia = dist;
    }

    public int Origem() {
        return origem;
    }

    public int Destino() {
        return destino;
    }

    public void setOrigem(Integer Origem) {
        this.origem = Origem;
    }

    public void setDestino(Integer Destino) {
        this.destino = Destino;
    }

    public String ToString() {
        return origem + " " + destino;
    }

    @Override
    public int compareTo(Movimento movimento) {
        return 0;
    }

    public int getDistacia() {
        return distacia;
    }

    public void setDistacia(int distacia) {
        this.distacia = distacia;
    }
}

