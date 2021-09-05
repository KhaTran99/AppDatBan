package com.example.appdatban;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.appdatban.databinding.ActivityMainBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import TimeOder.Day;
import home.FragmentHome;
import home.ItemHome;
import location.Location;
import menu.Menu;
import news.News;
import notification.FragmentNotification;
import notification.Notification;
import order.MainActivityOrder;
import promotion.FragmentPromotion;
import promotion.Promotion;
import retrofit.ApiUtils;
import retrofit.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sqlhelper.SQLHotMenu;
import sqlhelper.SQLListOder;
import sqlhelper.SQLLocation;
import sqlhelper.SQLMenu;
import sqlhelper.SQLNews;
import sqlhelper.SQLNotification;
import sqlhelper.SQLPromotion;
import sqlhelper.SQLPromotionUser;
import sqlhelper.SQLUser;
import sqlhelper.SQLUserNew;
import user.FragmentUser;
import user.User;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private android.location.Location location;
    private GoogleApiClient gac;
    public static String NAMECITY;
    private static final String TAG = "MainActivity";
    public static List<ItemHome> itemHomeList = new ArrayList<>();
    public static List<Menu> menuList = new ArrayList<>();
    public static List<Menu> hotMenuList = new ArrayList<>();
    public static List<Location> locationList = new ArrayList<>();
    public static List<Promotion> promotionList = new ArrayList<>();
    public static List<Notification> notificationList = new ArrayList<>();
    public static List<News> newsList = new ArrayList<>();
    public static List<Day> dayList = new ArrayList<>();
    public static User user = null ;

    private SOService mService;
    public static SQLHotMenu sqlHotMenu;
    public static SQLLocation sqlLocation;
    public static SQLMenu sqlMenu;
    public static SQLNews sqlNews;
    public static SQLNotification sqlNotification;
    public static SQLPromotion sqlPromotion;
    public static SQLPromotionUser sqlPromotionUser;
    public static SQLUser sqlUser;
    public static SQLUserNew sqlUserNew;
    public static SQLListOder sqlListOder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        checkAndRequestPermissions();
        mService = ApiUtils.getSOService();
        checkInternet();
        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
        }
        sqlUser = new SQLUser(this);
        sqlUserNew = new SQLUserNew(this);
        List<User> userList = sqlUser.getAllUser();
        if(userList.size()>0) {
            user = userList.get(userList.size()-1);
        }


//        mService.getJsonUser().enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                user = response.body();
//                sqlUserNew.insertUser(response.body());
//                if(user == null) {
//                    sqlUser.insertUser(response.body());
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });



        if(ConnectionReceiver.isConnected()==true) {

            mService.getJsonDay().enqueue(new Callback<List<Day>>() {
                @Override
                public void onResponse(Call<List<Day>> call, Response<List<Day>> response) {
                    dayList = response.body();




                }

                @Override
                public void onFailure(Call<List<Day>> call, Throwable t) {

                }
            });

        }
        if (ConnectionReceiver.isConnected() == true) {


            getFragment(FragmentHome.newInstance());
            binding.tvCheckInternet.setText("");
        } else {

            binding.tvCheckInternet.setText("Chưa kết nối Internet");
        }
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectionReceiver.isConnected() == true) {
                    getFragment(FragmentHome.newInstance());
                    binding.tvCheckInternet.setText("");
                } else {

                   binding.tvCheckInternet.setText("Chưa kết nối Internet");
                }
            }
        });
        binding.btnPomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectionReceiver.isConnected() == true) {
                    getFragment(FragmentPromotion.newInstance());
                    binding.tvCheckInternet.setText("");
                } else {

                    binding.tvCheckInternet.setText("Chưa kết nối Internet");
                }
            }
        });
        binding.btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ConnectionReceiver.isConnected() == true) {
                    getFragment(FragmentNotification.newInstance());
                    binding.tvCheckInternet.setText("");
                } else {

                    binding.tvCheckInternet.setText("Chưa kết nối Internet");
                }
            }
        });
        binding.btnPeson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ConnectionReceiver.isConnected() == true) {
                    getFragment(FragmentUser.newInstance());
                    binding.tvCheckInternet.setText("");
                } else {

                    binding.tvCheckInternet.setText("Chưa kết nối Internet");
                }
            }
        });
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectionReceiver.isConnected() == true) {
                    Intent intent = new Intent(getBaseContext(), MainActivityOrder.class);
                    startActivityForResult(intent, 10);
                    binding.tvCheckInternet.setText("");
                } else {

                    binding.tvCheckInternet.setText("Chưa kết nối Internet");
                }

            }
        });
    }

    public void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Frame, fragment).commit();
    }

    public void checkInternet() {
        boolean ret = ConnectionReceiver.isConnected();

        if (ret == true) {
            String msg = "Thiết bị đã kết nối internet";
//            Toast.makeText( getBaseContext(), msg, Toast.LENGTH_LONG).show();
        } else {
            String msg = "Thiết bị chưa kết nối internet";
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }

    }
    public void dispLocation(View view) {
        getttLocationCheck();
    }
    private void getttLocationCheck() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Kiểm tra quyền hạn
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        } else {
            location = LocationServices.FusedLocationApi.getLastLocation(gac);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                // Hiển thị
//                tvLocation.setText(latitude + ", " + longitude);
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                String cityName = addresses.get(0).getLocality();
                if(cityName== null) {
                    cityName = addresses.get(0).getAdminArea();
                }

                NAMECITY = cityName;
            } else {
//                tvLocation.setText("(Không thể hiển thị vị trí. " +
//                        "Bạn đã kích hoạt location trên thiết bị chưa?)");
            }
        }
    }
    protected synchronized void buildGoogleApiClient() {
        if (gac == null) {
            gac = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {

        }
    }

    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getttLocationCheck();
    }

    @Override
    public void onConnectionSuspended(int i) {

        gac.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Lỗi kết nối: " + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();

    }
    protected  void onStart() {
        gac.connect();
        super.onStart();
    }
    protected void onStop() {
        gac.disconnect();
        super.onStop();
    }
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1000).show();
            } else {
                Toast.makeText(this, "Thiết bị này không hỗ trợ.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }
}