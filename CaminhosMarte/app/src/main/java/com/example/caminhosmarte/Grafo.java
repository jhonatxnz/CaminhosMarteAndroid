package com.example.caminhosmarte;


import android.annotation.SuppressLint;

import java.io.Console;
import java.util.List;
import java.util.Stack;

public class Grafo {
    private /*final*/ int NUM_VERTICES = 0;
    private Vertice[] vertices;
    private int adjMatrix[][];
    int numVerts;

    DistOriginal[] percurso;
    int infinity = Integer.MAX_VALUE;
    int verticeAtual; // global que indica o vértice atualmente sendo visitado
    int doInicioAteAtual; // global usada para ajustar menor caminho com Djikstra
    int nTree;

    public Grafo(int number) {
        NUM_VERTICES = number;
        vertices = new Vertice[NUM_VERTICES];
        adjMatrix = new int[NUM_VERTICES][NUM_VERTICES];
        numVerts = 0;
        nTree = 0;

        for (int j = 0; j < NUM_VERTICES; j++)
            for (int k = 0; k < NUM_VERTICES; k++)
                adjMatrix[j][k] = infinity;

        percurso = new DistOriginal[NUM_VERTICES];
    }

    public String Caminho(int inicioDoPercurso, int finalDoPercurso, List<String> lista)
    {
        for (int j = 0; j < numVerts; j++)
            vertices[j].foiVisitado = false;
        vertices[inicioDoPercurso].foiVisitado = true;
        for (int j = 0; j < numVerts; j++)
        {
            int tempDist = adjMatrix[inicioDoPercurso][j];
            percurso[j] = new DistOriginal(inicioDoPercurso, tempDist);
        }
        for (int nTree = 0; nTree < numVerts; nTree++)
        {
            int indiceDoMenor = obterMenor();
            int distanciaMinima = percurso[indiceDoMenor].distancia;
            verticeAtual = indiceDoMenor;
            doInicioAteAtual = percurso[indiceDoMenor].distancia;
            vertices[verticeAtual].foiVisitado = true;
            ajustarMenorCaminho(lista);
        }
        return exibirPercursos(inicioDoPercurso, finalDoPercurso, lista);
    }

    public void ajustarMenorCaminho(List<String> lista)
    {
        for (int coluna = 0; coluna < numVerts; coluna++)
            if (!vertices[coluna].foiVisitado) // para cada vértice ainda não visitado
            {

                int atualAteMargem = adjMatrix[verticeAtual][coluna];

                int doInicioAteMargem = doInicioAteAtual + atualAteMargem;

                int distanciaDoCaminho = percurso[coluna].distancia;
                if (doInicioAteMargem < distanciaDoCaminho)
                {
                    percurso[coluna].verticePai = verticeAtual;
                    percurso[coluna].distancia = doInicioAteMargem;
                    exibirTabela(lista);

                }
            }
        lista.add("==================Caminho ajustado==============");
        lista.add(" ");
    }

    @SuppressLint("SuspiciousIndentation")
    public void exibirTabela(List<String> lista)
    {
        String dist = "";
        lista.add("Vértice\tVisitado?\tPeso\tVindo de");
        for (int i = 0; i < numVerts; i++)
        {
            if (percurso[i].distancia == infinity)
                dist = "inf";
            else
                dist = String.valueOf(percurso[i].distancia);
            lista.add(vertices[i].rotulo + "\t" + vertices[i].foiVisitado +
                    "\t\t" + dist + "\t" + vertices[percurso[i].verticePai].rotulo);
        }
        lista.add("-----------------------------------------------------");
    }

    public int obterMenor()
    {
        int distanciaMinima = infinity;
        int indiceDaMinima = 0;
        for (int j = 0; j < numVerts; j++)
            if (!(vertices[j].foiVisitado) && (percurso[j].distancia <distanciaMinima))
            {
                distanciaMinima = percurso[j].distancia;
                indiceDaMinima = j;
            }
        return indiceDaMinima;
    }

    public void novoVertice(String label)
    {
        vertices[numVerts] = new Vertice(label);
//        numVerts++;
//        if (dgv != null) // se foi passado um dataGridView para exibição
//        { // é realizado o seu ajuste para a quantidade de vértices
//            dgv.RowCount = numVerts + 1;
//            dgv.ColumnCount = numVerts + 1;
//            dgv.Columns[numVerts].Width = 45;
//        }
    }
    public void novaAresta(int origem, int destino, int peso)
    {
        adjMatrix[origem][destino] = peso;
    }

    public int semSucessores() // encontra e retorna a linha de um vértice sem sucessores
    {
        boolean temAresta;
        for (int linha = 0; linha < numVerts; linha++)
        {
            temAresta = false;
            for (int col = 0; col < numVerts; col++)
                if (adjMatrix[linha][col] > 0)
                {
                    temAresta= true;
                    break;
                }
            if (!temAresta)
                return linha;
        }
        return -1;
    }
    public void removerVertice(int vert) {
        //  if (dgv != null)
        {
            //      MessageBox.Show($"Matriz de Adjacências antes de remover vértice {vert}");
            exibirAdjacencias();
            // }
            if (vert != numVerts - 1) {
                for (int j = vert; j < numVerts - 1; j++)// remove vértice do vetor
                    vertices[j] = vertices[j + 1];
// remove vértice da matriz
                for (int row = vert; row < numVerts; row++)
                    moverLinhas(row, numVerts - 1);
                for (int col = vert; col < numVerts; col++)
                    moverColunas(col, numVerts - 1);
            }
            numVerts--;
//            if (dgv != null) {
//                MessageBox.Show($"Matriz de Adjacências após remover vértice {vert}");
//                ExibirAdjacencias();
//                MessageBox.Show("Retornando à ordenação");
//            }
        }
    }

