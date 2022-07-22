import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TemperatureARFFFileCreator {

    public File createFileToWrite(String rootDirectory) {
        File temperatureFile = new File(rootDirectory + "temperature.arff");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(temperatureFile);
            fileWriter.write("""
                    @RELATION temperature

                    @ATTRIBUTE ts  NUMERIC
                    @ATTRIBUTE temperature NUMERIC

                    @DATA
                    """);
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
        return temperatureFile;
    }
}
