package com.example.caminhosmarte;

public class Movimento implements Comparable<Movimento> {
    // onde estou, para onde vou
    private int origem, destino;

    public Movimento(int or, int dest) {
        origem = or;
        destino = dest;
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
}

