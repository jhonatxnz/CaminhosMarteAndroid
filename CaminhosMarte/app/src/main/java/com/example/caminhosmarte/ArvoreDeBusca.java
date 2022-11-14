package com.example.caminhosmarte;

import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.ArrayDeque;

public class ArvoreDeBusca<Dado extends Comparable<Dado>>// indica que a classe específica terá um construtor vazio
{
    NoArvore<Dado> raiz,
            atual,       // indica o nó que está sendo visitado no momento
            antecessor;  // indica o nó ancestral daquele que está sendo visitado no momento
    int quantosNos;

    public ImageView imgArvore;

    public ImageView getOndeExibir() {
        return imgArvore;
    }

    public void setOndeExibir(ImageView imgArvore) {
        this.imgArvore = imgArvore;
    }

    public ArvoreDeBusca() {
        raiz = null;
        atual = null;
        antecessor = null;
        quantosNos = 0;
    }

    public NoArvore<Dado> getRaiz() {
        return raiz;
    }

    public void setRaiz(NoArvore<Dado> raiz) {
        this.raiz = raiz;
    }


    public String getInOrdem()  // propriedade que gera a string do percurso in-ordem da árvore
    {
        return FazInOrdem(raiz);
    }

    public String getPreOrdem()  // propriedade que gera a string do percurso pre-ordem da árvore
    {
        return FazPreOrdem(raiz);
    }

    public String getPosOrdem()  // propriedade que gera a string do percurso pos-ordem da árvore
    {
        return FazPosOrdem(raiz);
    }

    public NoArvore<Dado> getAtual() {
        return atual;
    }

    public void setAtual(NoArvore<Dado> atual) {
        this.atual = atual;
    }

    private String FazInOrdem(NoArvore<Dado> r) {
        if (r == null)
            return "";  // retorna cadeia vazia

        return FazInOrdem(r.esq) + " " +
                r.info.toString() + " " +
                FazInOrdem(r.dir);
    }

    private String FazPreOrdem(NoArvore<Dado> r) {
        if (r == null)
            return "";  // retorna cadeia vazia

        return r.info.toString() + " " +
                FazPreOrdem(r.esq) + " " +
                FazPreOrdem(r.dir);
    }

    public String FazPosOrdem(NoArvore<Dado> r) {
        if (r == null)
            return "";  // retorna cadeia vazia

        return FazPosOrdem(r.esq) + " " +
                FazPosOrdem(r.dir) + " " +
                r.info.toString();
    }


// Exercícios iniciais

    private boolean Eq(NoArvore<Dado> atualA,
                       NoArvore<Dado> atualB) {
        if (atualA == null && atualB == null)
            return true;

        if ((atualA == null) != (atualB == null)) // apenas um dos nós é
            return false; // uma é nulo e outra não é

        // os dois nós não são nulos
        if (atualA.info.compareTo(atualB.info) != 0)
            return false; // Infos diferentes

        return Eq(atualA.esq, atualB.esq) &&
                Eq(atualA.dir, atualB.dir);
    }

    public boolean EquivaleA(ArvoreDeBusca<Dado> outraArvore) {
            /* . ambas são vazias
            ou
            .. Info(A) = Info(B) e
            ... Esq(A) eq Esq(B) e Dir(A) eq Dir(B)
            */
        return Eq(this.raiz, outraArvore.raiz);
    }

    private int QtosNos(NoArvore<Dado> noAtual) {
        if (noAtual == null)  // árvore vazia ou descendente de folha
            return 0;

        return 1 +                 // conta o nó atual
                QtosNos(noAtual.esq) + // conta nós da subárvore esquerda
                QtosNos(noAtual.dir);  // conta nós da subárvore direita
    }

    public int QtosNos() {
        return QtosNos(raiz);
    }

