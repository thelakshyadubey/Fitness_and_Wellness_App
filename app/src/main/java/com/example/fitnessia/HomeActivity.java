package com.example.fitnessia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button logoutButton;
    private FirebaseAuth firebaseAuth;
    private Button logMealButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutButton);

        logMealButton = findViewById(R.id.logMealButton);
        logMealButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LogMealActivity.class);
            startActivity(intent);
        });

        Button bmiCalculatorButton = findViewById(R.id.bmiCalculatorButton);
        bmiCalculatorButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BMICalculatorActivity.class);
            startActivity(intent);
        });

        Button buttonWorkoutPlanner = findViewById(R.id.buttonWorkoutPlanner);
        buttonWorkoutPlanner.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, WorkoutPlannerActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            firebaseAuth.signOut();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
