package com.example.caminhosmarte;

import android.annotation.SuppressLint;

public class Ligacao implements Comparable<Ligacao> {

    String origem, destino;
    int distancia, tempo, custo;

    public Ligacao(String origem, String destino, int distancia, int tempo, int custo) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.tempo = tempo;
        this.custo = custo;
    }

    //construtor vazio
    public Ligacao() {
    }

    public String getOrigem()
    {
        return origem;
    }

    public String setOrigem(String origem)
    {
        return this.origem = origem;
    }

    public String getDestino()
    {
        return destino;
    }
    public String setDestino(String destino)
    {
        return this.destino = destino;
    }

    public int getDistancia()
    {
        return distancia;
    }

    public int setDistancia(Integer Distancia)
    {
        return this.distancia = Distancia;
    }

    public int getTempo()
    {
        return tempo;
    }

    public int setTempo(Integer Tempo)
    {
        return this.tempo = Tempo;
    }

    public int getCusto()
    {
        return custo;
    }

    public int setCusto(Integer Custo)
    {
        return this.custo = Custo;
    }

    public int compareTo(Ligacao outro) {
        return (origem.toUpperCase() + destino.toUpperCase()).compareTo(
                outro.origem.toUpperCase() + outro.destino.toUpperCase());
    }

    @SuppressLint("DefaultLocale")
    public String ParaArquivo() {
        return String.format("%s %s %d %d %d",origem,destino,distancia,tempo,custo);
    }

    @SuppressLint("DefaultLocale")
    public  String ToString() {
        return String.format("%s %s %d %d %d",origem,destino,distancia,tempo,custo);
    }
}