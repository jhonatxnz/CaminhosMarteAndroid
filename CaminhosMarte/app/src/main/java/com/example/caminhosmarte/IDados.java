//package com.example.caminhosmarte;
//
//public interface IDados<Dado extends Comparable<Dado>>
//        {
//        void PosicionarNoPrimeiro();
//        void RetrocederPosicao();
//        void AvancarPosicao();
//        void PosicionarNoUltimo();
//        void PosicionarEm(int posicaoDesejada);
//        Dado DadoAtual();  // retorna o dado atualmente visitado
//        Dado this[int indice] { get; set; }  // acessa e retorna o indice-ésimo Dado
//        void ExibirDados();
////        void ExibirDados(ListBox lista);
////        void ExibirDados(ComboBox lista);
////        void ExibirDados(TextBox lista);
//        boolean Existe(Dado procurado,  int ondeEsta); //OUT
//        boolean Excluir(Dado dadoAExcluir);             // excluirá o Dado atualmente visitado
//        boolean IncluirNoInicio(Dado novoValor);   // Inserir novo dado antes do primeiro dado armazenado
//        boolean IncluirAposFim(Dado novoValor);    // Inserir novo dado após o último dado armazenado
//        boolean Incluir(Dado novoValor);   // incluirá na ordem dada pelo método comparador
//        boolean Incluir(Dado novoValor, int posicaoDeInclusao);
//        void Ordenar();
//        void LerDados(String nomeArquivo);
//        void GravarDados(String nomeArquivo);
//        //Situacao SituacaoAtual { get; set; }
//        int PosicaoAtual();
//        boolean EstaNoInicio();
//        boolean EstaNoFim();
//        boolean EstaVazio();
//        int Tamanho();
//        }