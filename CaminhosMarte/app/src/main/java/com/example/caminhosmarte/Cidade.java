package com.example.caminhosmarte;

import java.util.ArrayList;
import java.util.List;

public class Cidade implements Comparable<Cidade> {
    List<Cidade> cidadesJson = new ArrayList<Cidade>();
    public final int tamanhoNome = 15;
    private String nomeCidade;
    double coordenadaX, coordenadaY;
    //atributo para os caminhos
    ListaSimples<Caminho> cams;


    //        final int tamanhoRegistro =
//        tamanhoNome;       // nome
//        sizeof(double) +    // x
//        sizeof(double);     // y
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public String getNome() {
        return nomeCidade;
    }

    public void setNome(String nome) {
        this.nomeCidade = padRight(nome, tamanhoNome);
    }

    public double getX() {
        return coordenadaX;
    }

    public void setX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getY() {
        return coordenadaY;
    }

    public void setY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public ListaSimples<Caminho> getCams() {
        return cams;
    }

    public void setCams(ListaSimples<Caminho> cams) {
        this.cams = cams;
    }

    public Cidade()  // construtor default (construtor vazio), sem parâmetros
    {
        setNome("");
        setX(0);
        setY(0);
        setCams(null);
    }


    public Cidade(String nome, double x, double y, ListaSimples<Caminho> cams) {
        setNome(nome);
        setX(x);
        setY(y);
        setCams(cams);
    }

    public Cidade(String nome) {
        setNome(nome);
    }

    //CompareTo de cidade por nome
    public int compareTo(Cidade outraCid) {
        return nomeCidade.toUpperCase().compareTo(outraCid.nomeCidade.toUpperCase());
    }

//    public override String ToString() {
//        return getNome() + "";
//    }
    public  String ToString() {
        return getNome() + "";
    }


}

//public int TamanhoRegistro { get => tamanhoRegistro; }
//
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
//        char[] umNome = new char[tamanhoNome];   // criamos um vetor de 30 caracteres
//        umNome = arquivo.ReadChars(tamanhoNome); // lêmos 30 caracteres do arquivo e guardamosno vertor umNome
//        string nomeLido = "";
//        for (int i = 0; i < tamanhoNome; i++) // montamos uma variável string com os caracteres do vetor umNome
//        nomeLido += umNome[i];
//        Nome = nomeLido;                  // armazenamos a string montada acima no campo nome do Funcionário
//
//        X = arquivo.ReadDouble();     // lê um real de 8 bytes
//        Y = arquivo.ReadDouble();     // lê um real de 8 bytes
//        MessageBox.Show(Nome + " " + X + " " + Y);
//
//
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
//
//        char[] umNome = new char[tamanhoNome];  // cria vetor de char para armazenar o nome
//        for (int i = 0; i < tamanhoNome; i++)
//        umNome[i] = nome[i];                // copia os caracteres do campo nome para o vetor de char
//        arquivo.Write(umNome);                  // grava o vetor de char no arquivo (com tamanho 30)
//
//        arquivo.Write(X);                 // escreve os 8 bytes do campo salario no arquivo
//        arquivo.Write(Y);                // escreve o byte do campo afastado no arquivo
//
//        var cid = new Cidade(nome, X, Y, null);
//
//        cidadesJson.Add(cid);
//        MessageBox.Show("Printando cidades json: " + cid.nome);
//
//        cid.Serializar(cidadesJson, @"C:\temp\cidades.json");
//        }
//
//        }
////Método que serializa
//public bool Serializar(List<Cidade> lista, string path) {
//        var strJson = JsonConvert.SerializeObject(lista, Formatting.Indented);
//        return SaveFileDialog(strJson, path);
//        }
////Método salva arquivo json
//private bool SaveFileDialog(string strJson, string path)
//        {
//        try {
//        using (StreamWriter sw = new StreamWriter(path)) {
//        sw.WriteLine(strJson);
//        }
//        return true;
//        }
//        catch (Exception err) {
//
//        }
//        return true;
//        }
//
//public Cidade LerRegistro(StreamReader arquivo)
//        {
//        throw new NotImplementedException();
//        }
//
//        @Override
//        public int compareTo(Cidade cidade) {
//            return 0;
//        }
//    }
