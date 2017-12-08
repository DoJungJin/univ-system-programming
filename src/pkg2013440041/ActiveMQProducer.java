/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013440041;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author djjproject
 */

// 해당 코드는 조교님 코드를 가져 온 것


public class ActiveMQProducer {

    private Connection connection;
    
    public ActiveMQProducer(String address) {
        init(address);        
    }

    private void init(String address) {
        try {
            // Create an ActiveMQConnectionFactory to use JMS
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(address);
            
            // Create a Connection
            this.connection = connectionFactory.createConnection();
            connection.start();    
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessageTo(String text, String destinationName) {
        
        try {            
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(destinationName);
            // 보낼 때 1:1 을 구현
            
            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            // Create a messages
            TextMessage message = session.createTextMessage(text);
            
            // Tell the producer to send the message
            System.out.println("Sent message: "+ text);
            producer.send(message);
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessageToMany(String text, String destinationName) {
        
        try {            
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination (Topic or Queue)
            Destination destination = session.createTopic(destinationName);
            // 보낼 때 1:다 를 구현하기 위해서 createTopic 으로 메소드 변경
            
            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            // Create a messages
            TextMessage message = session.createTextMessage(text);
            
            // Tell the producer to send the message
            System.out.println("Sent message: "+ text);
            producer.send(message);
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQProducer.class.getName()).log(Level.SEVERE, null, ex);
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
