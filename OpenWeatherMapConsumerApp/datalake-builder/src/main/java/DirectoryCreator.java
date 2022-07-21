import java.io.File;

public class DirectoryCreator {
    public String createNewDirectoryFromRootDirectory(String rootDirectory) {
        String newDirectories = "/datalake/events/sensor.Weather";
        File fullDirectory = new File(rootDirectory + newDirectories);
        fullDirectory.mkdirs();
        System.out.println("directory: " + rootDirectory + newDirectories + " is created successfully");
        return (rootDirectory + newDirectories);
    }
}