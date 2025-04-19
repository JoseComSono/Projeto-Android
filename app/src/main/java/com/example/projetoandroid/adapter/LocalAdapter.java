package com.example.projetoandroid.adapter;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoandroid.R;
import com.example.projetoandroid.model.Local;

import java.util.List;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {

    public interface OnItemClick {
        void onClick(Local local);
    }

    private final Context ctx;
    private final OnItemClick listener;
    private List<Local> lista;

    public LocalAdapter(Context ctx, OnItemClick listener) {
        this.ctx = ctx;
        this.listener = listener;
    }

    public void setLocais(List<Local> l) {
        this.lista = l;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx)
                .inflate(R.layout.item_local, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int pos) {
        Local loc = lista.get(pos);
        h.img.setImageURI(Uri.parse(loc.imagemUri));
        h.txtTitulo.setText(loc.titulo);
        h.txtCoords.setText("Lat: "+loc.latitude+" Lon: "+loc.longitude);
        h.itemView.setOnClickListener(v -> listener.onClick(loc));
    }

    @Override public int getItemCount() { return lista != null ? lista.size() : 0; }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtTitulo, txtCoords;
        ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.imgLocal);
            txtTitulo = v.findViewById(R.id.txtTitulo);
            txtCoords = v.findViewById(R.id.txtCoordenadas);
        }
    }
}