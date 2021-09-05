package com.example.appdatban.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.example.appdatban.MainActivity;
import com.example.appdatban.R;
import com.example.appdatban.databinding.ActivityDangKiBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sqlhelper.SQLUserNew;
import user.User;

public class DangKiActivity extends AppCompatActivity {
    ActivityDangKiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_dang_ki);
        List<User> useNewList = new ArrayList<>();
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rePass = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!])[a-zA-Z\\d@#$%!]{8,}$";
                String rePhone = "^[0-9d_-]{10,}$";
                String reUser = "^[a-z0-9A-Zd_-]{6,}$";
                String reEmail = "^[a-z0-9A-Zd_]{10,}$";
                Pattern patternPass = Pattern.compile(rePass);
                Pattern patternUser = Pattern.compile(reUser);
                Pattern patternPhone = Pattern.compile(rePhone);
                Pattern patternEmail = Pattern.compile(reEmail);
                Matcher matcherPass = patternPass.matcher(binding.etPass.getText().toString());
                Matcher matcherPass2 = patternPass.matcher(binding.etPass2.getText().toString());
                Matcher matcherUser = patternUser.matcher(binding.etUser.getText().toString());
                Matcher matcherPhone = patternPhone.matcher(binding.etPhone.getText().toString());
                Matcher matcherEmail = patternEmail.matcher(binding.etEmail.getText().toString());

                if(!matcherPhone.matches()== true) {
                    binding.imgErrorSdt.setVisibility(View.VISIBLE);
                    binding.tvErrorPhone.setText("Số điện thoại của bạn chưa đúng");

                }
                else {
                    binding.imgErrorSdt.setVisibility(View.INVISIBLE);
                    binding.tvErrorPhone.setText("");
                }
                if(!matcherEmail.matches()==true) {
                    binding.imgErrorEmail.setVisibility(View.VISIBLE);
                    binding.tvErrorEmail.setText("Email của bạn chưa đúng");
                }
                else{
                    binding.imgErrorEmail.setVisibility(View.INVISIBLE);
                    binding.tvErrorEmail.setText("");
                }
                if(!matcherUser.matches()== true) {
                    binding.imgErrorUsername.setVisibility(View.VISIBLE);
                    binding.tvErrorUsername.setText("username phải chứa ít nhất 6 kí tự và không có kí từ đặc biệt");
                }
                else {
                    binding.imgErrorUsername.setVisibility(View.INVISIBLE);
                    binding.tvErrorUsername.setText("");
                }

                if(!matcherPass.matches()== true) {
                    binding.imgErrorPass1.setVisibility(View.VISIBLE);
                    binding.tvErrorPass.setText("password phải có ít nhất 8 kí tự và chứa ít nhất một chữ in hoa , chữ thường, số và kí tự đặc biệt");
                }
                else if(binding.tvErrorPass.getText().toString().equals(binding.etUser.getText().toString())) {
                    binding.imgErrorPass1.setVisibility(View.VISIBLE);
                    binding.tvErrorPass.setText("Password không được trùng với Username");
                }
                else{
                    binding.imgErrorPass1.setVisibility(View.INVISIBLE);
                    binding.tvErrorPass.setText("");
                }
                if(! binding.etPass2.getText().toString().equals(binding.etPass.getText().toString()) == true || ! matcherPass2.matches()== true ) {
                    binding.imgErrorPass2.setVisibility(View.VISIBLE);
                    binding.tvErrorPass2.setText("Mật khẩu chưa trùng khớp ");
                }
                if( matcherPhone.matches()== true && matcherEmail.matches()==true && matcherUser.matches()== true && matcherPass.matches()== true &&  matcherPass2.matches()== true && binding.etPass2.getText().toString().equals(binding.etPass.getText().toString()) == true) {
                    String username = binding.etUser.getText().toString();
                    String phone = binding.etPhone.getText().toString();
                    String email = binding.etEmail.getText().toString();
                    String pass = binding.etPass.getText().toString();
                    User user = new User(0,username,pass,phone,email,"null","600");
                    MainActivity.sqlUserNew.insertUser(user);
                    Intent intent = new Intent(getBaseContext(),LogInActivity.class);
                    startActivity(intent);
                }


            }


        });




    }
}
