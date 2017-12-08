/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013440041;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author djjproject
 */


// 해당 코드는 조교님 코드를 가져 온 것

public class ActiveMQConsumer implements ExceptionListener {

    private Connection connection;
    private Publisher publisher;


// 초기화할 때 Publisher 의 함수를 콜을 해야함으로 publisher 도 받음
    public ActiveMQConsumer(String address,Publisher publisher) {
        init(address);
        this.publisher = publisher;
    }

    @Override
    public void onException(JMSException jmse) {
        System.out.println("JMS Exception occured. SHutting down client");
    }

    private void init(String address) {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(address);

            // Create a Connection
            this.connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);
        } catch (JMSException e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    public void setConsumerDestination(String destinationName) {
        try {
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(destinationName);
            // 받는 부분을 브로드캐스팅으로 받기 위해서 Topic 으로 받음. 실제로 테스트 해 보니 큰 영향은 없음

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            MessageListener listener = new MessageListener() {
                public void onMessage(Message message) {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            System.out.println("Received Message:\n" + textMessage.getText());
                            // 메시지가 오면 텍스르르르 publisher 로 보내고 소리를 재생한다.
                            publisher.onMessage(textMessage.getText());
                            publisher.soundPlay();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            // Register to MessageListener
            consumer.setMessageListener(listener);
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setConsumerDestinationMany(String destinationName) {
        try {
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createTopic(destinationName);
            // 받는 부분을 브로드캐스팅으로 받기 위해서 Topic 으로 받음. 실제로 테스트 해 보니 큰 영향은 없음

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            MessageListener listener = new MessageListener() {
                public void onMessage(Message message) {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            System.out.println("Received Message:\n" + textMessage.getText());
                            // 메시지가 오면 텍스르르르 publisher 로 보내고 소리를 재생한다.
                            publisher.onMessage(textMessage.getText());
                            publisher.soundPlay();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            // Register to MessageListener
            consumer.setMessageListener(listener);
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
