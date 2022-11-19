package com.example.caminhosmarte;
import java.util.ArrayList;
import java.util.List;

public class PilhaLista<Dado extends Comparable<Dado>> implements IStack<Dado>{
    NoLista<Dado> topo;
    int tamanho;

    public PilhaLista() // construtor
    {
        topo = null;
        tamanho = 0;
    }

    @Override
    public void empilhar(Dado dado) {
        NoLista<Dado> novoNo = new NoLista<Dado>(dado, topo);
        topo = novoNo;
        tamanho++;
    }

    @Override
    public Dado desempilhar() throws Exception {
        if (estaVazia())
            throw new Exception("Underflow da pilha");
        Dado o = topo.info;
        topo = topo.prox;
        tamanho--;
        return o;
    }

    @Override
    public Dado oTopo() throws Exception {
        if (estaVazia())
            throw new Exception("Underflow da pilha");

        return topo.info;
    }

    @Override
    public int tamanho() throws Exception {
        return tamanho;
    }

    @Override
    public boolean estaVazia() {
        return topo == null;
    }

    public List<Dado> dadosDaPilha() {
        List<Dado> lista = new ArrayList<Dado>();

        NoLista<Dado> atual = topo;
        while (atual != null)
        {
            lista.add(atual.info);
            atual = atual.prox;
        }
        return lista;
    }

    public int compareTo(Dado dado) {
        return 0;
    }
}