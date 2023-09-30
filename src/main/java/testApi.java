import entity.Grade;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.text.html.*;

import java.io.IOException;
import java.util.Arrays;

public class testApi {

    private static final String API_URL = "https://www.eventbriteapi.com/v3/users/me";

    private static final String API_TOKEN = System.getenv("TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }

    public static String getUser(String token) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://www.eventbriteapi.com/v3/users/me/?token=%s", API_TOKEN))
                .addHeader("Content-Type", "text/html")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            String name = responseBody.getString("name");
            return name.toString();

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEmail(String token) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://www.eventbriteapi.com/v3/users/me/?token=%s", API_TOKEN))
                .addHeader("Content-Type", "text/html")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            String email = responseBody.getJSONArray("emails").getJSONObject(0).getString("email");
            return email.toString();

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        System.out.println("Authentication successful.");

        System.out.print("User: " + getUser(API_TOKEN) + "\n");

        System.out.println("Email: " + getEmail(API_TOKEN));
    }

}
