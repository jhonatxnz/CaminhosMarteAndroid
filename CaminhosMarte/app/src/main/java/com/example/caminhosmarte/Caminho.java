//package com.example.caminhosmarte;
//
//public class Caminho : IComparable<Caminho>, IRegistro<Caminho>
//{
//public const int tamCidadeOrigem = 15;
//public const int tamCidadeDestino = 15;
//
//private string nomeOrigem;
//private string nomeDestino;
//        int distancia, tempo, custo;
//
//
//        const int tamanhoRegistro =
//        tamCidadeOrigem +  // cidadeOrigem
//        tamCidadeDestino + //cidadeOrigem
//        sizeof(int) +    // distancia
//        sizeof(int) +    // tempo
//        sizeof(int);     // custo
//
//public string NomeOrigem { get => nomeOrigem; set => nomeOrigem = value.PadRight(tamCidadeOrigem, ' ').Substring(0, tamCidadeOrigem); }
//public string NomeDestino { get => nomeDestino; set => nomeDestino = value.PadRight(tamCidadeDestino, ' ').Substring(0, tamCidadeDestino); }
//
//public int Distancia { get => distancia; set => distancia = value; }
//public int Tempo { get => tempo; set => tempo = value; }
//public int Custo { get => custo; set => custo = value; }
//
//public Caminho()  // construtor default (construtor vazio), sem parâmetros
//        {
//        NomeOrigem = "";
//        NomeDestino = "";
//        Distancia = 0;
//        Tempo = 0;
//        Custo = 0;
//        }
//
//public Caminho(string nomeOrigem, string nomeDestino, int distancia, int tempo, int custo)
//        {
//        this.NomeOrigem = nomeOrigem;
//        this.NomeDestino = nomeDestino;
//        this.Distancia = distancia;
//        this.Tempo = tempo;
//        this.Custo = custo;
//        }
//
//
//public int CompareTo(Caminho outroCam)
//        {
//        string iguiOri = "";
//
//
//        string iguiDes = "";
//        iguiOri = nomeOrigem + nomeDestino;
//        iguiDes = outroCam.nomeOrigem + outroCam.nomeDestino;
//
//
//        return iguiOri.ToUpperInvariant().CompareTo(iguiDes.ToUpperInvariant());
//
//        }
//
//public override string ToString()
//        {
//        return NomeOrigem + "" + NomeOrigem;
//        }
//
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
//
