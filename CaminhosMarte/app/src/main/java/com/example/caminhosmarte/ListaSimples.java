package com.example.caminhosmarte;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ListaSimples<Dado extends Comparable<Dado>> {
    NoLista<Dado> primeiro, ultimo, atual, anterior;
    int quantosNos;
    boolean primeiroAcessoDoPercurso;

    public ListaSimples() {
        primeiro = ultimo = anterior = atual = null;
        quantosNos = 0;
        primeiroAcessoDoPercurso = false;
    }

//public void LerArquivoDeRegistros(String nomeArquivo)
//        {
//        atual = null;
//        Dado dado = new Dado();
//        var origem = new FileStream(nomeArquivo, FileMode.OpenOrCreate);
//        var arquivo = new BinaryReader(origem);
//        MessageBox.Show("Tamanho do arquivo =" + origem.Length + "\n\nTamanho do registro = " + dado.TamanhoRegistro);
//        int posicaoFinal = (int)origem.Length / dado.TamanhoRegistro - 1;
//
//        Particionar(0, posicaoFinal, ref atual);
//
//        origem.Close();
//
//        void Particionar(long inicio, long fim, ref NoLista<Dado> atual)
//        {
//        if (inicio <= fim)
//        {
//        long meio = (inicio + fim) / 2;
//
//        dado = new Dado();       // cria um objeto para armazenar os dados
//        dado.LerRegistro(arquivo, meio);
//        atual = new NoLista<Dado>(dado);
//        InserirEmOrdem(atual.Info);
//        Particionar(meio + 1, fim, ref atual);
//        }
//        }
//        }
//
//public void GravarArquivoDeRegistros(string nomeArquivo)
//        {
//        var destino = new FileStream(nomeArquivo, FileMode.Create);
//        var arquivo = new BinaryWriter(destino);
//
//        atual = primeiro;
//        while (atual != null)
//        {
//        atual.Info.GravarRegistro(arquivo);
//        atual = atual.Prox;
//        }
//
//        arquivo.Close();
//        }


    public void PercorrerLista() {
        atual = primeiro;
        while (atual != null)
            atual = atual.prox;
    }

    public void InserirAntesDoInicio(NoLista<Dado> novoNo) {
        if (EstaVazia())       // se a lista está vazia, estamos
            ultimo = novoNo; // incluindo o 1o e o último nós!

        novoNo.prox = primeiro; // faz o novo nó apontar para o nó
        primeiro = novoNo;      // atualmente no início da lista
        quantosNos++;           // (que pode ser null)
    }

    public void InserirAposFim(NoLista<Dado> novoNo) {
        if (EstaVazia())
            primeiro = novoNo;
        else
            ultimo.prox = novoNo;

        quantosNos++;
        ultimo = novoNo;
        ultimo.prox = null; // garantimos final lógico da lista
    }

//public void listar(ListBox lsb)
//{
//  lsb.Items.Clear();
//  atual = primeiro;
//  while (atual != null)
//  {
//    lsb.Items.Add(atual.Info.ToString());
//    atual = atual.Prox;
//  }
//}

    public boolean ExisteDado(Dado outroProcurado) {
        anterior = null;
        atual = primeiro;

        //	Em seguida, é verificado se a lista está vazia. Caso esteja, é
        //	retornado false ao local de chamada, indicando que a chave não foi
        //	encontrada, e atual e anterior ficam valendo null

        if (EstaVazia())
            return false;

        // a lista não está vazia, possui nós

        // dado procurado é menor que o primeiro dado da lista:
        // portanto, dado procurado não existe
        if (outroProcurado.compareTo(primeiro.info) < 0)
            return false;

        // dado procurado é maior que o último dado da lista:
        // portanto, dado procurado não existe
        if (outroProcurado.compareTo(ultimo.info) > 0) {
            anterior = ultimo;
            atual = null;
            return false;
        }

        //	caso não tenha sido definido que a chave está fora dos limites de
        //	chaves da lista, vamos procurar no seu interior

        //	o apontador atual indica o primeiro nó da lista e consideraremos que
        //	ainda não achou a chave procurada nem chegamos ao final da lista

        boolean achou = false;
        boolean fim = false;

        //	repete os comandos abaixo enquanto não achou o RA nem chegou ao
        //	final da lista

        while (!achou && !fim)

            // se o apontador atual vale null, indica final da lista

            if (atual == null)
                fim = true;

                // se não chegou ao final da lista, verifica o valor da chave atual

            else

                // verifica igualdade entre chave procurada e chave do nó atual

                if (outroProcurado.compareTo(atual.info) == 0)
                    achou = true;
                else

                    // se chave atual é maior que a procurada, significa que
                    // a chave procurada não existe na lista ordenada e, assim,
                    // termina a pesquisa indicando que não achou. Anterior
                    // aponta o anterior ao atual, que foi acessado por
                    // último

                    if (atual.info.compareTo(outroProcurado) > 0)
                        fim = true;
                    else {

                        // se não achou a chave procurada nem uma chave > que ela,
                        // então a pesquisa continua, de maneira que o apontador
                        // anterior deve apontar o nó atual e o apontador atual
                        // deve seguir para o nó seguinte

                        anterior = atual;
                        atual = atual.prox;
                    }

        // por fim, caso a pesquisa tenha terminado, o apontador atual
        // aponta o nó onde está a chave procurada, caso ela tenha sido
        // encontrada, ou o nó onde ela deveria estar para manter a
        // ordenação da lista. O apontador anterior aponta o nó anterior
        // ao atual

        return achou;   // devolve o valor da variável achou, que indica
    }           // se a chave procurada foi ou não encontrado

    public void InserirEmOrdem(Dado dados) {
        if (!ExisteDado(dados)) // existeChave configura anterior e atual
        {                       // aqui temos certeza de que a chave não existe
            // guarda dados no novo nó
            NoLista<Dado> novo = new NoLista<Dado>(dados, null);
            if (EstaVazia())      // se a lista está vazia, então o
                InserirAntesDoInicio(novo);  // novo nó é o primeiro da lista
            else
                // testa se nova chave < primeira chave
                if (anterior == null && atual != null)
                    InserirAntesDoInicio(novo); // liga novo antes do primeiro
                else
                    // testa se nova chave > última chave
                    if (anterior != null && atual == null)
                        InserirAposFim(novo);
                    else
                        InserirNoMeio(novo);  // insere entre os nós anterior e atual
        }
    }

    private void InserirNoMeio(NoLista<Dado> novo) {
        // existeDado() encontrou intervalo de inclusão do novo nó

        anterior.prox = novo;   // liga anterior ao novo
        novo.prox = atual;      // e novo no atual

        if (anterior == ultimo)  // se incluiu ao final da lista,
            ultimo = novo;       // atualiza o apontador ultimo
        quantosNos++;            // incrementa número de nós da lista     	}
    }

    public boolean RemoverDado(Dado dados) {
        if (ExisteDado(dados)) {
            // existeDado posicionou atual e anterior
            RemoverNo( atual,  anterior);
            return true;
        }

        return false;
    }

    private void RemoverNo( NoLista<Dado>atual,  NoLista<Dado>anterior) {
        if (!EstaVazia()) {
            if (atual == primeiro) {
                primeiro = primeiro.prox;
                if (EstaVazia())
                    ultimo = null;
            } else if (atual == ultimo) {
                ultimo = anterior;
                ultimo.prox = null;
            } else
                anterior.prox = atual.prox;

            quantosNos--;
        }
    }

    public void IniciarPercursoSequencial() {
        primeiroAcessoDoPercurso = true;
        atual = primeiro;
        anterior = null;
    }

    public boolean PodePercorrer() {
        if (!primeiroAcessoDoPercurso) {
            anterior = atual;
            atual = atual.prox;
        } else
            primeiroAcessoDoPercurso = false;

        return atual != null;
    }

    private void ProcurarMenorDado
            ( NoLista<Dado>menorAteAgora,
              NoLista<Dado>anteriorAoMenor) {
        menorAteAgora = primeiro;
        anteriorAoMenor = null;

        IniciarPercursoSequencial();
        while (PodePercorrer()) {
            if (atual.info.compareTo(menorAteAgora.info) < 0) {
                anteriorAoMenor = anterior;
                menorAteAgora = atual;
            }
        }
    }

    public void Ordenar() {
        ListaSimples<Dado> ordenada = new ListaSimples<Dado>();
        NoLista<Dado> menorDeTodos = null,
                antesDoMenor = null;

        while (!this.EstaVazia()) {
            ProcurarMenorDado( menorDeTodos,  antesDoMenor);

            NoLista<Dado> novoNo = menorDeTodos;
            this.RemoverNo( menorDeTodos,  antesDoMenor);

            ordenada.InserirAposFim(novoNo);

        }

    }

    public boolean EstaVazia()
    {
            return (primeiro == null);
    }

    public NoLista<Dado> Primeiro()
    {
        return primeiro;
    }
    public void setPrimeiro(NoLista<Dado> Primeiro){
         this.primeiro = Primeiro;
    }

    public NoLista<Dado> Atual() {
            return atual;
    }

    public NoLista<Dado> Ultimo() {
            return ultimo;
    }

    public int QuantosNos() {
            return quantosNos;
    }

    // exercício 1
    public int ContagemDeNos() {
        int quantos = 0;
        atual = primeiro;
        while (atual != null) {
            quantos++;
            atual = atual.prox;
        }
        return quantos;
    }

// exercicio 3

    public void CasamentoCom(ListaSimples<Dado> outra,
                              ListaSimples<Dado>nova) {
        nova = new ListaSimples<Dado>();
        NoLista<Dado> a = null,
                b = null;
        while (!this.EstaVazia() && !outra.EstaVazia()) {
            a = this.primeiro;
            b = outra.primeiro;

            if (a.info.compareTo(b.info) < 0) {
                this.quantosNos--;
                this.primeiro = this.primeiro.prox; // avança na lista 1
                nova.InserirAposFim(a);
            } else if (b.info.compareTo(a.info) < 0) {
                outra.quantosNos--;
                outra.primeiro = outra.primeiro.prox; // avança na lista 2
                nova.InserirAposFim(b);
            } else {
                this.quantosNos--;
                outra.quantosNos--;
                this.primeiro = this.primeiro.prox; // avança na lista 1
                outra.primeiro = outra.primeiro.prox; // avança na lista 2
                nova.InserirAposFim(a);
            }
        }
        if (!this.EstaVazia())  // não acabou a lista 1
        {
            nova.ultimo.prox = this.primeiro;
            nova.ultimo = this.ultimo;
            nova.quantosNos += this.quantosNos;
        }

        if (!outra.EstaVazia()) {
            nova.ultimo.prox = outra.primeiro;
            nova.ultimo = outra.ultimo;
            nova.quantosNos += outra.quantosNos;
        }

        this.primeiro = this.ultimo = null;
        this.quantosNos = 0;

        outra = new ListaSimples<Dado>();
    }

    // exercício 4
    public void Inverter() {
        if (quantosNos > 1) {
            NoLista<Dado> um = primeiro;
            ultimo = primeiro;
            NoLista<Dado> dois = primeiro.prox;
            NoLista<Dado> tres = null;
            while (dois != null) {
                tres = dois.prox;
                dois.prox = um;
                um = dois;
                dois = tres;
            }
            primeiro = um;
            ultimo.prox = null;
        }
    }

//    public void LerDados(String nomeArquivo)    // fará a leitura e armazenamento dos dados do arquivo cujo nome é passado por parâmetro
//    {
//        if (nomeArquivo != null) // se arquivo for diferente de nulo
//        {
//            var arquivo = new StreamReader(nomeArquivo); //novo obj do tipo StreamReader
//
//            while (!arquivo.EndOfStream)  //enquanto não for fim de arquivo
//            {
//                var dado = new Dado(); //cria obj do tipo Dado
//
//                dado.LerRegistro(arquivo);  //le o dado
//
//
//                InserirEmOrdem(dado);  //inclui o dado
//            }
//
//            arquivo.Close(); //fecha o arquivo
//        }
//    }
    public void PosicionarNoPrimeiro()        // Posicionar atual no primeiro nó para ser acessado
    {
        if (!EstaVazia())         //se a lista no está vazia
            atual = primeiro;   //posiciona atual no primeiro nó
    }
    public Dado DadoAtual()  // retorna o dado atualmente visitado
    {
        if (atual != null) //se atual for diferente de null
            return atual.info; //sera retornado as informações do nó atual

        return null;//caso o atual seja nulo, nada sera retornado
    }
    public void AvancarPosicao()
    {
        if (!EstaVazia())         //se a lista no está vazia
            atual = atual.prox; //posiciona atual no próximo nó
    }
    public int getTamanho(){
        return quantosNos;
    }

    JSONObject jsonObject;
    JSONParser parser = new JSONParser();
    String nomeCidade;
    String coordenadaX;
    String coordenadaY;
    public void lerEmJason() {
        try {
            //Salva no objeto JSONObject o que o parse tratou do arquivo
            jsonObject = (JSONObject) parser.parse(new FileReader("/storage/emulated/0/WhatsApp/Media/WhatsApp Documents/Sent/cidades.json"));

            //Salva nas variaveis os dados retirados do arquivo
            nomeCidade = (String) jsonObject.get("nomeCidade");
            coordenadaX = (String) jsonObject.get("coordenadaX");
            coordenadaY = (String) jsonObject.get("coordenadaY");

            System.out.printf(
                    "Nome: %s\nSobrenome: %s\nEstado: %s\nPais: \n",
                    nomeCidade, coordenadaX, coordenadaY);
        }
        //Trata as exceptions que podem ser lançadas no decorrer do processo
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