    private int QtasFolhas(NoArvore<Dado> noAtual) {
        if (noAtual == null)
            return 0;
        if (noAtual.esq == null && noAtual.dir == null) // noAtual é folha
            return 1;

        // noAtual não é folha, portanto procuramos as folhas de cada ramo e as contamos
        return QtasFolhas(noAtual.esq) + // conta folhas da subárvore esquerda
                QtasFolhas(noAtual.dir); // conta folhas da subárvore direita
    }

    public int QtasFolhas() {
        return QtasFolhas(raiz);
    }

    private boolean EstritamenteBinaria(NoArvore<Dado> noAtual) {
        if (noAtual == null)
            return true;

        // noAtual não é nulo
        if (noAtual.esq == null && noAtual.dir == null)
            return true;

        // um dos descendentes é nulo e o outro não é
        if (noAtual.esq == null && noAtual.dir != null)
            return false;
        if (noAtual.esq != null && noAtual.dir == null)
            return false;

        // se chegamos aqui, nenhum dos descendentes é nulo, dai testamos a
        // "estrita binariedade" das duas subárvores descendentes do nó atual
        return EstritamenteBinaria(noAtual.esq) &&
                EstritamenteBinaria(noAtual.dir);
    }

    public boolean EstritamenteBinaria() {
        return EstritamenteBinaria(raiz);
    }

    private int Altura(NoArvore<Dado> noAtual) {
        int alturaEsquerda,
                alturaDireita;
        if (noAtual == null)
            return 0;
        alturaEsquerda = Altura(noAtual.esq);
        alturaDireita = Altura(noAtual.dir);
        if (alturaEsquerda >= alturaDireita)
            return 1 + alturaEsquerda;
        return 1 + alturaDireita;
    }

    public int Altura() {
        return Altura(raiz);
    }

    private String EntreParenteses(NoArvore<Dado> noAtual) {
        String saida = "(";
        if (noAtual != null)
            saida += noAtual.info + ":" +
                    EntreParenteses(noAtual.esq) +
                    "," +
                    EntreParenteses(noAtual.dir);
        saida += ")";
        return saida;
    }

    public String EntreParenteses() {
        return EntreParenteses(raiz);
    }

    private void Trocar(NoArvore<Dado> noAtual) {
        if (noAtual != null) {
            NoArvore<Dado> auxiliar = noAtual.esq;
            noAtual.esq = noAtual.dir;
            noAtual.dir = auxiliar;
            Trocar(noAtual.esq);
            Trocar(noAtual.dir);
        }
    }

    public void Trocar() {
        Trocar(raiz);
    }

    private String PercursoPorNiveis(NoArvore<Dado> noAtual) {
        String saida = "";
        //Filalista<NoArvore<Dado>> umaFila = new FilaLista<NoArvore<Dado>>();

        //var umaFila = new ArrayDeque<NoArvore<Dado>>();
        ArrayDeque<NoArvore<Dado>> umaFila = new ArrayDeque<NoArvore<Dado>>();
        while (noAtual != null) {
            if (noAtual.esq != null)
                //umaFila.Enqueue(noAtual.esq);
                umaFila.add(noAtual.esq);
            if (noAtual.dir != null)
                //umaFila.Enqueue(noAtual.dir);
                umaFila.add(noAtual.dir);
            saida += " " + noAtual.info;
            if (umaFila.size() == 0) //if (umaFila.Count == 0)
                noAtual = null;
            else
                //noAtual = umaFila.Dequeue();
                noAtual = umaFila.remove();
        }
        return saida;
    }

    public String PercursoPorNiveis() {
        return PercursoPorNiveis(raiz);
    }

    int[] quantosNoNivel = new int[1000]; // GLOBAL NA CLASSE

    private int Largura(NoArvore<Dado> noAtual) {
        for (int i = 0; i < 1000; i++)
            quantosNoNivel[i] = 0;
        ContarNosNosNiveis(noAtual, 0);
        // acha o nível com o maior contador de nós
        int indiceMaior = 0;
        for (int i = 0; i < 1000; i++)
            if (quantosNoNivel[i] > quantosNoNivel[indiceMaior])
                indiceMaior = i;
        return quantosNoNivel[indiceMaior];
    }

