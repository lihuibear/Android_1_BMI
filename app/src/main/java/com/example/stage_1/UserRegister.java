package com.example.stage_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserRegister extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEditText;
    private RadioGroup genderRadioGroup;
    private RadioGroup cityRadioGroup; // 新增城市选择的 RadioGroup
    private Button register_button;

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        usernameEditText = findViewById(R.id.username);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        cityRadioGroup = findViewById(R.id.cityRadioGroup);
        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(this);
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(this, "姓名不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        // 获取性别
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId != -1) {
            RadioButton selectedGender = findViewById(selectedGenderId);
            gender = selectedGender.getText().toString();
        }

        // 获取城市信息
        int selectedCityId = cityRadioGroup.getCheckedRadioButtonId();
        String city = "";
        if (selectedCityId != -1) {
            RadioButton selectedCity = findViewById(selectedCityId);
            city = selectedCity.getText().toString();
        }

        message = "注册信息:姓名: " + username + "性别: " + gender + "选择的城市: " + city;
    }

    @Override
    public void onClick(View v) {
        registerUser();
        if (v.getId() == R.id.register_button) {
            Intent intent1 = new Intent(UserRegister.this, UsersLogin.class);
            startActivity(intent1);
        }
        // 显示注册信息
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
