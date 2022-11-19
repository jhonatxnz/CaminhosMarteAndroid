package com.example.caminhosmarte;

import java.util.ArrayList;
import java.util.List;

public class Cidade implements Comparable<Cidade> {
    List<Cidade> cidadesJson = new ArrayList<Cidade>();
    public final int tamanhoNome = 15;
    private String nomeCidade;
    double coordenadaX, coordenadaY;
    //atributo para os caminhos
    ListaSimples<Caminho> cams;


    //        final int tamanhoRegistro =
//        tamanhoNome;       // nome
//        sizeof(double) +    // x
//        sizeof(double);     // y
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public String getNome() {
        return nomeCidade;
    }

    public void setNome(String nome) {
        this.nomeCidade = padRight(nome, tamanhoNome);
    }

    public double getX() {
        return coordenadaX;
    }

    public void setX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getY() {
        return coordenadaY;
    }

    public void setY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public ListaSimples<Caminho> getCams() {
        return cams;
    }

    public void setCams(ListaSimples<Caminho> cams) {
        this.cams = cams;
    }

    public Cidade()  // construtor default (construtor vazio), sem parâmetros
    {
        this.nomeCidade = "";
        this.coordenadaX = 0;
        this.coordenadaY = 0;
        this.cams = null;
    }


    public Cidade(String nome, double x, double y, ListaSimples<Caminho> cams) {
        this.nomeCidade = nome;
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.cams = cams;
    }

    public Cidade(String nome) {
        setNome(nome);
    }

    //CompareTo de cidade por nome
    public int compareTo(Cidade outraCid) {
        return nomeCidade.toUpperCase().compareTo(outraCid.nomeCidade.toUpperCase());
    }

    public  String ToString() {
        return getNome() + "";
    }

}

