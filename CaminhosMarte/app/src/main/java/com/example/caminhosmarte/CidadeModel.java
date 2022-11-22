package com.example.caminhosmarte;

public class CidadeModel {
    // string course_name for storing course_name
    // and imgid for storing image id.
    private String cidade_name;
    private Cidade cid;

    public CidadeModel(String cidade_name) {
        this.cidade_name = cidade_name;
    }

    public String getCidade_name() {
        return cidade_name;
    }

    public void setCidade_name(String cidade_name) {
        this.cidade_name = cidade_name;
    }
}
//public class CidadeModel {
//    // string course_name for storing course_name
//    // and imgid for storing image id.
//    private Cidade cid;
//
//    public CidadeModel(Cidade cid) {
//        this.cid = cid;
//    }
//
//    public Cidade getCidade() {
//        return cid;
//    }
//
//    public void setCidade(Cidade cid) {
//        this.cid = cid;
//    }
//}