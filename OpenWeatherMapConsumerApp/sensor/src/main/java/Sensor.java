import java.util.Timer;

public class Sensor {
    public static void main(String[] argv) {
        SensorTask sensorTask = new SensorTask();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(sensorTask, 0, 5000); //900000
    }
}
