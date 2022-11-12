package com.example.caminhosmarte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner spiOrig, spiDest;
    ImageView imgMapa;
    Button btnRecursivo, btnDjkistra;

//    ListaSimples<Cidade> cidades = new ListaSimples<>();
//    ListaSimples<Ligacao> caminhos = new ListaSimples<Ligacao>();
//    GrafoBackTracking oGrafo;
    String endCidades = "C:\\Users\\Jhon\\Documents\\Cotuca\\4_Semestre\\EST2-Chico\\CaminhosMarteAndroid\\CaminhosMarte\\cidades.json";
    String endCaminhos = "C:\\Users\\Jhon\\Documents\\Cotuca\\4_Semestre\\EST2-Chico\\CaminhosMarteAndroid\\CaminhosMarte\\caminhos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRecursivo = findViewById(R.id.btnRecursivo);
        btnDjkistra = findViewById(R.id.btnDjkistra);
        spiOrig = findViewById(R.id.spiOrig);
        spiDest = findViewById(R.id.spiDest);
        imgMapa = findViewById(R.id.imgMapa);


        btnRecursivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btnDjkistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}