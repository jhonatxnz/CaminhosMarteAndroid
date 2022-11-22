package com.example.caminhosmarte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CidadeAdapter extends ArrayAdapter<CidadeModel> {
    public CidadeAdapter(@NonNull Context context, ArrayList<CidadeModel> cidadeModelArrayList) {
        super(context, 0, cidadeModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }
        CidadeModel courseModel = getItem(position);
        TextView cidadeTV = listitemView.findViewById(R.id.idTexto);
        
        cidadeTV.setText(courseModel.getCidade_name());
        //cidadeTV.setText(courseModel.getCidade().nomeCidade);

        cidadeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Caminho escolhido",Toast.LENGTH_LONG).show();;
            }
        });

        return listitemView;
    }
}