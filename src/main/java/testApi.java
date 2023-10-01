import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class testApi {

    private static final String API_URL = "https://www.eventbriteapi.com/v3/users/me/";
    private static final String API_TOKEN = System.getenv("API_TOKEN");
    public static String getName(String token) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("Success response code: " + response.code());
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);


                if (jsonObject.has("emails")) {
                    String name = jsonObject.getString("name");
                    return name;
                } else {
                    return "name not found in the JSON response";
                }
            } else {
                System.out.println("Request failed with response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String email = getName(API_TOKEN);
        System.out.println("Name: " + email);
    }
}