    public int Largura() {
        return Largura(raiz);
    }

    public void ContarNosNosNiveis(NoArvore<Dado> noAtual, int qualNivel) {
        if (noAtual != null) {
            ++quantosNoNivel[qualNivel];
            ContarNosNosNiveis(noAtual.esq, qualNivel + 1);
            ContarNosNosNiveis(noAtual.dir, qualNivel + 1);
        }
    }

    boolean achou = false;

    private String EscreverAntecessores(NoArvore<Dado> atual, Dado procurado) {
        String saida = "";
        if (atual != null) {
            if (!achou)
                EscreverAntecessores(atual.esq, procurado);
            if (!achou)
                EscreverAntecessores(atual.dir, procurado);
            if (atual.info.compareTo(procurado) == 0)
                achou = true;
            if (achou)
                saida += " " + atual.info;
        }
        return saida;
    }

    public String PreparaEscritaDosAntecessores(Dado procurado) {
        achou = false;
        return EscreverAntecessores(raiz, procurado);
    }

//    public void DesenharArvore(boolean primeiraVez, NoArvore<Cidade> raiz,
//                               int x, int y, double angulo, double incremento,
//                               double comprimento, Graphics g) {
//        int xf, yf;
//        if (raiz != null) {
//            Pen caneta = new Pen(Color.Red);
//            xf = (int) Math.Round(x + Math.Cos(angulo) * comprimento);
//            yf = (int) Math.Round(y + Math.Sin(angulo) * comprimento);
//
//            if (primeiraVez)
//                yf = 25;
//
//            g.DrawLine(caneta, x, y, xf, yf);
//            Thread.Sleep(200);
//            DesenharArvore(false, raiz.esq, xf, yf, Math.PI / 2 + incremento,
//                    incremento * 0.60, comprimento * 0.8, g);
//            DesenharArvore(false, raiz.dir, xf, yf, Math.PI / 2 - incremento,
//                    incremento * 0.6, comprimento * 0.8, g);
//            Thread.Sleep(100);
//            SolidBrush preenchimento = new SolidBrush(Color.MediumAquamarine);
//            g.FillEllipse(preenchimento, xf - 25, yf - 15, 42, 30);
//            g.DrawString(Convert.ToString(raiz.info.Nome.ToString()),
//                    new Font("Comic Sans", 10),
//                    new SolidBrush(Color.Black), xf - 23, yf - 7);
//        }
//    }

    public boolean Existe(Dado procurado) {
        antecessor = null;
        atual = raiz;

        while (atual != null) {
            if (atual.info.compareTo( procurado) == 0) // atual aponta o nó com o registro procurado, antecessor aponta seu "pai"
                return true;

            antecessor = atual;
            if (procurado.compareTo( atual.info) < 0)
                atual = atual.esq;     // Desloca apontador para o ramo à esquerda
            else
                atual = atual.dir;     // Desloca apontador para o ramo à direita
        }
        return false;       // Se local == null, a chave não existe
    }

    private boolean ExisteRec(NoArvore<Dado> local, Dado procurado) {
        if (local == null)
            return false;

        if (local.info.compareTo(procurado) == 0) {
            atual = local;
            return true;
        }

        antecessor = local;
        if (procurado.compareTo( local.info) < 0)
            return ExisteRec(local.esq, procurado);  // Desloca apontador na
        else                                         // próxima instância do
            return ExisteRec(local.dir, procurado);  // método
    }

    public boolean ExisteRegistro(Dado procurado) {
        return ExisteRec(raiz, procurado);
    }

    public void Incluir(Dado dadoLido)  throws Exception{
        Incluir( raiz, dadoLido);
    }

