package com.example.caminhosmarte;
//Jhonatan Willian dos Santos Silva 21686
//Matheus Henrique Pedrozo Traiba   21254
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvDist;
    Spinner spiOrig, spiDest;
    ImageView imgMapa;
    Button btnRecursivo, btnDijkstra;
    GridView dgvCaminhos;

    //Classes globais
    Cidade[] asCidades               = new Cidade[23];
    Ligacao[] osCaminhos             = new Ligacao[28];
    List<String> caminhosEncontrados = new ArrayList<String>();
    GrafoBackTracking oGrafoRec;
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
        tvDist       = findViewById(R.id.tvDist);

        //Código para salvar o bitmap do mapa de Marte(se essas variáveis estivessem locais em algum método o mapa seria redesenhado, perdendo alterações que poderiam ser feitas posteriomente)
        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither              = true;
        myOptions.inScaled              = false;
        myOptions.inPreferredConfig     = Bitmap.Config.ARGB_8888;
        myOptions.inPurgeable           = true;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mars_political_map_by_axiaterraartunion_d4vfxdf, myOptions);
        Paint paint   = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        paint.setStrokeWidth(30);

        Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas        = new Canvas(mutableBitmap);

        //https://www.bezkoder.com/java-android-read-json-file-assets-gson/
        //Ler do arquivo json
        String[] spiCidades;
        spiCidades = new String[23];

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "cidades.json");

        Gson gson         = new Gson();
        Type listUserType = new TypeToken<List<Cidade>>() {
        }.getType();

        List<Cidade> citys = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < citys.size(); i++) {
            //Coloca a cidade do arquivo json no vetor de cidades
            asCidades[i]  = citys.get(i);
            spiCidades[i] = citys.get(i).getNome();
        }

        //Adapter para o spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiCidades);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Seta o adapter com o nome das cidades para o spinner
        spiOrig.setAdapter(spinnerAdapter);
        spiDest.setAdapter(spinnerAdapter);


        String jsonFileString2 = Utils.getJsonFromAssets(getApplicationContext(), "caminhos.json");
        Log.i("data", jsonFileString2);

        Type listUserType2 = new TypeToken<List<Ligacao>>() {
        }.getType();

        List<Ligacao> paths = gson.fromJson(jsonFileString2, listUserType2);
        for (int i = 0; i < paths.size(); i++) {
            osCaminhos[i] = paths.get(i);
        }

        //Desenha as cidades no mapa
        desenhar(mutableBitmap, paint, canvas);

        //Monta a matriz de adjacencia
        oGrafoRec = new GrafoBackTracking(osCaminhos, citys.size(), asCidades);

        //Botão que trata de achar caminhos de maneira recursiva
        btnRecursivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Limpa o GridView
                //oGrafoRec.limpar(dgvCaminhos);

                if (spiOrig.getSelectedItem().toString().compareTo(spiDest.getSelectedItem().toString()) == 0) {
                    Toast.makeText(getApplicationContext(), "Erro!\nCidade de origem igual cidade de destino", Toast.LENGTH_LONG).show();
                } else {
                    //Acha cidade de origem e de destino pelo spinner
                    int origem  = oGrafoRec.cidadeId(spiOrig.getSelectedItem().toString(), asCidades);
                    int destino = oGrafoRec.cidadeId(spiDest.getSelectedItem().toString(), asCidades);

                    //Lista recebe os caminhos encontrados pelo método de recursão
                    caminhosEncontrados = oGrafoRec.Recursao(dgvCaminhos, origem, destino, asCidades);
                    //Se a lista está vazio não achou nenhum caminho
                    if (caminhosEncontrados.size() == 0) {
                        Toast.makeText(getApplicationContext(), "Não achou caminho!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //Botão que trata de achar caminhos com algoritimo de dijkstra
        btnDijkstra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Limpa o GridView
                //oGrafoRec.limpar(dgvCaminhos);

                if (spiOrig.getSelectedItem().toString().compareTo(spiDest.getSelectedItem().toString()) == 0) {
                    Toast.makeText(getApplicationContext(), "Erro!\nCidade de origem igual cidade de destino", Toast.LENGTH_LONG).show();
                } else {
                    //Acha cidade de origem e de destino pelo spinner
                    int origem  = oGrafoRec.cidadeId(spiOrig.getSelectedItem().toString(), asCidades);
                    int destino = oGrafoRec.cidadeId(spiDest.getSelectedItem().toString(), asCidades);

                    oGrafo = new Grafo(asCidades.length);
                    //Foreach que cria os vertices
                    for (Cidade cid : asCidades) {
                        oGrafo.novoVertice(cid.getNome());
                    }
                    //Foreach que cria as arestas com a distancia
                    for (Ligacao cam : osCaminhos) {
                        oGrafo.novaAresta(oGrafoRec.cidadeId(cam.origem, asCidades), oGrafoRec.cidadeId(cam.destino, asCidades), cam.distancia);
                    }
                    //Lista recebe o menor caminho encontrado pelo método menorCaminho(dijkstra)
                    caminhosEncontrados = oGrafo.menorCaminho(origem, destino, dgvCaminhos);
                    tvDist.setText(tvDist.getText() + " " + oGrafo.soma);
                }
            }
        });

        dgvCaminhos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Vetor que recebe as cidades do caminho encontrado que foi clicado pelo usuario
                //Pegamos esse caminho do clique por causa do parâmetro position
                //Tiramos as " --> " que vieram da string
                String[] cidades = caminhosEncontrados.get(position).split(" --> ");
                if(caminhosEncontrados.size() > 1){
                    tvDist.setText(oGrafoRec.distacias.get(position));
                }
                //Gera uma cor aleátoria
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                //Percorre as cidades para podermos desenha o caminho no mapa
                for (int cont = 0; cont < cidades.length; cont++) {
                    if (cont != 0) {
                        cont--;
                    }
                    //Pega a primeira cidade
                    Cidade cidadeUm = new Cidade(cidades[cont], 0, 0);
                    int i = oGrafoRec.cidadeId(cidadeUm.nomeCidade, asCidades);
                    //X e Y da primeira cidade
                    double x = asCidades[i].coordenadaX;
                    double y = asCidades[i].coordenadaY;

                    //Pega a segunda cidade
                    Cidade cidadeDois = new Cidade(cidades[++cont], 0, 0);

                    i = oGrafoRec.cidadeId(cidadeDois.nomeCidade, asCidades);
                    //X e Y da segunda cidade
                    double x2 = asCidades[i].coordenadaX;
                    double y2 = asCidades[i].coordenadaY;

                    linha(x, y, x2, y2, canvas, mutableBitmap, paint, color);
                }
            }
        });

    }

    //Método que desenha o nome das cidades no mapa
    public void desenhar(Bitmap mutableBitmap, Paint paint, Canvas canvas) {

        //Percorre as cidades para podermos desenhar elas no mapa
        for (int i = 0; i < asCidades.length; i++) {

            Cidade cid = asCidades[i];
            //X e Y da cidade
            float x = (float) (cid.coordenadaX * 3500);
            float y = (float) (cid.coordenadaY * 2000);
            //Desenha bolinha no X e Y
            canvas.drawCircle(x, y, 20, paint);
            //Desenha nome da cidade no X e Y
            canvas.drawText(cid.getNome(), x, y, paint);
            imgMapa.setAdjustViewBounds(true);
            //Atualiza o bitmap do mapa
            imgMapa.setImageBitmap(mutableBitmap);
        }

    }

    //Método que desenha o caminho entre as cidades
    public void linha(double coordX, double coordY, double coordX2, double coordY2, Canvas canvas, Bitmap mutableBitmap, Paint paint, Integer color) {
        //Seta cor aleatória
        paint.setColor(color);
        //Onde começa a linha
        float x  = (float) (coordX  * 3500);
        float y  = (float) (coordY  * 2000);
        //Onde termina a linha
        float x2 = (float) (coordX2 * 3500);
        float y2 = (float) (coordY2 * 2000);
        //Desenha a linha
        canvas.drawLine(x, y, x2, y2, paint);

        ImageView imageView = (ImageView) findViewById(R.id.imgMapa);
        imageView.setAdjustViewBounds(true);
        //Atualiza o bitmap do mapa
        imageView.setImageBitmap(mutableBitmap);
    }

}