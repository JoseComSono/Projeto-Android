package com.example.projetoandroid.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Local {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public double latitude;
    public double longitude;
    public String imagemUri;
}
