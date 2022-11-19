package com.example.caminhosmarte;

import java.util.Locale;

public class Caminho implements Comparable<Caminho> {
    public final int tamCidadeOrigem = 15;
    public final int tamCidadeDestino = 15;

    private String origem;
    private String destino;
    int distancia, tempo, custo;


//    final int tamanhoRegistro =
//            tamCidadeOrigem +  // cidadeOrigem
//                    tamCidadeDestino; //cidadeOrigem
//        sizeof(int) +    // distancia
//        sizeof(int) +    // tempo
//        sizeof(int);     // custo

    public String getNomeOrigem() {
        return origem;
    }

    public void setNomeOrigem(String origem) {
        this.origem = origem;
    }

    public String getNomeDestino() {
        return destino;
    }

    public void setNomeDestino(String nomeOrigem) {
        this.destino = destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public Caminho()  // construtor default (construtor vazio), sem parâmetros
    {
        this.origem = "";
        this.destino = "";
        this.custo = 0;
        this.tempo = 0;
        this.distancia = 0;
    }

    public Caminho(String nomeOrigem, String nomeDestino, int distancia, int tempo, int custo) {
        this.origem = nomeOrigem;
        this.destino = nomeDestino;
        this.custo = custo;
        this.tempo = tempo;
        this.distancia = distancia;
    }


    public int compareTo(Caminho outroCam) {
        String iguiOri = "";


        String iguiDes = "";
        iguiOri = origem + destino;
        iguiDes = outroCam.origem + outroCam.destino;


        return iguiOri.toUpperCase().compareTo(iguiDes.toUpperCase());
    }

    public int CompareTo(Caminho caminho) {
        return 0;
    }
}

//public int TamanhoRegistro { get => tamanhoRegistro; }
//public void LerRegistro(BinaryReader arquivo, long qualRegistro)
//        {
//        if (arquivo != null)  // arquivo de leitura foi instanciado (já fica aberto)
//        try
//        {
//        long qtosBytes = qualRegistro * TamanhoRegistro;  // offset no arquivo
//        // (número de byrtes pulados para posicionar no registro desejado)
//        arquivo.BaseStream.Seek(qtosBytes, SeekOrigin.Begin);  // posicionamento no byte inicial do registro
//
//        // lemos cada um dos campos do registro separadamente
//
//        char[] umNomeOri = new char[tamCidadeOrigem];   // criamos um vetor de 30 caracteres
//        umNomeOri = arquivo.ReadChars(tamCidadeOrigem); // lêmos 30 caracteres do arquivo e guardamosno vertor umNome
//        char[] umNomeDes = new char[tamCidadeDestino];   // criamos um vetor de 30 caracteres
//        umNomeDes = arquivo.ReadChars(tamCidadeDestino); // lêmos 30 caracteres do arquivo e guardamosno vertor umNome
//        string nomeLido = "";
//        string nomeLidoo = "";
//
//        for (int i = 0; i < tamCidadeOrigem; i++) // montamos uma variável string com os caracteres do vetor umNome
//        nomeLido += umNomeOri[i];
//
//        for (int i = 0; i < tamCidadeDestino; i++) // montamos uma variável string com os caracteres do vetor umNome
//        nomeLidoo += umNomeDes[i];
//        NomeOrigem = nomeLido;                  // armazenamos a string montada acima no campo nome do Funcionário
//        NomeDestino = nomeLidoo;
//
//        MessageBox.Show("Ori: " + NomeOrigem + " " + "Des: " + NomeDestino);
//
//        Distancia = arquivo.ReadInt32();     // lê um real de 8 bytes
//        Tempo = arquivo.ReadInt32();
//        Custo = arquivo.ReadInt32();
//        }
//        catch (Exception e)
//        {
//        MessageBox.Show(e.Message);
//        }
//        }
//
//public void GravarRegistro(BinaryWriter arquivo)
//        {
//        if (arquivo != null)
//        {
//        // cria vetor de char para armazenar o nome
//        char[] umNomeOri = new char[tamCidadeOrigem];
//        char[] umNomeDes = new char[tamCidadeDestino];
//
//        for (int i = 0; i < tamCidadeOrigem; i++)
//        umNomeOri[i] = nomeOrigem[i];
//        // copia os caracteres do campo nome para o vetor de char
//        for (int i = 0; i < tamCidadeDestino; i++)
//        umNomeDes[i] = nomeDestino[i];                // copia os caracteres do campo nome para o vetor de char
//
//        arquivo.Write(umNomeOri);
//        arquivo.Write(umNomeDes);                  // grava o vetor de char no arquivo (com tamanho 30)
//        arquivo.Write(Distancia);                 // escreve os 8 bytes do campo salario no arquivo
//        arquivo.Write(Tempo);                // escreve o byte do campo afastado no arquivo
//        arquivo.Write(Custo);
//
//        }
//        }
//
//public Caminho LerRegistro(StreamReader arquivo)
//        {
//        throw new NotImplementedException();
//        }
//        }

