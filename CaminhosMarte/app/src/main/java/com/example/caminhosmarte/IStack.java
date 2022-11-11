package com.example.caminhosmarte;

interface IStack<Dado> {
    int Tamanho() throws Exception;

    boolean EstaVazia() throws Exception;

    void Empilhar(Dado elemento) throws Exception;

    Dado Desempilhar() throws Exception;

    Dado OTopo() throws Exception;

}