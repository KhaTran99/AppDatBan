package com.example.appdatban.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import com.example.appdatban.MainActivity;
import com.example.appdatban.R;
import com.example.appdatban.databinding.ActivityLogInBinding;

import java.util.ArrayList;
import java.util.List;

import user.User;

public class LogInActivity extends AppCompatActivity {
    ActivityLogInBinding binding;
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        binding.btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(),DangKiActivity.class);
                startActivity(intent);
            }
        });
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etUser.getText().toString();
                String password = binding.etPass.getText().toString();
                userList = MainActivity.sqlUserNew.getAllUser();
                for(int i = 0 ; i < userList.size(); i ++) {
                    User user1 = userList.get(i);
                    if((username.equals(user1.getUsername()) == true || username.equals(user1.getEmail()) == true || username.equals(user1.getPhoneNumber()) == true ) && password.equals(user1.getPassword())== true) {
                        MainActivity.sqlUser.insertUser(user1);
                        binding.tvErrorPassWord.setText("");
                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        binding.tvErrorPassWord.setText("Username hoặc mật khẩu không đúng");
                    }
                }


            }
        });

    }
}