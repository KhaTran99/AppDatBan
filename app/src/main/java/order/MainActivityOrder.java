package order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appdatban.MainActivity;
import com.example.appdatban.R;
import com.example.appdatban.databinding.ActivityMainOrderBinding;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import TimeOder.AdapterHours;
import TimeOder.Day;
import TimeOder.Hours;
import TimeOder.OnClickHouse;
import location.Location;
import retrofit.ApiUtils;
import retrofit.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityOrder extends AppCompatActivity {
    ActivityMainOrderBinding binding;
    AdapterHours adapterHours;
    AdapterTopOder adapterTopOder;
    Timer timer;
    TimerTask timerTask;
    int position;
    LinearLayoutManager layoutManagerTop;
    public static List<Hours> hoursList = new ArrayList<>();
    private int ID_LOCATION;
    private String LOCATION_ODER;
    private int AMOUNT_ODER;
    private int ID_DAY;
    private int ID_HOUSE;
    private  int P_HOUSE_NEw = -1 ;
    private int ID_PROMOTION;
    private String DAY;
    private String HOURS;
    private String NOTE;
    private SOService mService;
    List<String> stringListDay = new ArrayList<>();
    List<String> listLocation = new ArrayList<>();
    List<String> listAmount = new ArrayList<String>();
    Location locationIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_order);
        Intent intent = getIntent();
        mService = ApiUtils.getSOService();

        for (int i = 0; i < MainActivity.dayList.size(); i++) {
            stringListDay.add(MainActivity.dayList.get(i).getDayOfTheWeek() + " " + MainActivity.dayList.get(i).getDate());
        }
        ArrayAdapter<String> stringArrayAdapterDay;
        stringArrayAdapterDay = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, stringListDay);
        binding.SpDay.setAdapter(stringArrayAdapterDay);
        binding.SpDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                DAY = MainActivity.dayList.get(position).getDayOfTheWeek();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        mService.getJsonHours().enqueue(new Callback<List<Hours>>() {
            @Override
            public void onResponse(Call<List<Hours>> call, Response<List<Hours>> response) {
                hoursList = response.body();
                adapterHours = new AdapterHours(hoursList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(binding.rlTime.getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.rlTime.setLayoutManager(layoutManager);
                binding.rlTime.setAdapter(adapterHours);
                adapterHours.setOnClickHouse(new OnClickHouse() {
                    @Override
                    public void OnClickItem(int p, Hours hours) {
                        P_HOUSE_NEw = p;
                       if(hours.getStatus()== 0) {
                           for(int i = 0 ; i < hoursList.size(); i++) {
                               hoursList.get(i).setStatus(0);
                           }
                           hoursList.get(P_HOUSE_NEw).setStatus(1);
                           HOURS = hoursList.get(P_HOUSE_NEw).getHoursOfDay();


                           adapterHours.notifyDataSetChanged();
                       }
                       else {
                           hoursList.get(P_HOUSE_NEw).setStatus(0);
                           adapterHours.notifyDataSetChanged();
                       }


                    }
                });

            }

            @Override
            public void onFailure(Call<List<Hours>> call, Throwable t) {

            }
        });

        adapterTopOder = new AdapterTopOder(MainActivity.promotionList, getBaseContext());
        layoutManagerTop = new LinearLayoutManager(binding.rlTopOder.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rlTopOder.setLayoutManager(layoutManagerTop);
        binding.rlTopOder.setAdapter(adapterTopOder);
        if (MainActivity.promotionList != null) {
            position = Integer.MAX_VALUE / 2;
            binding.rlTopOder.scrollToPosition(position);
        }
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.rlTopOder);
        binding.rlTopOder.smoothScrollBy(5, 0);

        binding.rlTopOder.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {
                    stopAutoScrollBanner();
                } else if (newState == 0) {
                    position = layoutManagerTop.findFirstVisibleItemPosition();
                    runAutoScrollBanner();

                }
            }
        });





        for (int i = 0; i < MainActivity.locationList.size(); i++) {
            listLocation.add(MainActivity.locationList.get(i).getName());
        }
        ArrayAdapter<String> stringArrayAdapterLocation = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listLocation);
        binding.SpCuahang.setAdapter(stringArrayAdapterLocation);
        binding.SpCuahang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ID_LOCATION = position;
                binding.tvTitleOder.setText(MainActivity.locationList.get(ID_LOCATION).getName().toString());
                LOCATION_ODER = MainActivity.locationList.get(ID_LOCATION).getName().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.tvTitleOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse(MainActivity.locationList.get(ID_LOCATION).getAddress().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        for (int i = 1; i <= 30; i++) {
            listAmount.add(String.valueOf(i));
        }
        ArrayAdapter<String> stringArrayAdapterAmount = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listAmount);
        binding.SpSoluong.setAdapter(stringArrayAdapterAmount);
        binding.SpSoluong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                AMOUNT_ODER = Integer.parseInt(listAmount.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnOderOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NOTE = binding.etNote.getText().toString();
                if(MainActivity.user == null) {
                    Toast.makeText(getBaseContext(),"Bạn chưa đăng nhập ",Toast.LENGTH_LONG).show();
                }
                else {
                    if(NOTE == null) {
                        NOTE = "Không";
                    }
                    Oder oder = new Oder(0,LOCATION_ODER,AMOUNT_ODER,DAY,HOURS,1,"NOTE",4);
                    MainActivity.sqlListOder.insertListOder(oder);
                    Toast.makeText(getBaseContext(),"Đặt bàn thành công ",Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent1);
                }
            }
        });
        binding.btnBackOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        runAutoScrollBanner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAutoScrollBanner();
    }

    private void stopAutoScrollBanner() {
        if (timer != null && timerTask != null) {
            timerTask.cancel();
            timer.cancel();
            timer = null;
            timerTask = null;
            position = layoutManagerTop.findFirstCompletelyVisibleItemPosition();
        }
    }

    private void runAutoScrollBanner() {
        if (timer == null && timerTask == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (position == Integer.MAX_VALUE) {
                        position = Integer.MAX_VALUE / 2;
                        binding.rlTopOder.scrollToPosition(position);
                        binding.rlTopOder.smoothScrollBy(5, 0);
                    } else {
                        position++;
                        binding.rlTopOder.smoothScrollToPosition(position);
                    }
                }
            };
            timer.schedule(timerTask, 4000, 4000);
        }
    }
    void test(View view) {

    }
}