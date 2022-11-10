//package com.example.caminhosmarte;
//
//public class NoArvore<Dado> : IComparable<NoArvore<Dado>> where Dado : IComparable<Dado>
//  {
//          Dado info;
//          NoArvore<Dado> esq;
//        NoArvore<Dado> dir;
//        int altura;
//private bool estaMarcadoParaMorrer;
//
//public NoArvore(Dado informacao)
//        {
//        this.Info = informacao;
//        this.esq = null;
//        this.dir = null;
//        this.Altura = 0;
//        this.estaMarcadoParaMorrer = false;
//        }
//
//public NoArvore(Dado dados, NoArvore<Dado> esquerdo, NoArvore<Dado> direito, int altura)
//        {
//        this.Info = dados;
//        this.Esq = esquerdo;
//        this.Dir = direito;
//        this.Altura = altura;
//        }
//public int Altura { get => altura; set => altura = value; }
//public Dado Info { get => info; set => info = value; }
//public NoArvore<Dado> Esq { get => esq; set => esq = value; }
//public NoArvore<Dado> Dir { get => dir; set => dir = value; }
//public bool EstaMarcadoParaMorrer
//        { get => estaMarcadoParaMorrer; set => estaMarcadoParaMorrer = value; }
//
//public int CompareTo(NoArvore<Dado> o)
//        {
//        return info.CompareTo(o.info);
//        }
//
//public bool Equals(NoArvore<Dado> o)
//        {
//        return this.info.Equals(o.info);
//        }
//        }
