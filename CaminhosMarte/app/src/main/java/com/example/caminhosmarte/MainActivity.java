package com.example.caminhosmarte;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spiOrig, spiDest;
    ImageView imgMapa,imgMapa2;
    Button btnRecursivo, btnDijkstra;
    GridView dgvCaminhos;
    //Classes globais
    ListaSimples<Cidade> listaCidades = new ListaSimples<Cidade>();
    ListaSimples<Ligacao> listaCaminhos = new ListaSimples<Ligacao>();
    GrafoBackTracking oGrafoRec;
//    Grafo Grafo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Encontra elemento por id na classe
        btnRecursivo = findViewById(R.id.btnRecursivo);
        btnDijkstra = findViewById(R.id.btnDijkstra);
        spiOrig = findViewById(R.id.spiOrig);
        spiDest = findViewById(R.id.spiDest);
        imgMapa = findViewById(R.id.imgMapa);
        imgMapa2 = findViewById(R.id.imgMapa2);
        dgvCaminhos = findViewById(R.id.dgvCaminhos);

        //https://www.bezkoder.com/java-android-read-json-file-assets-gson/
        //Ler do arquivo json
        String [] spiCidades;
        spiCidades = new String[23];

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "cidades.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Cidade>>() { }.getType();

        List<Cidade> citys = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < citys.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + citys.get(i));
            listaCidades.InserirAposFim(new NoLista<>(citys.get(i)));

            spiCidades[i] = citys.get(i).getNome();
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiCidades);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiOrig.setAdapter(spinnerAdapter);
        spiDest.setAdapter(spinnerAdapter);

        String jsonFileString2 = Utils.getJsonFromAssets(getApplicationContext(), "caminhos.json");
        Log.i("data", jsonFileString2);

        Type listUserType2 = new TypeToken<List<Ligacao>>() { }.getType();

        List<Ligacao> paths = gson.fromJson(jsonFileString2, listUserType2);
        for (int i = 0; i < citys.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + paths.get(i));
            listaCaminhos.InserirAposFim(new NoLista<>(paths.get(i)));
        }

        oGrafoRec = new GrafoBackTracking(listaCaminhos, 23);

        //Botão que trata de achar caminhos de maneira recursiva
        btnRecursivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //Botão que trata de achar caminhos com algoritimo de dijkstra
        btnDijkstra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //desenha nome das cidades e bolinha no mapa
    public void desenharNoMapa(){
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);
        canvas.drawCircle(50, 50, 1, paint);
        canvas.drawText("lalala",50,50, paint);
        imgMapa2.setImageBitmap(bitmap);
    }
}