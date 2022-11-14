package com.example.caminhosmarte;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
    ArvoreDeBusca<Cidade> cidades = new ArvoreDeBusca<Cidade>();
    ArvoreDeBusca<Caminho> caminhos = new ArvoreDeBusca<Caminho>();
//    ListaSimples<Cidade> cidades = new ListaSimples<Cidade>();
//    ListaSimples<Ligacao> caminhos = new ListaSimples<Ligacao>();
//    GrafoBackTracking oGrafoRec;
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
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "cidades.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Cidade>>() { }.getType();

        List<Cidade> users = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < users.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + users.get(i));
        }


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
    public void DesenharNoMapa(){
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