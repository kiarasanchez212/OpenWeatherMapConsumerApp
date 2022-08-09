import java.sql.*;
import java.util.ArrayList;

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
    public ArrayList<ArrayList<String>> databaseGet(String databaseDirectory) {
        String sql = "SELECT ts,lat,lon,temperature,pressure,humidity FROM weather";
        WeatherDatabaseManager weatherDatabaseManager = new WeatherDatabaseManager();
        Connection databaseConnection = weatherDatabaseManager.openDatabase(databaseDirectory);
        ArrayList<ArrayList<String>> databaseContentList = new ArrayList<>();
        try (databaseConnection;
             Statement statement = databaseConnection.createStatement();
             ResultSet queryResult = statement.executeQuery(sql)) {
            while (queryResult.next()) {
                ArrayList<String> rowContentList = new ArrayList<>();
                rowContentList.add(Long.toString(queryResult.getLong("ts")));
                rowContentList.add(Double.toString(queryResult.getDouble("lat")));
                rowContentList.add(Double.toString(queryResult.getDouble("lon")));
                rowContentList.add(Double.toString(queryResult.getDouble("temperature")));
                rowContentList.add(Double.toString(queryResult.getDouble("pressure")));
                rowContentList.add(Double.toString(queryResult.getDouble("humidity")));
                databaseContentList.add(rowContentList);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        weatherDatabaseManager.closeDatabase(databaseConnection);
        return databaseContentList;
    }
}


