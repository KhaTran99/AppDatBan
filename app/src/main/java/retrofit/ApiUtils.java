package retrofit;

public class ApiUtils {
    public static final String BASE_URL = "https://demo4368667.mockable.io/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }

}
