package com.example.stage_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView resultTextView;
    private TextView levelTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        resultTextView = findViewById(R.id.resultTextView);
        levelTextView = findViewById(R.id.levelTextView);
        imageView = findViewById(R.id.user_avatar);
        TextView usernameTextView = findViewById(R.id.usernameTextView);
        Button calculateButton = findViewById(R.id.calculateButton);


        calculateButton.setOnClickListener(this);

        // 从 Intent 中获取用户名
        String username = getIntent().getStringExtra("username");
        if (username != null) {
            usernameTextView.setText("用户: " + username);
            setAvatarBasedOnUsername(username);
        } else {
            usernameTextView.setText("用户: 未知");
            imageView.setImageResource(R.drawable.avatar); // 默认头像
        }
    }

    private void setAvatarBasedOnUsername(String username) {

        int usernameavatar = getResources().getIdentifier(username, "drawable", getPackageName());

        if (usernameavatar != 0) {
            imageView.setImageResource(usernameavatar); // 根据用户名加载头像
        } else {
            imageView.setImageResource(R.drawable.avatar); // 如果找不到，使用默认头像
        }
    }

    private void calculateBMI() {
        String weightStr = weightEditText.getText().toString();
        String heightStr = heightEditText.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            try {
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr) / 100;
                double bmi = weight / (height * height);
                resultTextView.setText(String.format("你的 BMI 指数是: %.2f", bmi));

                // 根据 BMI 值判断等级
                String level;
                if (bmi <= 18.4) {
                    level = "消瘦";
                } else if (bmi <= 23.9) {
                    level = "正常";
                } else if (bmi <= 27.9) {
                    level = "超重";
                } else {
                    level = "肥胖";
                }
                levelTextView.setText("你的 BMI 等级是: " + level);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "请输入有效的数字！", Toast.LENGTH_SHORT).show();
                resultTextView.setText("");
                levelTextView.setText("");
            }
        } else {
            Toast.makeText(this, "请输入有效的体重和身高！", Toast.LENGTH_SHORT).show();
            resultTextView.setText("");
            levelTextView.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        calculateBMI();

    }
}
