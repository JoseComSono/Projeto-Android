package com.example.projetoandroid.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoandroid.R;
import com.example.projetoandroid.model.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.VH> {

    private final Context ctx;
    private List<Photo> fotos;

    public PhotoAdapter(Context ctx) { this.ctx = ctx; }

    public void setFotos(List<Photo> f) {
        fotos = f;
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup p, int vt) {
        return new VH(LayoutInflater.from(ctx)
                .inflate(R.layout.item_photo, p, false));
    }

    @Override
    public void onBindViewHolder(VH h, int i) {
        h.img.setImageURI(Uri.parse(fotos.get(i).uri));
    }

    @Override public int getItemCount() { return fotos!=null?fotos.size():0; }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img;
        VH(View v) {
            super(v);
            img = v.findViewById(R.id.imgPhoto);
        }
    }
}