    private void Incluir( NoArvore<Dado>atual, Dado dadoLido) throws Exception {
        if (atual == null) {
            atual = new NoArvore<Dado>(dadoLido);
        } else if (dadoLido.compareTo( atual.info) == 0)
            throw new Exception("Já existe esse registro!");
        else if (dadoLido.compareTo( atual.info) > 0) {
            NoArvore<Dado> apDireito = atual.dir;
            Incluir( apDireito, dadoLido);
            atual.dir = apDireito;
        } else {
            NoArvore<Dado> apEsquerdo = atual.esq;
            Incluir( apEsquerdo, dadoLido);
            atual.esq = apEsquerdo;
        }
    }

    private void Incluir2( NoArvore<Dado> atual, Dado dadoLido) throws Exception {
        if (Existe(dadoLido))  // configura o valor do ponteiro antecessor
            throw new Exception("Já existe esse registro!");

        // dadoLido não existe na árvore ainda, vamos incluí-lo

        NoArvore<Dado> novoNo = new NoArvore<Dado>(dadoLido);

        if (raiz == null)  // se a árvore está vazia, raiz
            raiz = novoNo;  // aPONTARÁ O NOVO NÓ

        else   // se a raiz não é nula, temos o atributo antecessor
            // apontando o pai do novo nó
            if (dadoLido.compareTo(antecessor.info) < 0)
                antecessor.esq = novoNo;
            else
                antecessor.dir = novoNo;
    }

//    public void LerArquivoDeRegistros(String nomeArquivo) {
//        raiz = null;
//        Dado dado = new Dado();
//        var origem = new FileStream(nomeArquivo, FileMode.OpenOrCreate);
//        var arquivo = new BinaryReader(origem);
//        MessageBox.Show("Tamanho do arquivo =" + origem.Length + "\n\nTamanho do registro = " + dado.TamanhoRegistro);
//        int posicaoFinal = (int) origem.Length / dado.TamanhoRegistro - 1;
//        Particionar(0, posicaoFinal,  raiz);
//        origem.Close();
//
//        void Particionar ( long inicio, long fim, ref NoArvore<Dado> atual)
//        {
//            if (inicio <= fim) {
//                long meio = (inicio + fim) / 2;
//
//                dado = new Dado();       // cria um objeto para armazenar os dados
//                dado.LerRegistro(arquivo, meio); //
//                atual = new NoArvore<Dado>(dado);
//                var novoEsq = atual.Esq;
//                Particionar(inicio, meio - 1, ref novoEsq);   // Particiona à esquerda
//                atual.Esq = novoEsq;
//                var novoDir = atual.Dir;
//                Particionar(meio + 1, fim, ref novoDir);        // Particiona à direita
//                atual.Dir = novoDir;
//                atual.Altura = Math.Max(getAltura(atual.Esq), getAltura(atual.Dir)) + 1;
//            }
//        }
//
//    }
//
//    public void GravarArquivoDeRegistros(String nomeArquivo) {
//        var destino = new FileStream(nomeArquivo, FileMode.Create);
//        var arquivo = new BinaryWriter(destino);
//        GravarInOrdem(raiz);
//        arquivo.Close();
//
//        void GravarInOrdem (NoArvore<Dado> r)
//        {
//            if (r != null) {
//                GravarInOrdem(r.Esq);
//
//                r.Info.GravarRegistro(arquivo);
//
//                GravarInOrdem(r.Dir);
//            }
//        }
//    }

    public void Inserir(Dado novosDados) {
        boolean achou = false, fim = false;
        NoArvore<Dado> novoNo = new NoArvore<Dado>(novosDados);
        if (raiz == null)         // árvore vazia
            raiz = novoNo;
        else                      // árvore não-vazia
        {
            antecessor = null;
            atual = raiz;
            while (!achou && !fim) {
                antecessor = atual;
                if (novosDados.compareTo(atual.info) < 0) {
                    atual = atual.esq;
                    if (atual == null) {
                        antecessor.esq = novoNo;
                        fim = true;
                    }
                } else if (novosDados.compareTo(atual.info) == 0)
                    achou = true;  // pode-se disparar uma exceção neste caso
                else {
                    atual = atual.dir;
                    if (atual == null) {
                        antecessor.dir = novoNo;
                        fim = true;
                    }
                }
            }
        }
    }