    public void exibirAdjacencias()
    {
//        dgv.RowCount = numVerts+1;
//        dgv.ColumnCount = numVerts+1;
//        for (int j = 0; j < numVerts; j++)
//        {
//            dgv.Rows[j + 1].Cells[0].Value = vertices[j].rotulo;
//            dgv.Rows[0].Cells[j+1].Value = vertices[j].rotulo;
//            for (int k = 0; k < numVerts; k++)
//                dgv.Rows[j + 1].Cells[k + 1].Value = Convert.ToString(adjMatrix[j, k]);
//        }
    }

    private void moverLinhas(int row, int length)
    {
        if (row != numVerts-1)
            for (int col = 0; col < length; col++)
                adjMatrix[row][col] = adjMatrix[row+1][col]; // desloca para excluir
    }

    private void moverColunas(int col, int length)
    {
        if (col != numVerts - 1)
            for (int row = 0; row < length; row++)
                adjMatrix[row][col] = adjMatrix[row][col+1]; // desloca para excluir
    }
    public void exibirVertice(int v) {
        System.out.println(vertices[v].rotulo + " ");
    }

    public String ordenacaoTopologica()
    {
        //IStack<String> gPilha = new IStack<String>(); //guarda a sequência de vértices
        Stack<String> gPilha = new Stack<String>();
        int origVerts = numVerts;
        while (numVerts > 0)
        {
            int currVertex = semSucessores();
            if (currVertex == -1)
                return "Erro: grafo possui ciclos.";
            gPilha.push(vertices[currVertex].rotulo); // empilha vértice
            removerVertice(currVertex);
        }
        String resultado = "Sequência da Ordenação Topológica: ";
        while (gPilha.size() > 0)
            resultado += gPilha.pop() + " "; // desempilha para exibir
        return resultado;
    }

    private int obterVerticeAdjacenteNaoVisitado(int v)
    {
        for (int j = 0; j <= numVerts - 1; j++)
            if ((adjMatrix[v][j] == 1) && (!vertices[j].foiVisitado))
                return j;

        return -1;
    }

    public void percursoEmProfundidade(/*TextBox txt*/)
    {
        //txt.Clear();
        //IStack<Integer> gPilha = new IStack<Integer>(); // para guardar a sequência de vértices
        Stack<Integer> gPilha = new Stack<Integer>();
        vertices[0].foiVisitado = true;
        exibirVertice(0);
        gPilha.push(0);
        int v;
        while (gPilha.size() > 0)
        {
            v = obterVerticeAdjacenteNaoVisitado(gPilha.peek());
            if (v == -1)
                gPilha.pop();
            else
            {
                vertices[v].foiVisitado = true;
                exibirVertice(v/*, txt*/);
                gPilha.push(v);
            }
        }
        for (int j = 0; j <= numVerts - 1; j++)
            vertices[j].foiVisitado = false;
    }

    void processarNo(int i)
    {
        System.out.println(vertices[i].rotulo);
    }

    public void percursoEmProfundidadeRec(int adjMatrix[][], int numVerts, int part)
    {
        int i;
        processarNo(part);
        vertices[part].foiVisitado = true;
        for (i = 0; i < numVerts; ++i)
            if (adjMatrix[part][i] == 1 && !vertices[i].foiVisitado)
                percursoEmProfundidadeRec(adjMatrix, numVerts, i);
    }

    public String exibirPercursos(int inicioDoPercurso, int finalDoPercurso, List<String> lista/*,
                                  ListBox lista*/)
    {
        String resultado = "";
        for (int j = 0; j < numVerts; j++)
        {
            resultado += vertices[j].rotulo + "=";
            if (percurso[j].distancia == infinity)
                resultado += "inf";
            else
                resultado += percurso[j].distancia+" ";
            String pai = vertices[percurso[j].verticePai].rotulo;
            resultado += "(" + pai + ") ";
        }
        lista.add(resultado);
        lista.add(" ");
        lista.add(" ");
        lista.add("Caminho entre " + vertices[inicioDoPercurso].rotulo +
                " e " + vertices[finalDoPercurso].rotulo);
        lista.add(" ");
        int onde = finalDoPercurso;
        Stack<String> pilha = new Stack<String>();
        int cont = 0;
        while (onde != inicioDoPercurso)
        {
            onde = percurso[onde].verticePai;
            pilha.push(vertices[onde].rotulo);
            cont++;
        }
        resultado = "";
        while (pilha.size() != 0)
        {
            resultado += pilha.pop();
            if (pilha.size() != 0)
                resultado += " --> ";
        }
        if ((cont == 1) && (percurso[finalDoPercurso].distancia == infinity))
            resultado = "Não há caminho";
        else
            resultado += " --> " + vertices[finalDoPercurso].rotulo;
        return resultado;
    }
}
