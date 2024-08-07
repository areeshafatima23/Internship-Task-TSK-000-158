package com.example.fitnesstracker;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class WorkoutRepository {
    private WorkoutDao workoutDao;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutRepository(Application application) {
        WorkoutDatabase database = WorkoutDatabase.getDatabase(application);
        workoutDao = database.workoutDao();
        allWorkouts = workoutDao.getAllWorkouts();
    }

    public void insert(Workout workout) {
        WorkoutDatabase.databaseWriteExecutor.execute(() -> workoutDao.insert(workout));
    }

    public void update(Workout workout) {
        WorkoutDatabase.databaseWriteExecutor.execute(() -> workoutDao.update(workout));
    }

    public void delete(Workout workout) {
        WorkoutDatabase.databaseWriteExecutor.execute(() -> workoutDao.delete(workout));
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }
}
