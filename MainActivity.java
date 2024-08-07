package com.example.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WorkoutViewModel workoutViewModel;

    // Define the ActivityResultLauncher
    private final ActivityResultLauncher<Intent> addEditActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String name = data.getStringExtra(AddEditWorkoutActivity.EXTRA_NAME);
                        int duration = data.getIntExtra(AddEditWorkoutActivity.EXTRA_DURATION, 1);
                        int caloriesBurned = data.getIntExtra(AddEditWorkoutActivity.EXTRA_CALORIES, 1);

                        Workout workout = new Workout(name, duration, caloriesBurned);
                        workoutViewModel.insert(workout);

                        Toast.makeText(this, "Workout saved", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Workout not saved", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        WorkoutAdapter adapter = new WorkoutAdapter();
        recyclerView.setAdapter(adapter);

        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        workoutViewModel.getAllWorkouts().observe(this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                adapter.setWorkouts(workouts);
            }
        });

        FloatingActionButton buttonAddWorkout = findViewById(R.id.button_add_workout);
        buttonAddWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditWorkoutActivity.class);
            addEditActivityResultLauncher.launch(intent);
        });
    }
}
