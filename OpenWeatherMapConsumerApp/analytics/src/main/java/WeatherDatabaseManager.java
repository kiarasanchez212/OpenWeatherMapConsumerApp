import java.sql.*;

public class WeatherDatabaseManager implements DatabaseManager {

    public Connection openDatabase(String databaseDirectory) {
        String url = "jdbc:sqlite:" + databaseDirectory;
        Connection databaseConnection = null;
        try {
            databaseConnection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return databaseConnection;
    }

    @Override
    public void closeDatabase(Connection databaseConnection) {
        try {
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createNewTable(String fullDirectory) {
        String sql = "CREATE TABLE IF NOT EXISTS weather ("
                + "ts long,"
                + "lat double,"
                + "lon double,"
                + "temperature double,"
                + "pressure double,"
                + "humidity double"
                + ");";
        WeatherDatabaseManager weatherDatabaseManager = new WeatherDatabaseManager();
        Connection databaseConnection = weatherDatabaseManager.openDatabase(fullDirectory);
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        weatherDatabaseManager.closeDatabase(databaseConnection);
    }

    @Override
    public void databasePut(Weather weather, String databaseDirectory) {
        String sql = "INSERT INTO weather (ts,lat,lon,temperature, pressure, humidity) VALUES(?,?,?,?,?,? )";
        WeatherDatabaseManager weatherDatabaseManager = new WeatherDatabaseManager();
        Connection databaseConnection = weatherDatabaseManager.openDatabase(databaseDirectory);
        try {
            PreparedStatement pst = databaseConnection.prepareStatement(sql);
            pst.setLong(1, weather.getTs().toEpochMilli());
            pst.setDouble(2, weather.getLat());
            pst.setDouble(3, weather.getLon());
            pst.setDouble(4, weather.getTemp());
            pst.setDouble(5, weather.getPressure());
            pst.setDouble(6, weather.getHumidity());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        weatherDatabaseManager.closeDatabase(databaseConnection);
    }

    @Override
    public void databaseGet(String databaseDirectory) {
        String sql = "SELECT ts,lat,lon,temperature,pressure,humidity FROM weather";
        WeatherDatabaseManager weatherDatabaseManager = new WeatherDatabaseManager();
        Connection databaseConnection = weatherDatabaseManager.openDatabase(databaseDirectory);
        try (databaseConnection;
             Statement statement = databaseConnection.createStatement();
             ResultSet queryResult = statement.executeQuery(sql)) {
            while (queryResult.next()) {
                System.out.println(
                        queryResult.getLong("ts") + "\t" +
                                queryResult.getDouble("lat") + "\t" +
                                queryResult.getDouble("lon") + "\t" +
                                queryResult.getDouble("temperature") + "\t" +
                                queryResult.getDouble("pressure") + "\t" +
                                queryResult.getDouble("humidity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        weatherDatabaseManager.closeDatabase(databaseConnection);
    }
}


