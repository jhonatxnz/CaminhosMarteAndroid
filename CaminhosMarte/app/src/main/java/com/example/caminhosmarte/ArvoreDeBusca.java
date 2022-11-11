package com.example.caminhosmarte;

import android.widget.ImageView;

import java.util.Queue;

public class ArvoreDeBusca<Dado extends Comparable<NoArvore<Dado>>>// indica que a classe específica terá um construtor vazio
{
    NoArvore<Dado> raiz,
            atual,       // indica o nó que está sendo visitado no momento
            antecessor;  // indica o nó ancestral daquele que está sendo visitado no momento
    int quantosNos;

    public ImageView imgArvore;

    public ImageView getOndeExibir()
    {
        return imgArvore;
    }
    public void setOndeExibir(ImageView imgArvore){ this.imgArvore = imgArvore; }

    public ArvoreDeBusca()
    {
        raiz = null;
        atual = null;
        antecessor = null;
        quantosNos = 0;
    }

    public NoArvore<Dado> getRaiz()
    {
        return raiz;
    }
    public void setRaiz(NoArvore<Dado> raiz){ this.raiz = raiz; }


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

    public NoArvore<Dado> getAtual() { return atual; }
    public void setAtual(NoArvore<Dado> atual) { this.atual = atual; }

    private String FazInOrdem(NoArvore<Dado> r)
    {
        if (r == null)
            return "";  // retorna cadeia vazia

        return FazInOrdem(r.esq) + " " +
                r.info.toString() + " " +
                FazInOrdem(r.dir);
    }

    private String FazPreOrdem(NoArvore<Dado> r)
    {
        if (r == null)
            return "";  // retorna cadeia vazia

        return r.info.toString() + " " +
                FazPreOrdem(r.esq) + " " +
                FazPreOrdem(r.dir);
    }

    public String FazPosOrdem(NoArvore<Dado> r)
    {
        if (r == null)
            return "";  // retorna cadeia vazia

        return FazPosOrdem(r.esq) + " " +
                FazPosOrdem(r.dir) + " " +
                r.info.toString();
    }


// Exercícios iniciais

    private boolean Eq(NoArvore<Dado> atualA,
                       NoArvore<Dado> atualB)
    {
        if (atualA == null && atualB == null)
            return true;

        if ((atualA == null) != (atualB == null)) // apenas um dos nós é
            return false; // uma é nulo e outra não é

        // os dois nós não são nulos
        if (atualA.info.compareTo((NoArvore<Dado>) atualB.info) != 0)
            return false; // Infos diferentes

        return Eq(atualA.esq, atualB.esq) &&
                Eq(atualA.dir, atualB.dir);
    }

    public boolean EquivaleA(ArvoreDeBusca<Dado> outraArvore)
    {
            /* . ambas são vazias
            ou
            .. Info(A) = Info(B) e
            ... Esq(A) eq Esq(B) e Dir(A) eq Dir(B)
            */
        return Eq(this.raiz, outraArvore.raiz);
    }

    private int QtosNos(NoArvore<Dado> noAtual)
    {
        if (noAtual == null)  // árvore vazia ou descendente de folha
            return 0;

        return 1 +                 // conta o nó atual
                QtosNos(noAtual.esq) + // conta nós da subárvore esquerda
                QtosNos(noAtual.dir);  // conta nós da subárvore direita
    }

    public int QtosNos()
    {
        return QtosNos(raiz);
    }
    private int QtasFolhas(NoArvore<Dado> noAtual)
    {
        if (noAtual == null)
            return 0;
        if (noAtual.esq == null && noAtual.dir == null) // noAtual é folha
            return 1;

        // noAtual não é folha, portanto procuramos as folhas de cada ramo e as contamos
        return QtasFolhas(noAtual.esq) + // conta folhas da subárvore esquerda
                QtasFolhas(noAtual.dir); // conta folhas da subárvore direita
    }

