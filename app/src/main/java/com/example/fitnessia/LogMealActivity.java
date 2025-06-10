package com.example.fitnessia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogMealActivity extends AppCompatActivity {
    private EditText mealNameEditText;
    private TextView nutritionTextView;
    private Button calculateButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_meal);

        mealNameEditText = findViewById(R.id.mealNameEditText);
        nutritionTextView = findViewById(R.id.nutritionTextView);
        calculateButton = findViewById(R.id.calculateButton);
        db = FirebaseFirestore.getInstance();

        calculateButton.setOnClickListener(v -> fetchMealData());
    }

    private void fetchMealData() {
        String mealName = mealNameEditText.getText().toString().trim().toLowerCase();
        if (mealName.isEmpty()) {
            Toast.makeText(this, "Please enter a meal name.", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("meals").document(mealName)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String calories = document.getString("calories");
                            String protein = document.getString("protein");
                            String carbs = document.getString("carbs");
                            String fat = document.getString("fat");

                            String nutritionInfo = "Calories: " + calories + " kcal\n"
                                    + "Protein: " + protein + " g\n"
                                    + "Carbs: " + carbs + " g\n"
                                    + "Fat: " + fat + " g";

                            nutritionTextView.setText(nutritionInfo);
                        } else {
                            nutritionTextView.setText("Meal not found.");
                        }
                    } else {
                        Toast.makeText(this, "Error fetching data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
