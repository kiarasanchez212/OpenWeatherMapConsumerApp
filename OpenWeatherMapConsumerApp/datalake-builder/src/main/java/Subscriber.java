import javax.jms.JMSException;

public interface Subscriber {
    void receiveTopicEvent(String directory) throws JMSException;
}