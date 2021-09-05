package order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appdatban.MainActivity;
import com.example.appdatban.databinding.ListOderFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import historyoder.HistoryOder;
import notification.AdapterNotification;
import retrofit.ApiUtils;
import retrofit.SOService;
import retrofit2.ApiUtils2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.SOService2;
import sqlhelper.SQLListOder;
import user.User;

public class FragmentListOder extends Fragment {


    AdapterListOder adapterListOder;
    List<Oder> oderList = new ArrayList<>();
    SOService2 soService2;
    SOService mService;
    public static FragmentListOder newInstance() {

        Bundle args = new Bundle();

        FragmentListOder fragment = new FragmentListOder();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListOderFragmentBinding  binding = ListOderFragmentBinding.inflate(inflater,container,false);
        soService2 = ApiUtils2.getSOService();
        mService = ApiUtils.getSOService();

        MainActivity.sqlListOder = new SQLListOder(getContext());
        oderList = MainActivity.sqlListOder.getAllListOder();
        mService.getJsonOder().enqueue(new Callback<List<Oder>>() {
            @Override
            public void onResponse(Call<List<Oder>> call, Response<List<Oder>> response) {
                if(oderList.size()==0 ) {
                    oderList = response.body();
                    adapterListOder = new AdapterListOder(oderList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(binding.rlOder.getContext(), LinearLayoutManager.VERTICAL, false);
                    binding.rlOder.setLayoutManager(layoutManager);
                    binding.rlOder.setAdapter(adapterListOder);
                    for(int i = 0 ; i < response.body().size(); i++) {
                        MainActivity.sqlListOder.insertListOder(response.body().get(i));

                    }

                }
                adapterListOder = new AdapterListOder(oderList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(binding.rlOder.getContext(), LinearLayoutManager.VERTICAL, false);
                binding.rlOder.setLayoutManager(layoutManager);
                binding.rlOder.setAdapter(adapterListOder);


            }

            @Override
            public void onFailure(Call<List<Oder>> call, Throwable t) {

            }
        });


//        soService2.getJsonHistoryOder().enqueue(new Callback<List<Oder>>() {
//            @Override
//            public void onResponse(Call<List<Oder>> call, Response<List<Oder>> response) {
//                oderList = response.body();
//                for(int i = 0 ; i < response.body().size(); i++) {
//                    MainActivity.sqlListOder.insertListOder(response.body().get(i));
//                }
//                Toast.makeText(getContext(),""+oderList.size(),Toast.LENGTH_LONG).show();
//                adapterListOder = new AdapterListOder(oderList);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(binding.rlOder.getContext(), LinearLayoutManager.VERTICAL, false);
//                binding.rlOder.setLayoutManager(layoutManager);
//                binding.rlOder.setAdapter(adapterListOder);
//            }
//
//            @Override
//            public void onFailure(Call<List<Oder>> call, Throwable t) {
//
//            }
//        });

        return binding.getRoot();
    }
}
