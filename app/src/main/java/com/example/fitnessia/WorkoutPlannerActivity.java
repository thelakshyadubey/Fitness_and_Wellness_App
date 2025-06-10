package com.example.fitnessia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;

public class WorkoutPlannerActivity extends AppCompatActivity {

    private Spinner spinnerFitnessGoal;
    private Button buttonShowExercises;
    private RecyclerView recyclerViewExercises;
    private ExerciseAdapter exerciseAdapter;
    private ArrayList<Exercise> exerciseList;

    private FirebaseFirestore firestore;
    private CollectionReference workoutsCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_planner);

        spinnerFitnessGoal = findViewById(R.id.spinnerFitnessGoal);
        buttonShowExercises = findViewById(R.id.buttonShowExercises);
        recyclerViewExercises = findViewById(R.id.recyclerViewExercises);

        firestore = FirebaseFirestore.getInstance();
        workoutsCollection = firestore.collection("workouts");

        exerciseList = new ArrayList<>();
        exerciseAdapter = new ExerciseAdapter(exerciseList);
        recyclerViewExercises.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewExercises.setAdapter(exerciseAdapter);

        buttonShowExercises.setOnClickListener(v -> fetchExercises());
    }

    private void fetchExercises() {
        String selectedGoal = spinnerFitnessGoal.getSelectedItem().toString().toLowerCase().replace(" ", "_");

        workoutsCollection.document(selectedGoal).collection("exercises")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        exerciseList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String name = document.getString("name");
                            String sets = document.getString("sets");
                            String reps = document.getString("reps");
                            exerciseList.add(new Exercise(name, sets, reps));
                        }
                        exerciseAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Failed to load exercises", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
