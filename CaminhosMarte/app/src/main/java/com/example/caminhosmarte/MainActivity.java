package com.example.caminhosmarte;
//Jogar fora as classes ArvoreDeBusca,Idados,ListaSimples,NoArvore,NoLista,PilhaLista
//Imports
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Spinner spiOrig, spiDest;
    ImageView imgMapa;
    Button btnRecursivo, btnDijkstra;
    GridView dgvCaminhos;

    //Classes globais
    Cidade []asCidades       = new Cidade[23];
    Ligacao []osCaminhos     = new Ligacao[28];
    GrafoBackTracking oGrafoRec;
    List<String> caminhosEncontrados = new ArrayList<String>();
    Grafo oGrafo;


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

        //https://acervolima.com/gridview-no-android-com-exemplo/

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
            asCidades[i] = citys.get(i);
            //listCidades.add(citys.get(i).nomeCidade);
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
        for (int i = 0; i < paths.size(); i++) {
            osCaminhos[i] = paths.get(i);
        }
        //Desenha as cidades no mapa
        desenhar();
        //Monta a matriz de adjacencia
        oGrafoRec = new GrafoBackTracking(osCaminhos, citys.size() ,asCidades);

        //Botão que trata de achar caminhos de maneira recursiva
        btnRecursivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(spiOrig.getSelectedItem().toString().compareTo(spiDest.getSelectedItem().toString()) == 0){
                    System.err.println("Erro!\nCidade de origem igual cidade de destino");
                } else {

                    int origem  = oGrafoRec.cidadeId(spiOrig.getSelectedItem().toString(),asCidades);
                    int destino = oGrafoRec.cidadeId(spiDest.getSelectedItem().toString(),asCidades);



                    try {
                        caminhosEncontrados =  oGrafoRec.Recursao(dgvCaminhos,origem,destino,asCidades);
//                        if (listCaminho.size() <= 0)
//                        {
//                            Toast.makeText(getApplicationContext(), "Não achou caminho!", Toast.LENGTH_LONG).show();
//                        }
//                        else
//                        {
//                            for (Movimento mov : listCaminho) {
//                            }
//
//                            Toast.makeText(getApplicationContext(), "Achou caminho!", Toast.LENGTH_LONG).show();
//
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        //Botão que trata de achar caminhos com algoritimo de dijkstra
        btnDijkstra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(spiOrig.getSelectedItem().toString().compareTo(spiDest.getSelectedItem().toString()) == 0){
                    Toast.makeText(getApplicationContext(),"Erro!\nCidade de origem igual cidade de destino",Toast.LENGTH_LONG).show();
                } else {
                    int origem  = oGrafoRec.cidadeId(spiOrig.getSelectedItem().toString(),asCidades);
                    int destino = oGrafoRec.cidadeId(spiDest.getSelectedItem().toString(),asCidades);

                    oGrafo = new Grafo(asCidades.length);

                    for (Cidade cid : asCidades) {
                        oGrafo.novoVertice(cid.getNome());
                    }
                    for (Ligacao cam : osCaminhos) {
                        oGrafo.novaAresta(oGrafoRec.cidadeId(cam.origem,asCidades),oGrafoRec.cidadeId(cam.destino,asCidades),cam.distancia);
                    }
                    caminhosEncontrados = oGrafo.menorCaminho(origem,destino,dgvCaminhos);
                }
            }
        });
        dgvCaminhos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

//                for (int cont = 0; cont < caminhosEncontrados.size(); cont++)
//                {
                int cont = 0;
                Cidade cidadeUm = new Cidade(caminhosEncontrados.get(cont), 0, 0, null);
                int i = oGrafoRec.cidadeId(cidadeUm.nomeCidade, asCidades);

                double x = asCidades[i].coordenadaX;
                double y = asCidades[i].coordenadaY;

                Cidade cidadeDois = new Cidade(caminhosEncontrados.get(++cont), 0, 0, null);

                i = oGrafoRec.cidadeId(cidadeDois.nomeCidade, asCidades);

                double x2 = asCidades[i].coordenadaX;
                double y2 = asCidades[i].coordenadaY;

                linha(x, y, x2, y2);
//                }
            }
        });
    }


    public void desenhar(){
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

            float x = (float)cid.coordenadaX * 3500;
            float y = (float)cid.coordenadaY * 2000;
            canvas.drawCircle(x, y, 20, paint);
            canvas.drawText(cid.getNome(),x,y, paint);
            imgMapa.setAdjustViewBounds(true);
            imgMapa.setImageBitmap(mutableBitmap);
        }

    }
    public void linha(double coordX, double coordY, double coordX2, double coordY2){
        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
        myOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mars_political_map_by_axiaterraartunion_d4vfxdf, myOptions);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setTextSize(100);

        Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        Canvas canvas = new Canvas(mutableBitmap);
        // canvas.drawCircle(60, 50, 25, paint);
        paint.setStrokeWidth(30);

        float x = (float) (coordX * 3500);
        float y = (float) (coordY * 2000);
        float x2 = (float) (coordX2 * 3500);
        float y2 = (float) (coordY2 * 2000);

        canvas.drawLine(x, y, x2, y2, paint);

        ImageView imageView = (ImageView)findViewById(R.id.imgMapa);
        imageView.setAdjustViewBounds(true);
        imageView.setImageBitmap(mutableBitmap);
    }

}