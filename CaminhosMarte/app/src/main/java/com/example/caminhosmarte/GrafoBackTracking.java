package com.example.caminhosmarte;

public class GrafoBackTracking {
    final int tamanhoDistancia = 4;
    char tipoGrafo;
    int qtasCidades;
    int [][]matriz;
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

    public GrafoBackTracking(Ligacao[] lig,int qtdCidades,Cidade []cidades)
    {

        qtdCidades = qtdCidades;
        //monta matriz com qtasCidades por qtasCidades
        matriz = new int[qtasCidades][qtasCidades];

        //posiciona no primeiro


        //enquanto dado atual for diferente de nulo insere na matriz a distancia dos caminhos
        for(int i =0;i < lig.length;i++){

            matriz[CidadeId(lig[i].origem,cidades)][CidadeId(lig[i].destino,cidades)] = lig[i].getDistancia();

        }

//        for(int x = 0;x < matriz.length;x++){
//            for(int z = 0;z <matriz.length;z++){
//                System.err.println(matriz[x][z]);
//            }
//            System.err.println("\n");
//        }

//        while (lig.DadoAtual() != null)
//        {
//            matriz[Integer.parseInt(lig.DadoAtual().idCidadeOrigem)]
//                    [Integer.parseInt(lig.DadoAtual().idCidadeDestino)] = lig.DadoAtual().distancia;
//
//            lig.AvancarPosicao();
//        }
    }
//RECURSAO
//    public PilhaLista<Movimento> BuscarCaminhos(int idCidadeOrigem, int idCidadeDestino) throws Exception {
//        PilhaLista<Movimento> pilhaMovimento = new PilhaLista<Movimento>();
//        for (int i = 0; i < qtasCidades; i++)
//            if ((matriz[idCidadeOrigem][i] != null) && (!passou[i]))
//        {
//            pilhaMovimento.empilhar(new Movimento(idCidadeOrigem, i));
//            passou[i] = true;
//
//            if (i == idCidadeDestino) // se chegou ao destino
//            {
//                Caminho novoCaminho = new Caminho();
//                novoCaminho. = pilhaMovimento.Clone();
//                caminhosEncontrados.Add(novoCaminho);
//                pilhaMovimento.desempilhar(); // busca novos caminhos
//                passou[i] = false;
//            }
//            else
//                BuscarCaminhos(i, idCidadeDestino); // backtracking
//        }
//
//        if (!pilhaMovimento.estaVazia())
//        {
//            pilhaMovimento.desempilhar();
//            passou[idCidadeOrigem] = false;
//        }
//        return caminhosEncontrados;
//    }

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
    public Integer CidadeId(String nome, Cidade[] cidades)
    {
        int cnt = 0;
        //percorre tudo
        for (int i = 0; i < cidades.length; i++)
        {
            //compara cod com cod
            if (cidades[i].getNome().compareTo(nome) == 0)
            {
                //retorna o dado atual
                cnt = i;
                return i;
            }

        }
        return cnt;
    }
}
