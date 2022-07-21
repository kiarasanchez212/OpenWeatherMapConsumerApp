import javax.jms.JMSException;

public class DatalakeBuilder {
    public static void main(String[] argv) throws JMSException {
        String rootDirectory =  argv[0];
        DirectoryCreator directoryCreator = new DirectoryCreator();
        String directory = directoryCreator.createNewDirectoryFromRootDirectory(rootDirectory);
        TopicReceiver topicReceiver = new TopicReceiver();
        topicReceiver.receiveTopicEvent(directory);
    }
}