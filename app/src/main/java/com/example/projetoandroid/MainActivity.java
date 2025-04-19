package com.example.projetoandroid;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoandroid.adapter.LocalAdapter;
import com.example.projetoandroid.db.AppDatabase;
import com.example.projetoandroid.db.LocalDao;
import com.example.projetoandroid.model.Local;
import com.example.projetoandroid.sensor.ShakeDetector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LocalAdapter adapter;
    private boolean filtro = false;
    private LocalDao dao;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        dao = AppDatabase.getInstance(this).localDao();
        rv = findViewById(R.id.recyclerViewLocais);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LocalAdapter(this, loc -> {
            Intent i = new Intent(this, LocationDetailsActivity.class);
            i.putExtra("local_id", loc.id);
            startActivity(i);
        });
        rv.setAdapter(adapter);

        Button btnF = findViewById(R.id.btnFiltro),
                btnA = findViewById(R.id.btnAdicionar);

        btnF.setOnClickListener(v -> { filtro = !filtro; load(); });
        btnA.setOnClickListener(v -> startActivity(new Intent(this, AddLocationActivity.class)));

        load();
        new ShakeDetector(this, () -> startActivity(new Intent(this, AuthorsActivity.class))).start();
    }

    private void load() {
        new Thread(() -> {
            List<Local> list = filtro
                    ? dao.getLocaisProximos(getLoc().getLatitude(), getLoc().getLongitude())
                    : dao.getAll();
            runOnUiThread(() -> adapter.setLocais(list));
        }).start();
    }

    private Location getLoc() {
        try {
            return ((LocationManager)getSystemService(Context.LOCATION_SERVICE))
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (SecurityException e) {
            runOnUiThread(() -> Toast.makeText(this, "Sem permissão localização", Toast.LENGTH_SHORT).show());
            return null;
        }
    }
}
