package com.example.fitnesstracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkoutDao {
    @Insert
    void insert(Workout workout);

    @Update
    void update(Workout workout);

    @Delete
    void delete(Workout workout);

    @Query("DELETE FROM workout_table")
    void deleteAllWorkouts();

    @Query("SELECT * FROM workout_table ORDER BY id DESC")
    LiveData<List<Workout>> getAllWorkouts();
}
