import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicSender implements Publisher {
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public void sendTopicEvent(String event) throws JMSException {
        Connection connection = startConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("sensor.Weather");
        MessageProducer messageProducer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage(event);
        messageProducer.send(textMessage);
        System.out.println("sensor.Weather printing@@ = " + textMessage.getText());
        messageProducer.close();
        session.close();
        connection.close();
    }

    private Connection startConnection() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        return connection;
    }
}
