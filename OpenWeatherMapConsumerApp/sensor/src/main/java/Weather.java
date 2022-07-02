import java.time.Instant;

public class Weather {
    private Instant ts;
    private Location location;
    private String weather;
    private Double temp;
    private Double wind;
    private Double windDirection;
    private Double humidity;
    private Double pressure;

    public Weather(Instant ts, Location location, String weather, Double temp, Double wind, Double windDirection, Double humidity, Double pressure) {
        this.ts = ts;
        this.location = location;
        this.weather = weather;
        this.temp = temp;
        this.wind = wind;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.pressure = pressure;
    }


    public Instant getTs() {
        return ts;
    }

    public void setTs(Instant ts) {
        this.ts = ts;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location jsonObject) {
        this.location = jsonObject;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
}
