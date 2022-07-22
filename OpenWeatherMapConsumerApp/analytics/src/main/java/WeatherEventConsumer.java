import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.Instant;

public class WeatherEventConsumer implements EventConsumer{
    @Override
    public void fillDatabaseTable(String databaseDirectory, String textReceived) {
        Weather weather = obtainWeatherFromJsonEvent(textReceived);
        WeatherDatabaseManager weatherDatabase = new WeatherDatabaseManager();
        weatherDatabase.databasePut(weather, databaseDirectory);
    }

    private Weather obtainWeatherFromJsonEvent(String weatherJsonEvent) {
        JsonObject weatherJsonObject = new Gson().fromJson(weatherJsonEvent, JsonObject.class);
        Instant ts = Instant.parse(weatherJsonObject.get("ts").getAsString());
        JsonObject location = weatherJsonObject.get("location").getAsJsonObject();
        Double lat = location.get("lat").getAsDouble();
        Double lon = location.get("lon").getAsDouble();
        Double temp = weatherJsonObject.get("temp").getAsDouble();
        Double pressure = weatherJsonObject.get("pressure").getAsDouble();
        Double humidity = weatherJsonObject.get("humidity").getAsDouble();
        return new Weather(ts, lon, lat, temp, pressure, humidity);
    }
}
