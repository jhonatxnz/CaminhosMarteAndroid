package com.example.caminhosmarte;

public class NoLista<Dado extends Comparable<Dado>>
{
    Dado info;
    NoLista<Dado> prox;

    public NoLista(Dado novaInfo, NoLista<Dado> proximoNo)
    {
        info = novaInfo;
        prox = proximoNo;
    }

    public NoLista(Dado novaInfo)
    {
        info = novaInfo;
        prox = null;
    }
    public Dado getDado() {
        return info;
    }
    public void setDado(Dado info){
        this.info = info;
    }

    public NoLista<Dado> getProx(){
        return prox;
    }
    public void setProx(NoLista<Dado> prox){
        this.prox = prox;
    }

}
