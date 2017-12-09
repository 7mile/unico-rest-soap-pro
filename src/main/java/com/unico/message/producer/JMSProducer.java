package com.unico.message.producer;

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
public class JMSProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private JmsTemplate jmsTemplate;
    private Queue queue;

    @Autowired
    public JMSProducer(JmsTemplate jmsTemplate, Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    public boolean sendMessage(String msg) {
        try {
            jmsTemplate.convertAndSend(queue, msg);
        } catch (Exception e) {
            logger.error("Failed to send msg:{} to queue, error:{}", msg, e.getMessage());
            return false;
        }
        return true;
    }
}
