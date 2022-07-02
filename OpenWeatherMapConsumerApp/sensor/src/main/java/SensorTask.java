import com.google.gson.*;

import javax.jms.JMSException;
import java.time.Instant;
import java.util.TimerTask;

public class SensorTask extends TimerTask {
    @Override
    public void run() {
        Weather weather = obtainWeatherFromOpenWeatherMapApi();
        String jsonWeatherEvent = serializeWeatherToJson(weather);
        try {
            TopicSender topicSender = new TopicSender();
            topicSender.sendTopicEvent(jsonWeatherEvent);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private Weather obtainWeatherFromOpenWeatherMapApi() {
        OpenWeatherMapRequester requester = new OpenWeatherMapRequester();
        return requester.getWeather();
    }

    private String serializeWeatherToJson(Weather weather) {
        Gson gsonSerializer = customizeGsonSerialization();
        return gsonSerializer.toJson(weather);
    }

    private Gson customizeGsonSerialization() {
        return new GsonBuilder().registerTypeAdapter(Instant.class,
                (JsonSerializer<Instant>) (instant, type, jsonSerializationContext) -> new JsonPrimitive(instant.toString())).create();
    }
}
