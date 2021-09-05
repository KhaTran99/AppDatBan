package retrofit2;

import java.util.List;

import TimeOder.Day;
import TimeOder.Hours;
import historyoder.HistoryOder;
import home.ItemHome;
import location.Location;
import menu.Menu;
import news.News;
import notification.Notification;
import order.Oder;
import promotion.Promotion;
import retrofit2.http.GET;
import user.User;

public interface SOService2 {
    @GET("historyoder")
    Call<List<Oder>> getJsonHistoryOder();
}
