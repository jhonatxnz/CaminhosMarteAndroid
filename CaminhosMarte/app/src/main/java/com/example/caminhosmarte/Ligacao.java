package com.example.caminhosmarte;

import java.util.Locale;

public class Ligacao implements Comparable<Ligacao> {
    final int tamCodigo = 3,
            tamDistancia = 5,
            tamTempo = 4,
            tamCusto = 4;

    final int iniCodigoOrigem = 0,
            iniCodigoDestino = iniCodigoOrigem + tamCodigo,
            iniDistancia = iniCodigoDestino + tamCodigo,
            iniTempo = iniDistancia + tamDistancia,
            iniCusto = iniTempo + tamTempo;


    String idCidadeOrigem, idCidadeDestino;
    int distancia, tempo, custo;

    public Ligacao(String idCidadeOrigem, String idCidadeDestino, int distancia, int tempo, int custo) {
        this.idCidadeOrigem = idCidadeOrigem;
        this.idCidadeDestino = idCidadeDestino;
        this.distancia = distancia;
        this.tempo = tempo;
        this.custo = custo;
    }

    //construtor vazio
    public Ligacao() {
    }

    public String getIdCidadeOrigem()
    {
        return idCidadeOrigem;
    }

    public String setIdCidadeOrigem(String IdCidadeOrigem)
    {
        return this.idCidadeOrigem = idCidadeOrigem;
    }

    public String getIdCidadeDestino()
    {
        return idCidadeDestino;
    }
    public String setIdCidadeDestino(String IdCidadeDestino)
    {
        return this.idCidadeDestino = IdCidadeDestino;
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
        return (idCidadeOrigem.toUpperCase() + idCidadeDestino.toUpperCase()).compareTo(
                outro.idCidadeOrigem.toUpperCase() + outro.idCidadeDestino.toUpperCase());
    }

    public Ligacao LerRegistro(StreamReader arquivo) {
        if (arquivo != null) // arquivo aberto?
        {
            String linha = arquivo.ReadLine();
            IdCidadeOrigem = linha.Substring(iniCodigoOrigem, tamCodigo);
            IdCidadeDestino = linha.Substring(iniCodigoDestino, tamCodigo);
            Distancia = Integer.parseInt(linha.Substring(iniDistancia, tamDistancia));
            Tempo = Integer.parseInt(linha.Substring(iniTempo, tamTempo));
            Custo = Integer.parseInt(linha.Substring(iniCusto, tamCusto));
            return this; // retorna o próprio objeto Contato, com os dados
        }
        return default (Ligacao);
    }

    public void GravarRegistro(StreamWriter arq) {
        if (arq != null)  // arquivo de saída aberto?
        {
            arq.WriteLine(ParaArquivo());
        }
    }

    public String ParaArquivo() {
        return String.format("%s %s %d %d %d",idCidadeOrigem,idCidadeDestino,distancia,tempo,custo);
    }

    public  String ToString() {
        return String.format("%s %s %d %d %d",idCidadeOrigem,idCidadeDestino,distancia,tempo,custo);
    }
}