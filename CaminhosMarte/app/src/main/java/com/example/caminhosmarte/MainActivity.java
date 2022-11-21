package com.example.caminhosmarte;
//Jogar fora as classes ArvoreDeBusca,Idados,ListaSimples,NoArvore,NoLista,PilhaLista
//Imports
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
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spiOrig, spiDest;
    ImageView imgMapa;
    Button btnRecursivo, btnDijkstra;
    GridView dgvCaminhos;

    //Classes globais
    Cidade []asCidades = new Cidade[23];
    Ligacao []osCaminhos  = new Ligacao[28];
    GrafoBackTracking oGrafoRec;
    ListaSimples<Cidade> listaCidades = new ListaSimples<Cidade>();
    ListaSimples<Ligacao> listaCaminhos = new ListaSimples<Ligacao>();
    //Grafo Grafo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Encontra elemento por id na classe
        btnRecursivo = findViewById(R.id.btnRecursivo);
        btnDijkstra  = findViewById(R.id.btnDijkstra);
        spiOrig      = findViewById(R.id.spiOrig);
        spiDest      = findViewById(R.id.spiDest);
        imgMapa      = findViewById(R.id.imgMapa);
        dgvCaminhos  = findViewById(R.id.dgvCaminhos);

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
            listaCidades.InserirAposFim(new NoLista<>(citys.get(i)));
            asCidades[i] = citys.get(i);
            System.err.println("Cidade" +  i + ": " + asCidades[i].getNome());
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
            listaCaminhos.InserirAposFim(new NoLista<>(paths.get(i)));
            osCaminhos[i] = paths.get(i);
        }
        desenharNoMapa2();
        //oGrafoRec = new GrafoBackTracking(osCaminhos, 23 ,asCidades);

        //Botão que trata de achar caminhos de maneira recursiva
        btnRecursivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(spiOrig.getSelectedItem().toString().compareTo(spiDest.getSelectedItem().toString()) == 0){
                    System.err.println("Erro!\nCidade de origem igual cidade de destino");
                }
                else{

                    int origem  = oGrafoRec.CidadeId(spiOrig.getSelectedItem().toString(),asCidades);
                    int destino = oGrafoRec.CidadeId(spiDest.getSelectedItem().toString(),asCidades);

//                    try {
//                        PilhaLista<Movimento> pilhaCaminho = oGrafoRec.BuscarCaminhos(origem,destino);
//                        if (pilhaCaminho.estaVazia())
//                        {
//                            Toast.makeText(getApplicationContext(), "Não achou caminho!", Toast.LENGTH_LONG).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(getApplicationContext(), "Achou caminho!", Toast.LENGTH_LONG).show();
//
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }

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

        while(listaCidades.DadoAtual() != null){
            Cidade cid = listaCidades.DadoAtual();

            float x = (float)cid.coordenadaX * 100;
            float y = (float)cid.coordenadaY * 100;
            canvas.drawCircle(x, y, 1, paint);
            //canvas.drawText(cid.getNome(),x,y, paint);
            imgMapa.setImageBitmap(bitmap);
            listaCidades.AvancarPosicao();
        }
    }
    public void desenharNoMapa2(){
        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
        myOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mars_political_map_by_axiaterraartunion_d4vfxdf,myOptions);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);

        Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);


        Canvas canvas = new Canvas(mutableBitmap);

        for(int i = 0;i < asCidades.length;i++){
            Cidade cid = asCidades[i];

            float x = (float)cid.coordenadaX * 2000;
            float y = (float)cid.coordenadaY * 2000;
            canvas.drawCircle(x, y, 20, paint);
            canvas.drawText(cid.getNome(),x,y, paint);
            imgMapa.setAdjustViewBounds(true);
            imgMapa.setImageBitmap(mutableBitmap);
        }
    }
}