package user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appdatban.MainActivity;
import com.example.appdatban.R;
import com.example.appdatban.databinding.EditUserFragmentBinding;

public class FragmentEditUsert extends Fragment {
    EditUserFragmentBinding binding;
    public static FragmentEditUsert newInstance() {

        Bundle args = new Bundle();

        FragmentEditUsert fragment = new FragmentEditUsert();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = EditUserFragmentBinding.inflate(inflater,container,false);
        if(MainActivity.user != null) {
            Glide.with(getContext())
                    .load(MainActivity.user.getAvatar())
                    .circleCrop()
                    .error(R.drawable.ic_user)
                    .into(binding.imgAvt);

            binding.etUser.setText(MainActivity.user.getUsername());
            binding.etPhone.setText(MainActivity.user.getPhoneNumber());
            binding.etEmail.setText(MainActivity.user.getEmail());
            binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(binding.etPass.getText().toString().equals(MainActivity.user.getPassword())) {
                        String username = binding.etUser.getText().toString();
                        String pass = binding.etPass2.getText().toString();
                        String email = binding.etEmail.getText().toString();
                        String phone = binding.etPhone.getText().toString();
                        User user = new User(0,username,pass,email,phone,MainActivity.user.getAvatar(),MainActivity.user.getScores());
                        MainActivity.sqlUser.updateUser(user);
                        GetFragment(FragmentUser.newInstance());

                        
                    }
                    else{
                        binding.imgErrorPass1.setVisibility(View.VISIBLE);
                        binding.tvErrorPass.setText("Mật khẩu không đúng");
                    }
                }
            });
        }
        return binding.getRoot();
    }
    public void GetFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.Frame, fragment).commit();
    }
}
