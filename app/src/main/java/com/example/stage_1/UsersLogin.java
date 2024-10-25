package com.example.stage_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UsersLogin extends AppCompatActivity implements View.OnClickListener {


    private final String[][] validCredentials = {
            {"lh", "057"},
            {"ljj", "058"},
            {"ljx", "059"},
            {"lrq", "061"},
    };

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button login_button;
    private TextView to_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.userpasswd);
        login_button = findViewById(R.id.login_button);
        to_register = findViewById(R.id.to_register);

        login_button.setOnClickListener(this);
        to_register.setOnClickListener(this);
    }

    private boolean validateCredentials() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }

        // 检查是否在数组中有匹配的用户名和密码对
        for (String[] credential : validCredentials) {
            if (username.equals(credential[0]) && password.equals(credential[1])) {
                return true;
            }
        }

        Toast.makeText(this, "用户名或密码输入有误！", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                if (validateCredentials()) {
                    Intent intent1 = new Intent(UsersLogin.this, MainActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.to_register:
                Intent intent2 = new Intent(UsersLogin.this, UserRegister.class);
                startActivity(intent2);
                break;
        }
    }
}
