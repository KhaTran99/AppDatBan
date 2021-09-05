package json;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdatban.MainActivity;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import TimeOder.Day;
import TimeOder.Hours;
import home.ItemHome;
import location.Location;
import menu.Menu;
import news.News;
import notification.Notification;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import order.MainActivityOrder;
import promotion.Promotion;

public class GetJson extends AppCompatActivity {


    static String URL_PROMOTION = "https://demo4368667.mockable.io/promotion";
    static String URL_MENU = "https://demo4368667.mockable.io/menu";
    static String URL_HOTMENU = "https://demo4368667.mockable.io/hotmenu";
    static String URL_LOCATION = "https://demo4368667.mockable.io/location";
    static String URL_NEWS = "https://demo4368667.mockable.io/news";
    static String URL_TITLEHOME = "https://demo4368667.mockable.io/titlehome";
    static String URL_DAYODER = "https://demo4368667.mockable.io/day";
    static String URL_HOURSODER = "https://demo4368667.mockable.io/hours";
    static String URL_NOTIFICATION = "https://demo4368667.mockable.io/notification";


    public static void geJsonPromotion() {
        OkHttpClient client = new OkHttpClient();

        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, Promotion.class);
        final JsonAdapter<List<Promotion>> jsonAdapter = moshi.adapter(usersType);

        // Tạo request lên server.
        Request request = new Request.Builder()
//                .url("https://api.github.com/users")
                .url(URL_PROMOTION)
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();
                MainActivity.promotionList = jsonAdapter.fromJson(json);
                MainActivity.promotionList.size();
//                final List<Promotion> users = jsonAdapter.fromJson(json);
//                for(int i=0 ; i< users.size(); i++) {
//
//
//                    MainActivity.promotionList.add(users.get(i));
//                }
            }
        });

    }

    public static void getJsonMenu() {
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, Menu.class);
        final JsonAdapter<List<Menu>> jsonAdapter = moshi.adapter(usersType);

        Request request = new Request.Builder()
                .url(URL_MENU)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                MainActivity.menuList = jsonAdapter.fromJson(json);
                MainActivity.menuList.size();

            }
        });

    }

    public static void getJsonLocation() {
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, Location.class);
        final JsonAdapter<List<Location>> jsonAdapter = moshi.adapter(usersType);

        Request request = new Request.Builder()
                .url(URL_LOCATION)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                MainActivity.locationList = jsonAdapter.fromJson(json);
                MainActivity.locationList.size();

            }
        });

    }

    public static void getJsonNotification() {
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, Notification.class);
        final JsonAdapter<List<Notification>> jsonAdapter = moshi.adapter(usersType);

        Request request = new Request.Builder()
                .url(URL_NOTIFICATION)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                MainActivity.notificationList = jsonAdapter.fromJson(json);
                MainActivity.notificationList.size();


            }
        });

    }

    public  static void getJsonHotMenu() {
        List<Menu> hotMenu = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, Menu.class);
        final JsonAdapter<List<Menu>> jsonAdapter = moshi.adapter(usersType);

        Request request = new Request.Builder()
                .url(URL_HOTMENU)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                MainActivity.hotMenuList = jsonAdapter.fromJson(json);

            }
        });
    }

    public static void getJsonNews() {
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, News.class);
        final JsonAdapter<List<News>> jsonAdapter = moshi.adapter(usersType);

        Request request = new Request.Builder()
                .url(URL_NEWS)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                MainActivity.newsList = jsonAdapter.fromJson(json);
                MainActivity.newsList.size();

            }
        });

    }

    public static void getJsonTitleHome() {
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, ItemHome.class);
        final JsonAdapter<List<ItemHome>> jsonAdapter = moshi.adapter(usersType);

        Request request = new Request.Builder()
                .url(URL_TITLEHOME)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                MainActivity.itemHomeList = jsonAdapter.fromJson(json);
                MainActivity.itemHomeList.size();

            }
        });
    }

    public static void getJsonDayOder() {
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, Day.class);
        final JsonAdapter<List<Day>> jsonAdapter = moshi.adapter(usersType);

        Request request = new Request.Builder()
                .url(URL_DAYODER)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                MainActivity.dayList = jsonAdapter.fromJson(json);

            }
        });
    }
    public static void getJsonHoursOder() {
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, Hours.class);
        final JsonAdapter<List<Hours>> jsonAdapter = moshi.adapter(usersType);

        Request request = new Request.Builder()
                .url(URL_HOURSODER)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                MainActivityOrder.hoursList = jsonAdapter.fromJson(json);
                MainActivityOrder.hoursList.size();

            }
        });
    }


}