package com.example.caminhosmarte;

interface IStack<Dado> {
    int tamanho() throws Exception;

    boolean estaVazia() throws Exception;

    void empilhar(Dado elemento) throws Exception;

    Dado desempilhar() throws Exception;

    Dado oTopo() throws Exception;

}