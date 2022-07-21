import java.io.FileWriter;
import java.io.IOException;

public class JsonFileCreator {
    public FileWriter createFileToWrite(String directory, String fileName) throws IOException {
        return new FileWriter(directory + "/" + fileName + ".json", true);
    }
}
