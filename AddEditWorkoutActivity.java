package com.example.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddEditWorkoutActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.fitnesstracker.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.fitnesstracker.EXTRA_NAME";
    public static final String EXTRA_DURATION = "com.example.fitnesstracker.EXTRA_DURATION";
    public static final String EXTRA_CALORIES = "com.example.fitnesstracker.EXTRA_CALORIES";

    private EditText editTextName;
    private NumberPicker numberPickerDuration;
    private NumberPicker numberPickerCaloriesBurned;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String name = data.getStringExtra(EXTRA_NAME);
                    int duration = data.getIntExtra(EXTRA_DURATION, 1);
                    int caloriesBurned = data.getIntExtra(EXTRA_CALORIES, 1);

                    // Handle the result data
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_workout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextName = findViewById(R.id.edit_text_name);
        numberPickerDuration = findViewById(R.id.number_picker_duration);
        numberPickerCaloriesBurned = findViewById(R.id.number_picker_calories_burned);

        numberPickerDuration.setMinValue(1);
        numberPickerDuration.setMaxValue(300);
        numberPickerCaloriesBurned.setMinValue(1);
        numberPickerCaloriesBurned.setMaxValue(1000);

        setTitle("Add Workout");

        FloatingActionButton buttonSaveWorkout = findViewById(R.id.button_save_workout);
        buttonSaveWorkout.setOnClickListener(v -> saveWorkout());

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Workout");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            numberPickerDuration.setValue(intent.getIntExtra(EXTRA_DURATION, 1));
            numberPickerCaloriesBurned.setValue(intent.getIntExtra(EXTRA_CALORIES, 1));
        } else {
            setTitle("Add Workout");
        }
    }

    private void saveWorkout() {
        String name = editTextName.getText().toString();
        int duration = numberPickerDuration.getValue();
        int caloriesBurned = numberPickerCaloriesBurned.getValue();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please insert a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DURATION, duration);
        data.putExtra(EXTRA_CALORIES, caloriesBurned);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
