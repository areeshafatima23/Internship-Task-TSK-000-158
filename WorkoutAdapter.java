package com.example.fitnesstracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {
    private List<Workout> workouts = new ArrayList<>();

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item, parent, false);
        return new WorkoutHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder holder, int position) {
        Workout currentWorkout = workouts.get(position);
        holder.textViewName.setText(currentWorkout.getName());
        holder.textViewDuration.setText(String.valueOf(currentWorkout.getDuration()));
        holder.textViewCaloriesBurned.setText(String.valueOf(currentWorkout.getCaloriesBurned()));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged();
    }

    class WorkoutHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDuration;
        private TextView textViewCaloriesBurned;

        public WorkoutHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDuration = itemView.findViewById(R.id.text_view_duration);
            textViewCaloriesBurned = itemView.findViewById(R.id.text_view_calories_burned);
        }
    }
}
