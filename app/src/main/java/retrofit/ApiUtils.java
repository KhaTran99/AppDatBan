package retrofit;

public class ApiUtils {
    public static final String BASE_URL = "https://testapi.io/api/khatran99/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }

}
