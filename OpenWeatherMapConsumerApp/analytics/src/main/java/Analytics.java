import javax.jms.JMSException;
import java.io.IOException;
import java.util.ArrayList;

public class Analytics {
    public static void main(String[] argv) throws JMSException, IOException {
        String databaseName = "weatherDatabase.db";
        // String rootDirectory = argv[0];
        String rootDirectory = "C:/Users/kiara/Desktop/OpenWeatherMapConsumerApp/analytics";
        String databaseDirectory = rootDirectory + "/" + databaseName;
        WeatherDatabaseManager weatherDatabase = new WeatherDatabaseManager();
        weatherDatabase.openDatabase(databaseDirectory);
        weatherDatabase.createNewTable(databaseDirectory);
        TopicReceiver receiver = new TopicReceiver();
        receiver.receiveTopicEvent(rootDirectory, databaseDirectory);
        ArrayList<ArrayList<String>> databaseContentList = weatherDatabase.databaseGet(databaseDirectory);
        DataExploiter dataExploiter = new DataExploiter();
        dataExploiter.createLineChart(rootDirectory, databaseContentList);
    }
}

