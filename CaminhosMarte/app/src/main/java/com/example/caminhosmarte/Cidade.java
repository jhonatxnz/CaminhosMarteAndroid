package com.example.caminhosmarte;

public class Cidade implements Comparable<Cidade> {

    public final int tamanhoNome = 15;
    public String nomeCidade;
    double coordenadaX, coordenadaY;


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



    public Cidade()  // construtor default (construtor vazio), sem parâmetros
    {
        this.nomeCidade = "";
        this.coordenadaX = 0;
        this.coordenadaY = 0;

    }


    public Cidade(String nome, double x, double y) {
        this.nomeCidade = nome;
        this.coordenadaX = x;
        this.coordenadaY = y;
    }

    public Cidade(String nome) {
        setNome(nome);
    }

    //CompareTo de cidade por nome
    @Override
    public int compareTo(Cidade outraCid) {
        return nomeCidade.toUpperCase().compareTo(outraCid.nomeCidade.toUpperCase());
    }

    public  String ToString() {
        return getNome() + "";
    }

}

