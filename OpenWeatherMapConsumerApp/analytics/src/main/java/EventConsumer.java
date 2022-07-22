public interface EventConsumer {
    void fillDatabaseTable(String fullDirectory, String textReceived) throws Exception;
}
