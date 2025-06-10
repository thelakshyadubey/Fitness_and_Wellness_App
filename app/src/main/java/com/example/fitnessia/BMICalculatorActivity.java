package com.example.fitnessia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText heightEditText, weightEditText;
    private TextView bmiResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        Button calculateButton = findViewById(R.id.calculateButton);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);

        calculateButton.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String heightStr = heightEditText.getText().toString().trim();
        String weightStr = weightEditText.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float height = Float.parseFloat(heightStr) / 100;
            float weight = Float.parseFloat(weightStr);
            float bmi = weight / (height * height);

            String result;
            if (bmi < 18.5) {
                result = "Underweight";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                result = "Normal";
            } else if (bmi >= 25.0 && bmi <= 29.9) {
                result = "Overweight";
            } else {
                result = "Obesity";
            }

            bmiResultTextView.setText(String.format("Your BMI is %.1f\nStatus: %s", bmi, result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }
}
