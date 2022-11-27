package com.example.caminhosmarte;

import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GrafoBackTracking {
    char tipoGrafo;
    int qtasCidades;
    int[][] matriz;
    Ligacao[][] matriz2;
    List<String> caminhosEncontrados = new ArrayList<String>();
    Stack<Movimento> pilhaMovimento = new Stack<Movimento>();
    ArrayList<CidadeModel> cidadeModelArrayList = new ArrayList<CidadeModel>();
    String resultado = "";
    boolean[] passou;
    boolean achou;
    int aux = 0;
    int soma = 0;

    public char getTipoGrafo() {
        return tipoGrafo;
    }

    public void setTipoGrafo(char tipoGrafo) {
        this.tipoGrafo = tipoGrafo;
    }

    public int getQtasCidades() {
        return qtasCidades;
    }

    public void setQtasCidades(int qtasCidades) {
        this.qtasCidades = qtasCidades;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    //Método que monta a matriz de adjacências
    public GrafoBackTracking(Ligacao[] lig, int qtdCidades, Cidade[] cidades) {
        qtasCidades = qtdCidades;
        passou = new boolean[qtdCidades];
        //Cria matriz de ordem [23][23]
        matriz = new int[qtdCidades][qtdCidades];
        matriz2 = new Ligacao[qtdCidades][qtdCidades];
        //Percorre todos os caminhos
        for (int i = 0; i < lig.length; i++) {
            //Coloca na matriz a distacia entre os caminhos
            matriz[cidadeId(lig[i].origem, cidades)][cidadeId(lig[i].destino, cidades)] = lig[i].getDistancia();
            matriz2[cidadeId(lig[i].origem, cidades)][cidadeId(lig[i].destino, cidades)] = lig[i];
        }
    }
    public void limpar(GridView gv){
        cidadeModelArrayList.clear();
        CidadeAdapter adapter = new CidadeAdapter(gv.getContext(), cidadeModelArrayList);
        gv.setAdapter(adapter);
    }


    //RECURSÃO
    public List<String> Recursao(GridView gv, int cidadeOrigem, int cidadeDestino, Cidade[] asCidades) {
        //Percorre todas as cidades
        for (int i = 0; i < qtasCidades; i++)
            if ((matriz[cidadeOrigem][i] != 0) && (!passou[i])) {
                pilhaMovimento.add(new Movimento(cidadeOrigem, i));
                soma += matriz[cidadeOrigem][i];
                resultado += asCidades[cidadeOrigem].nomeCidade + " --> ";
                passou[i] = true;
                if (i == cidadeDestino) // se chegou ao destino
                {
                    achou = true;
                    Caminho novoCaminho = new Caminho();
                    novoCaminho.setMovimentos(pilhaMovimento);
                    resultado += asCidades[cidadeDestino].nomeCidade;
                    System.err.println("Caminho encontrado:" + resultado);

                    cidadeModelArrayList.add(new CidadeModel(resultado));
                    CidadeAdapter adapter = new CidadeAdapter(gv.getContext(), cidadeModelArrayList);
                    gv.setAdapter(adapter);
                    caminhosEncontrados.add(resultado);
                    resultado = asCidades[aux].nomeCidade + " --> ";

                    pilhaMovimento.pop(); // busca novos caminhos
                    passou[i] = false;
                } else {
                    aux = cidadeOrigem;
                    Recursao(gv, i, cidadeDestino, asCidades);
                }
            }

        if (!pilhaMovimento.empty()) {
            pilhaMovimento.pop();
            passou[cidadeOrigem] = false;
        }
        System.err.println("Distancia: " + soma);
        return caminhosEncontrados;
    }

    //metodo que retorna posicao da cidade buscando pelo seu nome
    public Integer cidadeId(String nome, Cidade[] cidades) {
        //percorre tudo
        for (int i = 0; i < cidades.length; i++) {
            //compara cod com cod
            if (cidades[i].getNome().compareTo(nome) == 0) {
                //retorna o indice
                return i;
            }
        }
        return 999;
    }
}
