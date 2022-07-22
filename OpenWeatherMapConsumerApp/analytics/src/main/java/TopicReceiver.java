import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicReceiver implements Subscriber {
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public void receiveTopicEvent(String rootDirectory, String fullDirectory) throws JMSException {
        Connection connection = startConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("sensor.Weather");
        MessageConsumer consumer = session.createDurableSubscriber(topic, "analytics");
        consumer.setMessageListener(message -> {
            WeatherEventConsumer weatherConsumer = new WeatherEventConsumer();
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Received message" + textMessage.getText() + "'");
                weatherConsumer.fillDatabaseTable(fullDirectory, textMessage.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private Connection startConnection() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("example");
        connection.start();
        return connection;
    }
}

