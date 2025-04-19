package com.example.projetoandroid.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = Local.class,
        parentColumns = "id",
        childColumns = "localId",
        onDelete = ForeignKey.CASCADE
))
public class Photo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int localId;
    public String uri;
}