import java.time.Instant;

public class Weather {
    private Instant ts;
    private Double lon;
    private Double lat;
    private Double temp;
    private Double humidity;
    private Double pressure;

    public Weather(Instant ts, Double lon, Double lat, Double temp, Double humidity, Double pressure) {
        this.ts = ts;
        this.lon = lon;
        this.lat = lat;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public Instant getTs() {
        return ts;
    }

    public void setTs(Instant ts) {
        this.ts = ts;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
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