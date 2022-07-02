import javax.jms.JMSException;

public interface Publisher {
    void sendTopicEvent(String string) throws JMSException;
}
