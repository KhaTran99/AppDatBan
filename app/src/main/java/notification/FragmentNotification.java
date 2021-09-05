package notification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appdatban.MainActivity;
import com.example.appdatban.databinding.HomeFragmentBinding;
import com.example.appdatban.databinding.NotificationFragmentBinding;

import java.util.List;

import menu.AdapterFragmentMenu;
import retrofit.ApiUtils;
import retrofit.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sqlhelper.SQLNotification;

public class FragmentNotification extends Fragment {
    AdapterNotification adapterNotification;
    private SOService mService;
    public static FragmentNotification newInstance() {

        Bundle args = new Bundle();

        FragmentNotification fragment = new FragmentNotification();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NotificationFragmentBinding binding = NotificationFragmentBinding.inflate(inflater,container,false);
        mService = ApiUtils.getSOService();
        MainActivity.sqlNotification = new SQLNotification(getContext());
        MainActivity.notificationList = MainActivity.sqlNotification.getAllNotification();
        mService.getJsonNotification().enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if(MainActivity.notificationList.size()==0 ) {
                    MainActivity.notificationList = response.body();
                    adapterNotification = new AdapterNotification(MainActivity.notificationList, getContext());
                    LinearLayoutManager layoutManagerNotification = new LinearLayoutManager(binding.rlNotification.getContext(), LinearLayoutManager.VERTICAL, false);
                    binding.rlNotification.setLayoutManager(layoutManagerNotification);
                    binding.rlNotification.setAdapter(adapterNotification);
                    for(int i = 0 ; i< response.body().size();i++) {
                        MainActivity.sqlNotification.insertNotification(response.body().get(i));
                    }

                }

                adapterNotification = new AdapterNotification(MainActivity.notificationList, getContext());
                LinearLayoutManager layoutManagerNotification = new LinearLayoutManager(binding.rlNotification.getContext(), LinearLayoutManager.VERTICAL, false);
                binding.rlNotification.setLayoutManager(layoutManagerNotification);
                binding.rlNotification.setAdapter(adapterNotification);
                adapterNotification.setOnClickNotification(new onClickNotification() {
                    @Override
                    public void onClickItem(int position,Notification notification) {
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle(notification.getTitle())
                                .setMessage(notification.getContent())
                                .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.notificationList.get(position).setStatus(0);
                                        MainActivity.sqlNotification.updateNOtification(MainActivity.notificationList.get(position));
                                        adapterNotification.notifyDataSetChanged();
                                    }
                                })
                                .create();
                        alertDialog.show();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {

            }
        });


        return binding.getRoot();
    }
}