    public void IncluirNovoRegistro(Dado novoRegistro) throws Exception {
        if (Existe(novoRegistro))
            throw new Exception("Registro com chave repetida!");
        else {
            // o novoRegistro tem uma chave inexistente, então criamos
            // um novo nó para armazená-lo e depois ligamos esse nó na
            // árvore
            NoArvore<Dado> novoNo = new NoArvore<Dado>(novoRegistro);

            // se a árvore está vazia, a raiz passará a apontar esse novo nó
            if (raiz == null)
                raiz = novoNo;
            else
                // nesse caso, antecessor aponta o pai do novo registro e
                // verificamos em qual ramo o novo nó será ligado
                if (novoRegistro.compareTo(antecessor.info) < 0)  // novo é menor que antecessor
                    antecessor.esq = novoNo;        // vamos para a esquerda
                else
                    antecessor.esq = novoNo;     // ou vamos para a direita

        }
    }

//    public boolean Excluir(Dado procurado) {
//        return Excluir( raiz);
//
//         boolean Excluir ( NoArvore<Dado> atual)
//        {
//            NoArvore<Dado> atualAnt;
//            if (atual == null)
//                return false;
//            else if (atual.info.compareTo((NoArvore<Dado>) procurado) > 0) {
//                NoArvore<Dado> temp = atual.esq;
//                boolean result = Excluir( (Dado) temp);
//                atual.esq = temp;
//                return result;
//            } else if (atual.info.compareTo((NoArvore<Dado>)procurado) < 0) {
//                NoArvore<Dado> temp = atual.dir;
//                boolean result = Excluir((Dado) temp);
//                atual.esq = temp;
//                return result;
//            } else {
//                atualAnt = atual;   // nó a retirar
//                if (atual.dir == null)
//                    atual = atual.esq;
//                else if (atual.esq == null)
//                    atual = atual.dir;
//                else {   // pai de 2 filhos
//                    NoArvore<Dado> temp = atual.esq;
//                    Rearranjar( temp,  atualAnt);
//                    atual.esq = temp;
//                    atualAnt = null;  // libera o nó excluído
//                }
//                return true;
//            }
//        }
//    }
    void Rearranjar ( NoArvore<Dado> aux,  NoArvore<Dado> atualAnt)
    {
        if (aux.dir != null) {
            NoArvore<Dado> temp = aux.dir;
            Rearranjar( temp,  atualAnt);  // Procura Maior
            aux.dir = temp;
        } else {                           // Guarda os dados do nó a excluir
            atualAnt.info = aux.info;   // troca conteúdo!
            atualAnt = aux;             // funciona com a passagem por referência
            aux = aux.esq;
        }
    }
    public boolean ApagarNo(Dado registroARemover) {
        atual = raiz;
        antecessor = null;
        boolean ehFilhoEsquerdo = true;
        while (atual.info.compareTo(registroARemover) != 0)  // enqto não acha a chave a remover
        {
            antecessor = atual;
            if (atual.info.compareTo(registroARemover) > 0) {
                ehFilhoEsquerdo = true;
                atual = atual.esq;
            } else {
                ehFilhoEsquerdo = false;
                atual = atual.dir;
            }

            if (atual == null)  // neste caso, a chave a remover não existe e não pode
                return false;   // ser excluída, dai retornamos falso indicando isso
        }  // fim do while

        // se fluxo de execução vem para este ponto, a chave a remover foi encontrada
        // e o ponteiro atual indica o nó que contém essa chave

        if ((atual.esq == null) && (atual.dir == null))  // é folha, nó com 0 filhos
        {
            if (atual == raiz)
                raiz = null;   // exclui a raiz e a árvore fica vazia
            else if (ehFilhoEsquerdo)        // se for filho esquerdo, o antecessor deixará
                antecessor.esq = null;  // de ter um descendente esquerdo
            else                        // se for filho direito, o antecessor deixará de
                antecessor.dir = null;  // apontar para esse filho

            atual = antecessor;  // feito para atual apontar um nó válido ao sairmos do método
        } else   // verificará as duas outras possibilidades, exclusão de nó com 1 ou 2 filhos
            if (atual.dir == null)   // neste caso, só tem o filho esquerdo
            {
                if (atual == raiz)
                    raiz = atual.esq;
                else if (ehFilhoEsquerdo)
                    antecessor.esq = atual.esq;
                else
                    antecessor.dir = atual.esq;
                atual = antecessor;
            } else if (atual.esq == null)  // neste caso, só tem o filho direito
            {
                if (atual == raiz)
                    raiz = atual.dir;
                else if (ehFilhoEsquerdo)
                    antecessor.esq = atual.esq;
                else
                    antecessor.dir = atual.dir;
                atual = antecessor;
            } else // tem os dois descendentes
            {
                NoArvore<Dado> menorDosMaiores = ProcuraMenorDosMaioresDescendentes(atual);
                atual.info = menorDosMaiores.info;
                menorDosMaiores = null; // para liberar o nó trocado da memória
            }
        return true;
    }

