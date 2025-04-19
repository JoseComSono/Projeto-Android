package com.example.projetoandroid.model;
@Entity
public class Local {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String titulo;
    public double latitude;
    public double longitude;
    public String imagemUri;
}