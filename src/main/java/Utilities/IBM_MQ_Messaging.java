package Utilities;

import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnection;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQQueueSender;
import com.ibm.mq.jms.MQQueueSession;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

public class IBM_MQ_Messaging {

    //To Push Given Message Into Mentioned IBM MQ
    public Boolean pushMessageIntoIBMMQ(String QueueManager,String channelName,String Host, int Port, String Queue, String Message) throws Exception {

        System.out.println("###################################################################");
        System.out.println("#######################PUSHING MESSAGE INTO MQ#####################");
        System.out.println("###################################################################");

        try {
            MQQueueConnectionFactory cf = new MQQueueConnectionFactory();

            //Setting Connection Details
            cf.setHostName(Host);
            cf.setPort(Port);
            cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE,WMQConstants.WMQ_CM_CLIENT);
            cf.setQueueManager(QueueManager);
            cf.setChannel(channelName);

            MQQueueConnection connection = (MQQueueConnection) cf.createQueueConnection();
            MQQueueSession session = (MQQueueSession) connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            MQQueue queue = (MQQueue) session.createQueue(Queue);
            MQQueueSender sender = (MQQueueSender) session.createSender(queue);

            TextMessage message = (TextMessage) session.createTextMessage(Message);

            // Start the connection
            connection.start();
            sender.send(message);

            System.out.println("Message: "+ message.getText());
            sender.close();
            session.close();
            connection.close();
            System.out.println("Message is Successfully Placed Into MQ: " + Queue);
        } catch (JMSException jmsex) {
            System.out.println("Unable to PlaceMessage Into MQ: " + Queue);
            jmsex.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Unable to PlaceMessage Into MQ: " + Queue);
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
