import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.time.Instant;

public class OpenWeatherMapRequester implements HttpRequester {
    private final HttpClient httpClient;
    private HttpGet get;
    private String resource;
    private Weather weather;

    public OpenWeatherMapRequester() {
        this.httpClient = HttpClients.createDefault();
        this.get = null;
        this.resource = null;
        this.weather = null;
    }

    @Override
    public Weather getWeather() {
        this.get = new HttpGet("https://api.openweathermap.org/data/2.5/weather?q=London&appid=4689809baa6b2cd19433521f80dc5830&units=metric");
        try {
            HttpResponse response = this.httpClient.execute(this.get);
            this.resource = EntityUtils.toString(response.getEntity());
            this.weather = deserializeJsonObjectToWeather(this.resource);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return this.weather;
    }

    private Weather deserializeJsonObjectToWeather(String apiResponse) {
        JsonObject apiResponseJsonObject = new Gson().fromJson(apiResponse, JsonObject.class);
        Location location = new Gson().fromJson(apiResponseJsonObject.get("coord").toString(), Location.class);
        JsonObject weatherJsonObject = apiResponseJsonObject.get("weather").getAsJsonArray().get(0).getAsJsonObject();
        String weather = weatherJsonObject.get("main").getAsString();
        JsonObject mainJsonObject = apiResponseJsonObject.get("main").getAsJsonObject();
        Double temp = mainJsonObject.get("temp").getAsDouble();
        JsonObject windJsonObject = apiResponseJsonObject.get("wind").getAsJsonObject();
        Double wind = windJsonObject.get("speed").getAsDouble();
        Double windDirection = windJsonObject.get("deg").getAsDouble();
        Double humidity = mainJsonObject.get("humidity").getAsDouble();
        Double pressure = mainJsonObject.get("pressure").getAsDouble();
        return new Weather(Instant.now(), location, weather, temp, wind, windDirection, humidity, pressure);
    }

}