package com.example.projetoandroid.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projetoandroid.model.Photo;

import java.util.List;

@Dao
public interface PhotoDao {
    @Insert
    void insert(Photo photo);

    @Query("SELECT * FROM Photo WHERE localId = :localId")
    List<Photo> getByLocal(int localId);
}
