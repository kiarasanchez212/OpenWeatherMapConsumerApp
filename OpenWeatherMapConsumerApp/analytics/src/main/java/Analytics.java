import java.io.File;
import java.util.ArrayList;

public class Analytics {
    public static void main(String[] argv) throws Exception {
        String databaseName = "weatherDatabase.db";
        String rootDirectory = argv[0];
        String databaseDirectory = rootDirectory + "/" + databaseName;
        WeatherDatabaseManager weatherDatabase = new WeatherDatabaseManager();
        weatherDatabase.openDatabase(databaseDirectory);
        weatherDatabase.createNewTable(databaseDirectory);
        TopicReceiver receiver = new TopicReceiver();
        receiver.receiveTopicEvent(rootDirectory, databaseDirectory);
        ArrayList<ArrayList<String>> databaseContentList = weatherDatabase.databaseGet(databaseDirectory);
        LinechartCreator linechartCreator = new LinechartCreator();
        linechartCreator.createLineChart(rootDirectory, databaseContentList);

        TemperatureARFFFileCreator temperatureARFFFileCreator = new TemperatureARFFFileCreator();
        File temperatureARFFFile = temperatureARFFFileCreator.createFileToWrite(rootDirectory);
        TemperatureARFFFileWriter temperatureARFFFileWriter = new TemperatureARFFFileWriter();
        temperatureARFFFileWriter.writeTemperatureARFFFile(databaseContentList, temperatureARFFFile);
        File outputFile = new File(rootDirectory + "/TemperatureLinearRegressionModel.model");
        TemperatureLinearRegression temperatureLinearRegression = new TemperatureLinearRegression(temperatureARFFFile, outputFile);
        temperatureLinearRegression.trainLinearRegressionModel();
    }
}

