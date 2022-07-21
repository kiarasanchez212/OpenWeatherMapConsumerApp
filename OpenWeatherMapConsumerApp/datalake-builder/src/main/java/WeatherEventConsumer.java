import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class WeatherEventConsumer implements EventConsumer {
    @Override
    public void writeEventsInsideFile(String directory, String weatherJsonEvent) {
        JsonObject weatherJsonObject = new Gson().fromJson(weatherJsonEvent, JsonObject.class);
        String epochByHour = getEpochByHourFromWeatherJsonEvent(weatherJsonObject);
        JsonFileCreator creator = new JsonFileCreator();
        try {
            FileWriter file = creator.createFileToWrite(directory, epochByHour);
            file.write(weatherJsonObject + "\n");
            file.close();
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }

    private String getEpochByHourFromWeatherJsonEvent(JsonObject weatherJsonObject) {
        String ts = weatherJsonObject.get("ts").getAsString();
        return ts.substring(0,4) + ts.substring(5,7) + ts.substring(8,10) + ts.substring(11,13);
    }
}
