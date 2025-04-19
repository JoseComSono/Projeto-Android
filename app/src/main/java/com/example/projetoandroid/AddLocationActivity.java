package com.example.projetoandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoandroid.db.AppDatabase;
import com.example.projetoandroid.model.Local;

public class AddLocationActivity extends AppCompatActivity {

    private static final int IMG_REQ = 100;
    private Uri uri;
    private EditText edtT, edtLat, edtLon;
    private ImageView imgSel;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_add_location);

        edtT = findViewById(R.id.edtTitulo);
        edtLat = findViewById(R.id.edtLatitude);
        edtLon = findViewById(R.id.edtLongitude);
        imgSel = findViewById(R.id.imgSelecionada);
        Button bImg = findViewById(R.id.btnEscolherImagem),
                bSal = findViewById(R.id.btnSalvar);

        bImg.setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                    IMG_REQ);
        });

        bSal.setOnClickListener(v -> {
            String t = edtT.getText().toString(),
                    ls = edtLat.getText().toString(),
                    lo = edtLon.getText().toString();

            if (t.isEmpty()|| ls.isEmpty()|| lo.isEmpty()|| uri==null) {
                Toast.makeText(this,"Preencha tudo!",Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Local l = new Local();
                l.titulo = t;
                l.latitude = Double.parseDouble(ls);
                l.longitude = Double.parseDouble(lo);
                l.imagemUri = uri.toString();
                new Thread(() -> {
                    AppDatabase.getInstance(this).localDao().insert(l);
                    runOnUiThread(() -> {
                        Toast.makeText(this,"Guardado!",Toast.LENGTH_SHORT).show();
                        finish();
                    });
                }).start();
            } catch (NumberFormatException e){
                Toast.makeText(this,"Coordenadas inválidas",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int req,int res,@Nullable Intent d){
        super.onActivityResult(req,res,d);
        if (req==IMG_REQ && res==RESULT_OK && d!=null) {
            uri = d.getData();
            imgSel.setImageURI(uri);
        }
    }
}
