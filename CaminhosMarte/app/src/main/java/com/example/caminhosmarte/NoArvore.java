package com.example.caminhosmarte;

public class NoArvore<Dado extends Comparable<NoArvore<Dado>>> {
    Dado info;
    NoArvore<Dado> esq;
    NoArvore<Dado> dir;
    int altura;
    private boolean estaMarcadoParaMorrer;

    public NoArvore(Dado informacao) {
        this.info = informacao;
        this.esq = null;
        this.dir = null;
        this.altura = 0;
        this.estaMarcadoParaMorrer = false;
    }

    public NoArvore(Dado dados, NoArvore<Dado> esquerdo, NoArvore<Dado> direito, int altura) {
        this.info = dados;
        this.esq = esquerdo;
        this.dir = direito;
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Dado getInfo() {
        return info;
    }

    public void setInfo(Dado info) {
        this.info = info;
    }

    public NoArvore<Dado> getEsq() {
        return esq;
    }

    public void setEsq(NoArvore<Dado> esq) {
        this.esq = esq;
    }

    public NoArvore<Dado> getDir() {
        return dir;
    }

    public void setDir(NoArvore<Dado> dir) {
        this.dir = dir;
    }

    public boolean getEstaMarcadoParaMorrer() {
        return estaMarcadoParaMorrer;
    }

    public void setEstaMarcadoParaMorrer(boolean estaMarcadoParaMorrer) {
        this.estaMarcadoParaMorrer = estaMarcadoParaMorrer;
    }


    public int CompareTo(NoArvore<Dado> o) {
        return info.toString().compareTo(o.info.toString());
    }

    public boolean Equals(NoArvore<Dado> o) {
        return this.info.equals(o.info);
    }
}