    public int QtasFolhas()
    {
        return QtasFolhas(raiz);
    }
    private boolean EstritamenteBinaria(NoArvore<Dado> noAtual)
    {
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

    public boolean EstritamenteBinaria()
    {
        return EstritamenteBinaria(raiz);
    }
    private int Altura(NoArvore<Dado> noAtual)
    {
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

    public int Altura()
    {
        return Altura(raiz);
    }

    private String EntreParenteses(NoArvore<Dado> noAtual)
    {
        String saida = "(";
        if (noAtual != null)
            saida += noAtual.info + ":" +
                    EntreParenteses(noAtual.esq) +
                    "," +
                    EntreParenteses(noAtual.dir);
        saida += ")";
        return saida;
    }

    public String EntreParenteses()
    {
        return EntreParenteses(raiz);
    }

    private void Trocar(NoArvore<Dado> noAtual)
    {
        if (noAtual != null)
        {
            NoArvore<Dado> auxiliar = noAtual.esq;
            noAtual.esq = noAtual.dir;
            noAtual.dir = auxiliar;
            Trocar(noAtual.esq);
            Trocar(noAtual.dir);
        }
    }

    public void Trocar()
    {
        Trocar(raiz);
    }

    private String PercursoPorNiveis(NoArvore<Dado> noAtual)
    {
        String saida = "";
        //Filalista<NoArvore<Dado>> umaFila = new FilaLista<NoArvore<Dado>>();

        var umaFila = new Queue<NoArvore<Dado>>();
        while (noAtual != null)
        {
            if (noAtual.esq != null)
                umaFila.Enqueue(noAtual.esq);
            if (noAtual.dir != null)
                umaFila.Enqueue(noAtual.dir);
            saida += " " + noAtual.info;
            if (umaFila.Count == 0)
                noAtual = null;
            else
                noAtual = umaFila.Dequeue();
        }
        return saida;
    }

    public String PercursoPorNiveis()
    {
        return PercursoPorNiveis(raiz);
    }

    int[] quantosNoNivel = new int[1000]; // GLOBAL NA CLASSE
    private int Largura(NoArvore<Dado> noAtual)
    {
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

    public int Largura()
    {
        return Largura(raiz);
    }
    public void ContarNosNosNiveis(NoArvore<Dado> noAtual, int qualNivel)
    {
        if (noAtual != null)
        {
            ++quantosNoNivel[qualNivel];
            ContarNosNosNiveis(noAtual.esq, qualNivel + 1);
            ContarNosNosNiveis(noAtual.dir, qualNivel + 1);
        }
    }

    boolean achou = false;
    private String EscreverAntecessores(NoArvore<Dado> atual, Dado procurado)
    {
        String saida = "";
        if (atual != null)
        {
            if (!achou)
                EscreverAntecessores(atual.esq, procurado);
            if (!achou)
                EscreverAntecessores(atual.dir, procurado);
            if (atual.info.compareTo((NoArvore<Dado>) procurado) == 0)
                achou = true;
            if (achou)
                saida += " " + atual.info;
        }
        return saida;
    }
    public String PreparaEscritaDosAntecessores(Dado procurado)
    {
        achou = false;
        return EscreverAntecessores(raiz, procurado);
    }
    public void DesenharArvore(boolean primeiraVez, NoArvore<Cidade> raiz,
                               int x, int y, double angulo, double incremento,
                               double comprimento, Graphics g)
    {
        int xf, yf;
        if (raiz != null)
        {
            Pen caneta = new Pen(Color.Red);
            xf = (int)Math.Round(x + Math.Cos(angulo) * comprimento);
            yf = (int)Math.Round(y + Math.Sin(angulo) * comprimento);

            if (primeiraVez)
                yf = 25;

            g.DrawLine(caneta, x, y, xf, yf);
            Thread.Sleep(200);
            DesenharArvore(false, raiz.esq, xf, yf, Math.PI / 2 + incremento,
                    incremento * 0.60, comprimento * 0.8, g);
            DesenharArvore(false, raiz.dir, xf, yf, Math.PI / 2 - incremento,
                    incremento * 0.6, comprimento * 0.8, g);
            Thread.Sleep(100);
            SolidBrush preenchimento = new SolidBrush(Color.MediumAquamarine);
            g.FillEllipse(preenchimento, xf - 25, yf - 15, 42, 30);
            g.DrawString(Convert.ToString(raiz.info.Nome.ToString()),
                    new Font("Comic Sans", 10),
                    new SolidBrush(Color.Black), xf - 23, yf - 7);
        }
    }
    public boolean Existe(Dado procurado)
    {
        antecessor = null;
        atual = raiz;

        while (atual != null)
        {
            if (atual.info.compareTo(procurado) == 0) // atual aponta o nó com o registro procurado, antecessor aponta seu "pai"
                return true;

            antecessor = atual;
            if (procurado.compareTo(atual.info) < 0)
                atual = atual.esq;     // Desloca apontador para o ramo à esquerda
            else
                atual = atual.dir;     // Desloca apontador para o ramo à direita
        }
        return false;       // Se local == null, a chave não existe
    }

    private boolean ExisteRec(NoArvore<Dado> local, Dado procurado)
    {
        if (local == null)
            return false;

        if (local.info.compareTo(procurado) == 0)
        {
            atual = local;
            return true;
        }

        antecessor = local;
        if (procurado.compareTo(local.info) < 0)
            return ExisteRec(local.esq, procurado);  // Desloca apontador na
        else                                         // próxima instância do
            return ExisteRec(local.dir, procurado);  // método
    }

    public boolean ExisteRegistro(Dado procurado)
    {
        return ExisteRec(raiz, procurado);
    }

    public void Incluir(Dado dadoLido)
    {
        Incluir(ref raiz, dadoLido);
    }

    private void Incluir(ref NoArvore<Dado> atual, Dado dadoLido)
    {
        if (atual == null)
        {
            atual = new NoArvore<Dado>(dadoLido);
        }
        else
        if (dadoLido.compareTo(atual.info) == 0)
            throw new Exception("Já existe esse registro!");
        else
        if (dadoLido.compareTo(atual.info) > 0)
        {
            NoArvore<Dado> apDireito = atual.dir;
            Incluir(ref apDireito, dadoLido);
            atual.dir = apDireito;
        }
        else
        {
            NoArvore<Dado> apEsquerdo = atual.esq;
            Incluir(ref apEsquerdo, dadoLido);
            atual.esq = apEsquerdo;
        }
    }

    private void Incluir2(ref NoArvore<Dado> atual, Dado dadoLido)
    {
        if (Existe(dadoLido))  // configura o valor do ponteiro antecessor
            throw new Exception("Já existe esse registro!");

        // dadoLido não existe na árvore ainda, vamos incluí-lo

        var novoNo = new NoArvore<Dado>(dadoLido);

        if (raiz == null)  // se a árvore está vazia, raiz
            raiz = novoNo;  // aPONTARÁ O NOVO NÓ

        else   // se a raiz não é nula, temos o atributo antecessor
            // apontando o pai do novo nó
            if (dadoLido.compareTo(antecessor.info) < 0)
                antecessor.esq = novoNo;
            else
                antecessor.dir = novoNo;
    }
    public void LerArquivoDeRegistros(String nomeArquivo)
    {
        raiz = null;
        Dado dado = new Dado();
        var origem = new FileStream(nomeArquivo, FileMode.OpenOrCreate);
        var arquivo = new BinaryReader(origem);
        MessageBox.Show("Tamanho do arquivo =" + origem.Length + "\n\nTamanho do registro = " + dado.TamanhoRegistro);
        int posicaoFinal = (int)origem.Length / dado.TamanhoRegistro - 1;
        Particionar(0, posicaoFinal, ref raiz);
        origem.Close();

        void Particionar(long inicio, long fim, ref NoArvore<Dado> atual)
        {
            if (inicio <= fim)
            {
                long meio = (inicio + fim) / 2;

                dado = new Dado();       // cria um objeto para armazenar os dados
                dado.LerRegistro(arquivo, meio); //
                atual = new NoArvore<Dado>(dado);
                var novoEsq = atual.Esq;
                Particionar(inicio, meio - 1, ref novoEsq);   // Particiona à esquerda
                atual.Esq = novoEsq;
                var novoDir = atual.Dir;
                Particionar(meio + 1, fim, ref novoDir);        // Particiona à direita
                atual.Dir = novoDir;
                atual.Altura = Math.Max(getAltura(atual.Esq), getAltura(atual.Dir)) + 1;
            }
        }

    }

    public void GravarArquivoDeRegistros(String nomeArquivo)
    {
        var destino = new FileStream(nomeArquivo, FileMode.Create);
        var arquivo = new BinaryWriter(destino);
        GravarInOrdem(raiz);
        arquivo.Close();

        void GravarInOrdem(NoArvore<Dado> r)
        {
            if (r != null)
            {
                GravarInOrdem(r.Esq);

                r.Info.GravarRegistro(arquivo);

                GravarInOrdem(r.Dir);
            }
        }
    }

    public void Inserir(Dado novosDados)
    {
        bool achou = false, fim = false;
        NoArvore<Dado> novoNo = new NoArvore<Dado>(novosDados);
        if (raiz == null)         // árvore vazia
            raiz = novoNo;
        else                      // árvore não-vazia
        {
            antecessor = null;
            atual = raiz;
            while (!achou && !fim)
            {
                antecessor = atual;
                if (novosDados.CompareTo(atual.Info) < 0)
                {
                    atual = atual.Esq;
                    if (atual == null)
                    {
                        antecessor.Esq = novoNo;
                        fim = true;
                    }
                }
                else
                if (novosDados.CompareTo(atual.Info) == 0)
                    achou = true;  // pode-se disparar uma exceção neste caso
                else
                {
                    atual = atual.Dir;
                    if (atual == null)
                    {
                        antecessor.Dir = novoNo;
                        fim = true;
                    }
                }
            }
        }
    }

    public void IncluirNovoRegistro(Dado novoRegistro)
    {
        if (Existe(novoRegistro))
            throw new Exception("Registro com chave repetida!");
        else
        {
            // o novoRegistro tem uma chave inexistente, então criamos
            // um novo nó para armazená-lo e depois ligamos esse nó na
            // árvore
            var novoNo = new NoArvore<Dado>(novoRegistro);

            // se a árvore está vazia, a raiz passará a apontar esse novo nó
            if (raiz == null)
                raiz = novoNo;
            else
                // nesse caso, antecessor aponta o pai do novo registro e
                // verificamos em qual ramo o novo nó será ligado
                if (novoRegistro.CompareTo(antecessor.Info) < 0)  // novo é menor que antecessor
                    antecessor.Esq = novoNo;        // vamos para a esquerda
                else
                    antecessor.Dir = novoNo;     // ou vamos para a direita

        }
    }
    public bool Excluir(Dado procurado)
    {
        return Excluir(ref raiz);


        bool Excluir(ref NoArvore<Dado> atual)
        {
            NoArvore<Dado> atualAnt;
            if (atual == null)
                return false;
            else
            if (atual.Info.CompareTo(procurado) > 0)
            {
                var temp = atual.Esq;
                bool result = Excluir(ref temp);
                atual.Esq = temp;
                return result;
            }
            else
            if (atual.Info.CompareTo(procurado) < 0)
            {
                var temp = atual.Dir;
                bool result = Excluir(ref temp);
                atual.Esq = temp;
                return result;
            }
            else
            {
                atualAnt = atual;   // nó a retirar
                if (atual.Dir == null)
                    atual = atual.Esq;
                else
                if (atual.Esq == null)
                    atual = atual.Dir;
                else
                {   // pai de 2 filhos
                    var temp = atual.Esq;
                    Rearranjar(ref temp, ref atualAnt);
                    atual.Esq = temp;
                    atualAnt = null;  // libera o nó excluído
                }
                return true;
            }
        }

        void Rearranjar(ref NoArvore<Dado> aux, ref NoArvore<Dado> atualAnt)
        {
            if (aux.Dir != null)
            {
                NoArvore<Dado> temp = aux.Dir;
                Rearranjar(ref temp, ref atualAnt);  // Procura Maior
                aux.Dir = temp;
            }
            else
            {                           // Guarda os dados do nó a excluir
                atualAnt.Info = aux.Info;   // troca conteúdo!
                atualAnt = aux;             // funciona com a passagem por referência
                aux = aux.Esq;
            }
        }

    }

    public bool ApagarNo(Dado registroARemover)
    {
        atual = raiz;
        antecessor = null;
        bool ehFilhoEsquerdo = true;
        while (atual.Info.CompareTo(registroARemover) != 0)  // enqto não acha a chave a remover
        {
            antecessor = atual;
            if (atual.Info.CompareTo(registroARemover) > 0)
            {
                ehFilhoEsquerdo = true;
                atual = atual.Esq;
            }
            else
            {
                ehFilhoEsquerdo = false;
                atual = atual.Dir;
            }

            if (atual == null)  // neste caso, a chave a remover não existe e não pode
                return false;   // ser excluída, dai retornamos falso indicando isso
        }  // fim do while

        // se fluxo de execução vem para este ponto, a chave a remover foi encontrada
        // e o ponteiro atual indica o nó que contém essa chave

        if ((atual.Esq == null) && (atual.Dir == null))  // é folha, nó com 0 filhos
        {
            if (atual == raiz)
                raiz = null;   // exclui a raiz e a árvore fica vazia
            else
            if (ehFilhoEsquerdo)        // se for filho esquerdo, o antecessor deixará
                antecessor.Esq = null;  // de ter um descendente esquerdo
            else                        // se for filho direito, o antecessor deixará de
                antecessor.Dir = null;  // apontar para esse filho

            atual = antecessor;  // feito para atual apontar um nó válido ao sairmos do método
        }
        else   // verificará as duas outras possibilidades, exclusão de nó com 1 ou 2 filhos
            if (atual.Dir == null)   // neste caso, só tem o filho esquerdo
            {
                if (atual == raiz)
                    raiz = atual.Esq;
                else
                if (ehFilhoEsquerdo)
                    antecessor.Esq = atual.Esq;
                else
                    antecessor.Dir = atual.Esq;
                atual = antecessor;
            }
            else
            if (atual.Esq == null)  // neste caso, só tem o filho direito
            {
                if (atual == raiz)
                    raiz = atual.Dir;
                else
                if (ehFilhoEsquerdo)
                    antecessor.Esq = atual.Dir;
                else
                    antecessor.Dir = atual.Dir;
                atual = antecessor;
            }
            else // tem os dois descendentes
            {
                NoArvore<Dado> menorDosMaiores = ProcuraMenorDosMaioresDescendentes(atual);
                atual.Info = menorDosMaiores.Info;
                menorDosMaiores = null; // para liberar o nó trocado da memória
            }
        return true;
    }

    public NoArvore<Dado> ProcuraMenorDosMaioresDescendentes(NoArvore<Dado> noAExcluir)
    {
        NoArvore<Dado> paiDoSucessor = noAExcluir;
        NoArvore<Dado> sucessor = noAExcluir;
        NoArvore<Dado> atual = noAExcluir.Dir;   // vai ao ramo direito do nó a ser excluído, pois este ramo contém
        // os descendentes que são maiores que o nó a ser excluído
        while (atual != null)
        {
            if (atual.Esq != null)
                paiDoSucessor = atual;
            sucessor = atual;
            atual = atual.Esq;
        }

        if (sucessor != noAExcluir.Dir)
        {
            paiDoSucessor.Esq = sucessor.Dir;
            sucessor.Dir = noAExcluir.Dir;
        }
        return sucessor;
    }

    public int alturaArvore(NoArvore<Dado> atual, ref bool balanceada)
    {
        int alturaDireita, alturaEsquerda, result;
        if (atual != null && balanceada)
        {
            alturaEsquerda = 1 + alturaArvore(atual.Esq, ref balanceada);
            alturaDireita = 1 + alturaArvore(atual.Dir, ref balanceada);
            result = Math.Max(alturaEsquerda, alturaDireita);

            //if (alturaDireita > alturaEsquerda)
            //    result = alturaDireita;
            //else
            //  result = alturaEsquerda;

            if (Math.Abs(alturaDireita - alturaEsquerda) > 1)
                balanceada = false;
        }
        else
            result = 0;
        return result;
    }

    public int getAltura(NoArvore<Dado> no)
    {
        if (no != null)
            return no.Altura;
        else
            return -1;
    }

    public void InserirBalanceado(Dado item)
    {
        raiz = InserirBalanceado(item, raiz);
    }
    private NoArvore<Dado> InserirBalanceado(Dado item, NoArvore<Dado> noAtual)
    {
        if (noAtual == null)
            noAtual = new NoArvore<Dado>(item);
        else
        {
            if (item.CompareTo(noAtual.Info) < 0)
            {
                noAtual.Esq = InserirBalanceado(item, noAtual.Esq);
                if (Math.Abs(getAltura(noAtual.Esq) - getAltura(noAtual.Dir)) == 2) // getAltura testa nulo
                    if (item.CompareTo(noAtual.Esq.Info) < 0)
                        noAtual = RotacaoSimplesComFilhoEsquerdo(noAtual);
                    else
                        noAtual = RotacaoDuplaComFilhoEsquerdo(noAtual);
            }
            else
            if (item.CompareTo(noAtual.Info) > 0)
            {
                noAtual.Dir = InserirBalanceado(item, noAtual.Dir);
                if (Math.Abs(getAltura(noAtual.Dir) - getAltura(noAtual.Esq)) == 2) // getAltura testa nulo
                    if (item.CompareTo(noAtual.Dir.Info) > 0)
                        noAtual = RotacaoSimplesComFilhoDireito(noAtual);
                    else
                        noAtual = RotacaoDuplaComFilhoDireito(noAtual);
            }
            //else ; - não faz nada, valor duplicado
            noAtual.Altura = Math.Max(getAltura(noAtual.Esq), getAltura(noAtual.Dir)) + 1;
        }
        return noAtual;
    }

    private NoArvore<Dado> RotacaoSimplesComFilhoEsquerdo(NoArvore<Dado> no)
    {
        NoArvore<Dado> temp = no.Esq;
        no.Esq = temp.Dir;
        temp.Dir = no;
        no.Altura = Math.Max(getAltura(no.Esq), getAltura(no.Dir)) + 1;
        temp.Altura = Math.Max(getAltura(temp.Esq), getAltura(no)) + 1;
        return temp;
    }

    private NoArvore<Dado> RotacaoSimplesComFilhoDireito(NoArvore<Dado> no)
    {
        NoArvore<Dado> temp = no.Dir;
        no.Dir = temp.Esq;
        temp.Esq = no;
        no.Altura = Math.Max(getAltura(no.Esq), getAltura(no.Dir)) + 1;
        temp.Altura = Math.Max(getAltura(temp.Dir), getAltura(no)) + 1;
        return temp;
    }
    private NoArvore<Dado> RotacaoDuplaComFilhoEsquerdo(NoArvore<Dado> no)
    {
        no.Esq = RotacaoSimplesComFilhoDireito(no.Esq);
        return RotacaoSimplesComFilhoEsquerdo(no);
    }
    private NoArvore<Dado> RotacaoDuplaComFilhoDireito(NoArvore<Dado> no)
    {
        no.Dir = RotacaoSimplesComFilhoEsquerdo(no.Dir);
        return RotacaoSimplesComFilhoDireito(no);
    }
    //Método para escrever nome das cidades no mapa
    public void DesenharNoMapa(NoArvore<Cidade> noAtual, Graphics g, PictureBox pb)
    {
        if (noAtual != null)
        {
            int x = (int)(noAtual.Info.X * pb.Width); //multiplica o valor de X pela largura do pictureBox
            int y = (int)(noAtual.Info.Y * pb.Height);//multiplica o valor de Y pela altura do pictureBox
            //pinta bolinha
            g.FillEllipse(new SolidBrush(Color.Black), new Rectangle(x, y, 10, 10));
            //escreve nome
            g.DrawString(noAtual.Info.Nome, new Font("Courier New", 9, FontStyle.Bold), Brushes.Black, x, y + 10);


            DesenharNoMapa(noAtual.Esq, g, pb); //Visita esq
            DesenharNoMapa(noAtual.Dir, g, pb); //Visita dir

            //DesenharArvore(false, raiz.Esq, xf, yf, Math.PI / 2 + incremento,
            //                       incremento * 0.60, comprimento * 0.8, g);
            //DesenharArvore(false, raiz.Dir, xf, yf, Math.PI / 2 - incremento,
            //                        incremento * 0.6, comprimento * 0.8, g);
        }
    }
}
