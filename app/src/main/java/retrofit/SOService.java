package retrofit;

import java.util.List;

import TimeOder.Day;
import TimeOder.Hours;
import home.ItemHome;
import location.Location;
import menu.Menu;
import news.News;
import notification.Notification;
import order.Oder;
import promotion.Promotion;
import retrofit2.Call;
import retrofit2.http.GET;
import user.User;

public interface SOService {
    @GET("promotion")
    Call<List<Promotion>> getJsonPromotion();

    @GET("menu")
    Call<List<Menu>> getJsonMenu();

    @GET("hotmenu")
    Call<List<Menu>> getJsonHotMenu();

    @GET("notification")
    Call<List<Notification>> getJsonNotification();

    @GET("location")
    Call<List<Location>> getJsonLocation();

    @GET("news")
    Call<List<News>> getJsonNews();

    @GET("day")
    Call<List<Day>> getJsonDay();

    @GET("titlehome")
    Call<List<ItemHome>> getJsonTitleHome();

    @GET("hours")
    Call<List<Hours>> getJsonHours();
    @GET("historyoder")
    Call<List<Oder>> getJsonOder();
}
