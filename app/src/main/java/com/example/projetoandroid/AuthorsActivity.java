package com.example.projetoandroid;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AuthorsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_authors);
        ListView lv = findViewById(R.id.lvAuthors);
        String[] data = {
                "Aluno A – 123456",
                "Aluno B – 234567"
        };
        lv.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data));
    }
}
;
