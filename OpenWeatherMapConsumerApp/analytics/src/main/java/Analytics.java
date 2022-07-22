import javax.jms.JMSException;

public class Analytics {
    public static void main(String[] argv) throws JMSException {
        String databaseName = "weatherDatabase.db";
        // String rootDirectory = argv[0];
        String rootDirectory = "C:/Users/kiara/Desktop/OpenWeatherMapConsumerApp/analytics";
        String databaseDirectory = rootDirectory + "/" + databaseName;
        WeatherDatabaseManager weatherDatabase = new WeatherDatabaseManager();
        weatherDatabase.openDatabase(databaseDirectory);
        weatherDatabase.createNewTable(databaseDirectory);
        TopicReceiver receiver = new TopicReceiver();
        receiver.receiveTopicEvent(rootDirectory, databaseDirectory);
    }
}