    public NoArvore<Dado> ProcuraMenorDosMaioresDescendentes(NoArvore<Dado> noAExcluir) {
        NoArvore<Dado> paiDoSucessor = noAExcluir;
        NoArvore<Dado> sucessor = noAExcluir;
        NoArvore<Dado> atual = noAExcluir.dir;   // vai ao ramo direito do nó a ser excluído, pois este ramo contém
        // os descendentes que são maiores que o nó a ser excluído
        while (atual != null) {
            if (atual.esq != null)
                paiDoSucessor = atual;
            sucessor = atual;
            atual = atual.esq;
        }

        if (sucessor != noAExcluir.dir) {
            paiDoSucessor.esq = sucessor.dir;
            sucessor.dir = noAExcluir.dir;
        }
        return sucessor;
    }

    public int alturaArvore(NoArvore<Dado> atual,  boolean balanceada) {
        int alturaDireita, alturaEsquerda, result;
        if (atual != null && balanceada) {
            alturaEsquerda = 1 + alturaArvore(atual.esq,  balanceada);
            alturaDireita = 1 + alturaArvore(atual.dir,  balanceada);
            result = Math.max(alturaEsquerda, alturaDireita);

            //if (alturaDireita > alturaEsquerda)
            //    result = alturaDireita;
            //else
            //  result = alturaEsquerda;

            if (Math.abs(alturaDireita - alturaEsquerda) > 1)
                balanceada = false;
        } else
            result = 0;
        return result;
    }

    public int getAltura(NoArvore<Dado> no) {
        if (no != null)
            return no.altura;
        else
            return -1;
    }

    public void InserirBalanceado(Dado item) {
        raiz = InserirBalanceado(item, raiz);
    }

