package com.example.caminhosmarte;

import java.util.Stack;

public class Caminho implements Comparable<Caminho> {

    private String origem;
    private String destino;
    int distancia, tempo, custo;
    private Stack<Movimento> movimentos;

    public String getNomeOrigem() {
        return origem;
    }

    public void setNomeOrigem(String origem) {
        this.origem = origem;
    }

    public String getNomeDestino() {
        return destino;
    }

    public void setNomeDestino(String nomeOrigem) {
        this.destino = destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public Caminho()  // construtor default (construtor vazio), sem parâmetros
    {
        this.origem = "";
        this.destino = "";
        this.custo = 0;
        this.tempo = 0;
        this.distancia = 0;
    }

    public Caminho(String nomeOrigem, String nomeDestino, int distancia, int tempo, int custo) {
        this.origem = nomeOrigem;
        this.destino = nomeDestino;
        this.custo = custo;
        this.tempo = tempo;
        this.distancia = distancia;
    }


    public int compareTo(Caminho outroCam) {
        String iguiOri = "";
        String iguiDes = "";
        iguiOri = origem + destino;
        iguiDes = outroCam.origem + outroCam.destino;

        return iguiOri.toUpperCase().compareTo(iguiDes.toUpperCase());
    }

    public Stack<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(Stack<Movimento> movimentos) {
        this.movimentos = movimentos;

    }
}
