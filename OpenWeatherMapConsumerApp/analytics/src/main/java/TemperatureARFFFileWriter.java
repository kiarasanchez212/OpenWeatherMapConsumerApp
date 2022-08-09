import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TemperatureARFFFileWriter {
    public void writeTemperatureARFFFile(ArrayList<ArrayList<String>> databaseContentList, File temperatureARFFFile) {
        for (ArrayList<String> strings : databaseContentList) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(temperatureARFFFile, true);
                fileWriter.write(strings.get(0) + "," + strings.get(3) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert fileWriter != null;
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
