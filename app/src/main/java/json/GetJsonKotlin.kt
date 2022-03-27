//package json
//
//import TimeOder.Day
//import TimeOder.Hours
//import android.util.Log
//import com.example.appdatban.MainActivity
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.Types
//import home.ItemHome
//import location.Location
//import menu.Menu
//import news.News
//import notification.Notification
//import okhttp3.*
//import order.MainActivityOrder
//import promotion.Promotion
//import java.io.IOException
//import java.lang.reflect.Type
//import java.util.*
//
//class GetJsonKotlin {
//    var URL_PROMOTION = "https://demo4368667.mockable.io/promotion"
//    var URL_MENU = "https://demo4368667.mockable.io/menu"
//    var URL_HOTMENU = "https://demo4368667.mockable.io/hotmenu"
//    var URL_LOCATION = "https://demo4368667.mockable.io/location"
//    var URL_NEWS = "https://demo4368667.mockable.io/news"
//    var URL_TITLEHOME = "https://demo4368667.mockable.io/titlehome"
//    var URL_DAYODER = "https://demo4368667.mockable.io/day"
//    var URL_HOURSODER = "https://demo4368667.mockable.io/hours"
//    var URL_NOTIFICATION = "https://demo4368667.mockable.io/notification"
//
//
//    fun geJsonPromotion() {
//        val client = OkHttpClient()
//
//        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, Promotion::class.java)
//        val jsonAdapter = moshi.adapter<List<Promotion>>(usersType)
//
//        // Tạo request lên server.
//        val request = Request.Builder() //                .url("https://api.github.com/users")
//                .url(URL_PROMOTION)
//                .build()
//
//        // Thực thi request.
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//
//                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
//                val json = response.body().string()
//                MainActivity.promotionList = jsonAdapter.fromJson(json)
//                MainActivity.promotionList.size
//                //                final List<Promotion> users = jsonAdapter.fromJson(json);
////                for(int i=0 ; i< users.size(); i++) {
////
////
////                    MainActivity.promotionList.add(users.get(i));
////                }
//            }
//        })
//    }
//
//    fun getJsonMenu() {
//        val client = OkHttpClient()
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, Menu::class.java)
//        val jsonAdapter = moshi.adapter<List<Menu>>(usersType)
//        val request = Request.Builder()
//                .url(URL_MENU)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val json = response.body().string()
//                MainActivity.menuList = jsonAdapter.fromJson(json)
//                MainActivity.menuList.size
//            }
//        })
//    }
//
//    fun getJsonLocation() {
//        val client = OkHttpClient()
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, Location::class.java)
//        val jsonAdapter = moshi.adapter<List<Location>>(usersType)
//        val request = Request.Builder()
//                .url(URL_LOCATION)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val json = response.body().string()
//                MainActivity.locationList = jsonAdapter.fromJson(json)
//                MainActivity.locationList.size
//            }
//        })
//    }
//
//    fun getJsonNotification() {
//        val client = OkHttpClient()
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, Notification::class.java)
//        val jsonAdapter = moshi.adapter<List<Notification>>(usersType)
//        val request = Request.Builder()
//                .url(URL_NOTIFICATION)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val json = response.body().string()
//                MainActivity.notificationList = jsonAdapter.fromJson(json)
//                MainActivity.notificationList.size
//            }
//        })
//    }
//
//    fun getJsonHotMenu() {
//        val hotMenu: List<Menu> = ArrayList()
//        val client = OkHttpClient()
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, Menu::class.java)
//        val jsonAdapter = moshi.adapter<List<Menu>>(usersType)
//        val request = Request.Builder()
//                .url(URL_HOTMENU)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val json = response.body().string()
//                MainActivity.hotMenuList = jsonAdapter.fromJson(json)
//            }
//        })
//    }
//
//    fun getJsonNews() {
//        val client = OkHttpClient()
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, News::class.java)
//        val jsonAdapter = moshi.adapter<List<News>>(usersType)
//        val request = Request.Builder()
//                .url(URL_NEWS)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val json = response.body().string()
//                MainActivity.newsList = jsonAdapter.fromJson(json)
//                MainActivity.newsList.size
//            }
//        })
//    }
//
//    fun getJsonTitleHome() {
//        val client = OkHttpClient()
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, ItemHome::class.java)
//        val jsonAdapter = moshi.adapter<List<ItemHome>>(usersType)
//        val request = Request.Builder()
//                .url(URL_TITLEHOME)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val json = response.body().string()
//                MainActivity.itemHomeList = jsonAdapter.fromJson(json)
//                MainActivity.itemHomeList.size
//            }
//        })
//    }
//
//    fun getJsonDayOder() {
//        val client = OkHttpClient()
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, Day::class.java)
//        val jsonAdapter = moshi.adapter<List<Day>>(usersType)
//        val request = Request.Builder()
//                .url(URL_DAYODER)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val json = response.body().string()
//                MainActivityOrder.dayList = jsonAdapter.fromJson(json)
//            }
//        })
//    }
//
//    fun getJsonHoursOder() {
//        val client = OkHttpClient()
//        val moshi = Moshi.Builder().build()
//        val usersType: Type = Types.newParameterizedType(MutableList::class.java, Hours::class.java)
//        val jsonAdapter = moshi.adapter<List<Hours>>(usersType)
//        val request = Request.Builder()
//                .url(URL_HOURSODER)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error", "Network Error")
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val json = response.body().string()
//                MainActivityOrder.hoursList = jsonAdapter.fromJson(json)
//                MainActivityOrder.hoursList.size
//            }
//        })
//    }
//
//
//}