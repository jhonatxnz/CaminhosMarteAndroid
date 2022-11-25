package com.example.caminhosmarte;

import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GrafoBackTracking {
    final int tamanhoDistancia = 4;
    char tipoGrafo;
    int qtasCidades;
    int [][]matriz;
    Ligacao[][] matriz2;
    boolean []passou;
    boolean achou;



    public char getTipoGrafo(){
        return  tipoGrafo;
    }
    public void setTipoGrafo(char tipoGrafo){
        this.tipoGrafo = tipoGrafo;
    }
    public int getQtasCidades(){
        return  qtasCidades;
    }
    public void setQtasCidades(int qtasCidades){
        this.qtasCidades = qtasCidades;
    }
    public int[][] getMatriz(){
        return  matriz;
    }
    public void setMatriz(int[][] matriz){
        this.matriz = matriz;
    }

    //Método que monta a matriz de adjacências
    public GrafoBackTracking(Ligacao[] lig,int qtdCidades,Cidade[] cidades)
    {
        //Cria matriz de ordem [23][23]
        matriz = new int[qtdCidades][qtdCidades];
        matriz2= new Ligacao[qtdCidades][qtdCidades];
        //Percorre todos os caminhos
        for(int i =0;i < lig.length;i++){
            //Coloca na matriz a distacia entre os caminhos
            matriz[cidadeId(lig[i].origem,cidades)][cidadeId(lig[i].destino,cidades)] = lig[i].getDistancia();
            matriz2[cidadeId(lig[i].origem,cidades)][cidadeId(lig[i].destino,cidades)] = lig[i];

        }
    }
//RECURSAO
public String[] Recursao(GridView gv){
        String [] caminhosEncontrados = new String[26];
        ArrayList<CidadeModel> cidadeModelArrayList = new ArrayList<CidadeModel>();
        char letra = 'A';

        for(int i = 0;i < caminhosEncontrados.length;i++){
            caminhosEncontrados[i] = ""+letra++;
            cidadeModelArrayList.add(new CidadeModel(caminhosEncontrados[i]));
        }

    CidadeAdapter adapter = new CidadeAdapter(gv.getContext(), cidadeModelArrayList);
    gv.setAdapter(adapter);
    return caminhosEncontrados;
}


//    public PilhaVetor<Movimento> BuscarCaminho(int origem, int destino,
//                                               DataGridView dgvGrafo,
//                                               DataGridView dgvPilha)
//
//    {
//        int cidadeAtual, saidaAtual;
//        bool achouCaminho = false,
//                naoTemSaida = false;
//        bool[] passou = new bool[qtasCidades];
//        // inicia os valores de “passou” pois ainda não foi em nenhuma cidade
//        for (int indice = 0; indice < qtasCidades; indice++)
//            passou[indice] = false;
//        cidadeAtual = origem;
//        saidaAtual = 0;
//
//        var pilha = new PilhaVetor<Movimento>(qtasCidades);
//        var caminhos = new PilhaVetor<Movimento>(qtasCidades);
//
//        while (!achouCaminho && !naoTemSaida)//tirar !achouCaminho // desempilhar e tacar false na cidade
//        {
//            naoTemSaida = (cidadeAtual == origem && saidaAtual == qtasCidades && pilha.EstaVazia);
//
//            if (!naoTemSaida)
//            {
//                while ((saidaAtual < qtasCidades) && !achouCaminho)
//                {
//                    // se não há saída pela cidade testada, verifica a próxima
//
//                    if (matriz[cidadeAtual, saidaAtual] == 0)
//                    saidaAtual++;
//                        else
//                    // se já passou pela cidade testada, vê se a próxima cidade permite saída
//                    if (passou[saidaAtual])
//                        saidaAtual++;
//                    else
//                        // se chegou na cidade desejada, empilha o local
//                        // e termina o processo de procura de caminho
//                        if (saidaAtual == destino)
//                        {
//                            Movimento movim = new Movimento(cidadeAtual, saidaAtual);
//                            pilha.Empilhar(movim);
//                            achouCaminho = true;
//                        }
//                        else
//                        {
//                            Movimento movim = new Movimento(cidadeAtual, saidaAtual);
//                            pilha.Empilhar(movim);
//                            passou[cidadeAtual] = true;
//                            cidadeAtual = saidaAtual; // muda para a nova cidade
//                            saidaAtual = 0; // reinicia busca de saídas da nova
//                            // cidade a partir da primeira cidade
//
//                        }
//                }
//            } /// if ! naoTemSaida
//            if (!achouCaminho)
//                if (!pilha.EstaVazia)
//                {
//                    var movim = pilha.Desempilhar();
//                    passou[cidadeAtual] = false;
//
//                    saidaAtual = movim.Destino;
//                    cidadeAtual = movim.Origem;
//                    saidaAtual++;
//                }
//        }
//
//
//        if (achouCaminho)
//        { // desempilha a configuração atual da pilha
//            // para a pilha da lista de parâmetros
//            //não deu certo
//            while (!pilha.EstaVazia) {
//                Movimento novoCaminho = pilha.Desempilhar();
//                caminhos.Empilhar(novoCaminho);
//            }
//
//            achouCaminho = false;
//            //coloca passou false nas cidades
//            for (int i = 0; i < qtasCidades; i++)
//                passou[i] = false;
//            //avanca para a prox saida p continuar procurando
//            saidaAtual++;
//        }
//        //retona caminhos
//        return caminhos;
//    }
    //metodo que retorna posicao da cidade buscando pelo seu nome
    public Integer cidadeId(String nome, Cidade[] cidades)
    {
        //percorre tudo
        for (int i = 0; i < cidades.length; i++)
        {
            //compara cod com cod
            if (cidades[i].getNome().compareTo(nome) == 0)
            {
                //retorna o dado atual
                return i;
            }

        }
        return 999;
    }
}
