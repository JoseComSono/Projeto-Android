package com.example.projetoandroid.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projetoandroid.model.Local;

import java.util.List;

@Dao
public interface LocalDao {
    @Insert
    void insert(Local local);

    @Query("SELECT * FROM Local")
    List<Local> getAll();

    @Query("SELECT * FROM Local WHERE ABS(latitude - :lat) <= 0.05 AND ABS(longitude - :lon) <= 0.05")
    List<Local> getLocaisProximos(double lat, double lon);

    @Query("SELECT * FROM Local WHERE id = :id LIMIT 1")
    Local getById(int id);
}