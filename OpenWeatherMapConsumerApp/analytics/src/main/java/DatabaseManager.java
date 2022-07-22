import java.sql.Connection;

public interface DatabaseManager {
    Connection openDatabase(String databaseDirectory);

    void closeDatabase(Connection databaseConnection);

    void createNewTable(String databaseDirectory);

    void databasePut(Weather weather, String databaseDirectory);

    void databaseGet(String databaseDirectory);
}
