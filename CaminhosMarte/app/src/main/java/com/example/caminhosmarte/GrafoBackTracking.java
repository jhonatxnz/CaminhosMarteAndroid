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
    List<String> distacias = new ArrayList<String>();

    ArrayList<CidadeModel> cidadeModelArrayList = new ArrayList<CidadeModel>();
    String resultado = "";
    boolean[] passou;
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
                pilhaMovimento.add(new Movimento(cidadeOrigem, i, matriz[cidadeOrigem][i]));
                passou[i] = true;
                if (i == cidadeDestino) // se chegou ao destino
                {
                    Caminho novoCaminho = new Caminho();
                    novoCaminho.setMovimentos(pilhaMovimento);
                    System.err.println("Caminho encontrado:" + resultado);
                    soma = 0;
                    int cont = 0;
                    resultado = "";
                    for (Movimento mov:
                         pilhaMovimento) {
                        resultado += asCidades[mov.Origem()].nomeCidade + " --> ";
                        soma += pilhaMovimento.get(cont).getDistacia();
                        System.err.println("soma"+ pilhaMovimento.get(cont).getDistacia());
                        cont++;
                    }
                    resultado += asCidades[cidadeDestino].nomeCidade;
                    distacias.add(String.valueOf(soma));
                    System.err.println(distacias.get(0));


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
