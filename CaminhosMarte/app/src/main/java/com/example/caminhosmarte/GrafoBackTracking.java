package com.example.caminhosmarte;

public class GrafoBackTracking {
    final int tamanhoDistancia = 4;
    char tipoGrafo;
    int qtasCidades;
    int [][]matriz;
    public GrafoBackTracking(ListaSimples<Ligacao> lig,int qtdCidades)
    {
        qtdCidades = qtdCidades;
        //monta matriz com qtasCidades por qtasCidades
        matriz = new int[qtasCidades][qtasCidades];

        //posiciona no primeiro
        lig.PosicionarNoPrimeiro();

        //enquanto dado atual for diferente de nulo insere na matriz a distancia dos caminhos
        while (lig.DadoAtual() != null)
        {
            matriz[Integer.parseInt(lig.DadoAtual().idCidadeOrigem)]
                  [Integer.parseInt(lig.DadoAtual().idCidadeDestino)] = lig.DadoAtual().distancia;

            lig.AvancarPosicao();
        }
    }


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

//    public void Exibir(DataGridView dgv)
//    {
//        dgv.RowCount = dgv.ColumnCount = qtasCidades;
//        for (int coluna = 0; coluna < qtasCidades; coluna++)
//        {
//            dgv.Columns[coluna].HeaderText = coluna.ToString();
//            dgv.Rows[coluna].HeaderCell.Value = coluna.ToString();
//            dgv.Columns[coluna].Width = 30;
//        }
//        for (int linha = 0; linha < qtasCidades; linha++)
//            for (int coluna = 0; coluna < qtasCidades; coluna++)
//                if (matriz[linha, coluna] != 0)
//        dgv[coluna, linha].Value = matriz[linha, coluna];
//    }

    public PilhaVetor<Movimento> BuscarCaminho(int origem, int destino,
                                               DataGridView dgvGrafo,
                                               DataGridView dgvPilha)

    {
        int cidadeAtual, saidaAtual;
        bool achouCaminho = false,
                naoTemSaida = false;
        bool[] passou = new bool[qtasCidades];
        // inicia os valores de “passou” pois ainda não foi em nenhuma cidade
        for (int indice = 0; indice < qtasCidades; indice++)
            passou[indice] = false;
        cidadeAtual = origem;
        saidaAtual = 0;

        var pilha = new PilhaVetor<Movimento>(qtasCidades);
        var caminhos = new PilhaVetor<Movimento>(qtasCidades);

        while (!achouCaminho && !naoTemSaida)//tirar !achouCaminho // desempilhar e tacar false na cidade
        {
            naoTemSaida = (cidadeAtual == origem && saidaAtual == qtasCidades && pilha.EstaVazia);

            if (!naoTemSaida)
            {
                while ((saidaAtual < qtasCidades) && !achouCaminho)
                {
                    // se não há saída pela cidade testada, verifica a próxima

                    if (matriz[cidadeAtual, saidaAtual] == 0)
                    saidaAtual++;
                        else
                    // se já passou pela cidade testada, vê se a próxima cidade permite saída
                    if (passou[saidaAtual])
                        saidaAtual++;
                    else
                        // se chegou na cidade desejada, empilha o local
                        // e termina o processo de procura de caminho
                        if (saidaAtual == destino)
                        {
                            Movimento movim = new Movimento(cidadeAtual, saidaAtual);
                            pilha.Empilhar(movim);
                            achouCaminho = true;
                        }
                        else
                        {
                            Movimento movim = new Movimento(cidadeAtual, saidaAtual);
                            pilha.Empilhar(movim);
                            passou[cidadeAtual] = true;
                            cidadeAtual = saidaAtual; // muda para a nova cidade
                            saidaAtual = 0; // reinicia busca de saídas da nova
                            // cidade a partir da primeira cidade

                        }
                }
            } /// if ! naoTemSaida
            if (!achouCaminho)
                if (!pilha.EstaVazia)
                {
                    var movim = pilha.Desempilhar();
                    passou[cidadeAtual] = false;

                    saidaAtual = movim.Destino;
                    cidadeAtual = movim.Origem;
                    saidaAtual++;
                }
        }


        if (achouCaminho)
        { // desempilha a configuração atual da pilha
            // para a pilha da lista de parâmetros
            //não deu certo
            while (!pilha.EstaVazia) {
                Movimento novoCaminho = pilha.Desempilhar();
                caminhos.Empilhar(novoCaminho);
            }

            achouCaminho = false;
            //coloca passou false nas cidades
            for (int i = 0; i < qtasCidades; i++)
                passou[i] = false;
            //avanca para a prox saida p continuar procurando
            saidaAtual++;
        }
        //retona caminhos
        return caminhos;
    }
    //metodo que pga cidade por id
    public Cidade CidadeId(String nome, ListaSimples<Cidade> cidades)
    {
        //posiciona no primeiro dado
        cidades.PosicionarNoPrimeiro();

        //percorre tudo
        for (int i = 0; i < cidades.getTamanho(); i++)
        {
            //compara cod com cod
            if (cidades.DadoAtual().getNome() == nome)
            {
                //retorna o dado atual
                return cidades.DadoAtual();
            }
            //avanca posicao
            cidades.AvancarPosicao();
        }
        //retorna cidade
        return cidades.DadoAtual();
    }

}
