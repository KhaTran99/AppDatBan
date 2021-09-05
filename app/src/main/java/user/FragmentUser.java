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
import com.example.appdatban.databinding.UserFragmentBinding;
import com.example.appdatban.login.DangKiActivity;
import com.example.appdatban.login.LogInActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import order.FragmentListOder;
import retrofit.ApiUtils;
import retrofit.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentUser extends Fragment {
    private SOService mService;


    public static FragmentUser newInstance() {

        Bundle args = new Bundle();

        FragmentUser fragment = new FragmentUser();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserFragmentBinding binding = UserFragmentBinding.inflate(inflater,container,false);
    if(MainActivity.user != null) {
        Glide.with(getContext())
                .load(MainActivity.user.getAvatar())
                .circleCrop()
                .error(R.drawable.ic_user)
                .into(binding.imgAvatar);

        binding.tvUsername.setText(MainActivity.user.getUsername());
        binding.tvPhoneNumber.setText(MainActivity.user.getPhoneNumber());
        binding.tvEmail.setText(MainActivity.user.getEmail());
        binding.tvScores.setText(MainActivity.user.getScores());

        binding.btnLogin.setVisibility(View.GONE);
        binding.btnDangKi.setVisibility(View.GONE);
        binding.tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentListOder.newInstance());

            }
        });
        binding.btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentEditUsert.newInstance());
            }
        });
    }
    else {
        binding.btnLogOut.setVisibility(View.GONE);

    }
    binding.btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.sqlUser.deleteAllUser();

            Intent intentLogin = new Intent(getContext(), LogInActivity.class);
            startActivity(intentLogin);
        }
    });
    binding.btnDangKi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(getContext(), DangKiActivity.class);
            startActivity(intent);
        }
    });

        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sqlUser.deleteAllUser();

                Intent intentLogin = new Intent(getContext(), LogInActivity.class);
                startActivity(intentLogin);
            }
        });

        return binding.getRoot();

    }
    public void GetFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.Frame, fragment).commit();
    }
}
