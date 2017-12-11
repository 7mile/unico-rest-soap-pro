package au.com.unico.message.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by xiaofei on 2017/12/9.
 */
@Component
public class JMSConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private JmsTemplate jmsTemplate;
    private Queue queue;

    @Autowired
    public JMSConsumer(JmsTemplate jmsTemplate, Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    public Object getMessage() {
        jmsTemplate.setReceiveTimeout(500L);
        Object msgObject = jmsTemplate.receiveAndConvert(queue);
        if (logger.isDebugEnabled()) {
            logger.debug("getMessage: " + msgObject);
        }
        return msgObject;
    }
}
