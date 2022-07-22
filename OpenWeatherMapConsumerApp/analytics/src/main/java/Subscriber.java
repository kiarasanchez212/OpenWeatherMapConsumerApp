import javax.jms.JMSException;

public interface Subscriber {
    void receiveTopicEvent(String rootDirectory, String fullDirectory) throws JMSException;
}
