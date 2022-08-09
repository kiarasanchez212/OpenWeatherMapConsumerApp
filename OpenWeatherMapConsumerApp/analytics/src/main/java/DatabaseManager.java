import java.sql.Connection;
import java.util.ArrayList;

public interface DatabaseManager {
    Connection openDatabase(String databaseDirectory);

    void closeDatabase(Connection databaseConnection);

    void createNewTable(String databaseDirectory);

    void databasePut(Weather weather, String databaseDirectory);

    ArrayList<ArrayList<String>> databaseGet(String databaseDirectory);
}
