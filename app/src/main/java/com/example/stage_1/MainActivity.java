package com.example.stage_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView resultTextView;
    private TextView levelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        resultTextView = findViewById(R.id.resultTextView);
        levelTextView = findViewById(R.id.levelTextView);
        Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightEditText.getText().toString();
        String heightStr = heightEditText.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr) / 100;
            double bmi = weight / (height * height);
            resultTextView.setText(String.format("你的 BMI 指数是: %.2f", bmi));

            // 根据 BMI 值判断等级
            String level = "";
            if (bmi <= 18.4) {
                level = "消瘦";
            } else if (bmi >= 18.5 && bmi <= 23.9) {
                level = "正常";
            } else if (bmi >= 24 && bmi <= 27.9) {
                level = "超重";
            } else {
                level = "肥胖";
            }
            levelTextView.setText("你的 BMI 等级是: " + level);
        } else {
            resultTextView.setText("请输入有效的体重和身高！");
            levelTextView.setText("");
        }
    }
}
