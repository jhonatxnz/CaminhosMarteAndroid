package com.example.caminhosmarte;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

public class MainActivity extends AppCompatActivity {
    Spinner spiOrig, spiDest;
    ImageView imgMapa,imgMapa2;
    Button btnRecursivo, btnDjkistra;

    Bitmap myBitmap;
    Paint myPaint;
    int x1 = 1;
    int y1 = 1;
    int x2 = 2;
    int y2 = 2;

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
        imgMapa2 = findViewById(R.id.imgMapa2);

        DesenharNoMapa();

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