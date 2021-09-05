package retrofit2;

public class ApiUtils2 {
    public static final String BASE_URL = "http://demo2123850.mockable.io/";

    public static SOService2 getSOService() {
        return RetrofitClient2.getClient(BASE_URL).create(SOService2.class);
    }

}