    private NoArvore<Dado> InserirBalanceado(Dado item, NoArvore<Dado> noAtual) {
        if (noAtual == null)
            noAtual = new NoArvore<Dado>(item);
        else {
            if (item.compareTo(noAtual.info) < 0) {
                noAtual.esq = InserirBalanceado(item, noAtual.esq);
                if (Math.abs(getAltura(noAtual.esq) - getAltura(noAtual.dir)) == 2) // getAltura testa nulo
                    if (item.compareTo(noAtual.esq.info) < 0)
                        noAtual = RotacaoSimplesComFilhoEsquerdo(noAtual);
                    else
                        noAtual = RotacaoDuplaComFilhoEsquerdo(noAtual);
            } else if (item.compareTo(noAtual.info) > 0) {
                noAtual.dir = InserirBalanceado(item, noAtual.dir);
                if (Math.abs(getAltura(noAtual.dir) - getAltura(noAtual.esq)) == 2) // getAltura testa nulo
                    if (item.compareTo(noAtual.dir.info) > 0)
                        noAtual = RotacaoSimplesComFilhoDireito(noAtual);
                    else
                        noAtual = RotacaoDuplaComFilhoDireito(noAtual);
            }
            //else ; - não faz nada, valor duplicado
            noAtual.altura = Math.max(getAltura(noAtual.esq), getAltura(noAtual.dir)) + 1;
        }
        return noAtual;
    }

    private NoArvore<Dado> RotacaoSimplesComFilhoEsquerdo(NoArvore<Dado> no) {
        NoArvore<Dado> temp = no.esq;
        no.esq = temp.dir;
        temp.dir = no;
        no.altura = Math.max(getAltura(no.esq), getAltura(no.dir)) + 1;
        temp.altura = Math.max(getAltura(temp.esq), getAltura(no)) + 1;
        return temp;
    }

    private NoArvore<Dado> RotacaoSimplesComFilhoDireito(NoArvore<Dado> no) {
        NoArvore<Dado> temp = no.dir;
        no.dir = temp.esq;
        temp.esq = no;
        no.altura = Math.max(getAltura(no.esq), getAltura(no.dir)) + 1;
        temp.altura = Math.max(getAltura(temp.dir), getAltura(no)) + 1;
        return temp;
    }

    private NoArvore<Dado> RotacaoDuplaComFilhoEsquerdo(NoArvore<Dado> no) {
        no.esq = RotacaoSimplesComFilhoDireito(no.esq);
        return RotacaoSimplesComFilhoEsquerdo(no);
    }

    private NoArvore<Dado> RotacaoDuplaComFilhoDireito(NoArvore<Dado> no) {
        no.dir = RotacaoSimplesComFilhoEsquerdo(no.dir);
        return RotacaoSimplesComFilhoDireito(no);
    }

    //Método para escrever nome das cidades no mapa
//    public void DesenharNoMapa(NoArvore<Cidade> noAtual, Graphics g, PictureBox pb) {
//        if (noAtual != null) {
//            int x = (int) (noAtual.info.X * pb.Width); //multiplica o valor de X pela largura do pictureBox
//            int y = (int) (noAtual.info.Y * pb.Height);//multiplica o valor de Y pela altura do pictureBox
//            //pinta bolinha
//            g.FillEllipse(new SolidBrush(Color.Black), new Rectangle(x, y, 10, 10));
//            //escreve nome
//            g.DrawString(noAtual.info.Nome, new Font("Courier New", 9, FontStyle.Bold), Brushes.Black, x, y + 10);
//
//
//            DesenharNoMapa(noAtual.esq, g, pb); //Visita esq
//            DesenharNoMapa(noAtual.dir, g, pb); //Visita dir
//
//            //DesenharArvore(false, raiz.Esq, xf, yf, Math.PI / 2 + incremento,
//            //                       incremento * 0.60, comprimento * 0.8, g);
//            //DesenharArvore(false, raiz.Dir, xf, yf, Math.PI / 2 - incremento,
//            //                        incremento * 0.6, comprimento * 0.8, g);
//        }
//    }
    JSONObject jsonObject;
    JSONParser parser = new JSONParser();
    String nomeCidade;
        String coordenadaX;
        String coordenadaY;

    public void lerEmJason() {
        try {
            //Salva no objeto JSONObject o que o parse tratou do arquivo
            jsonObject = (JSONObject) parser.parse(new FileReader("C:\\Users\\Jhon\\Documents\\Cotuca\\4_Semestre\\EST2-Chico\\CaminhosMarteEST2\\cidades.json"));

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
//
}
