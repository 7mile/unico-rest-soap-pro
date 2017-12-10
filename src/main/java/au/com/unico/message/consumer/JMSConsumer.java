package au.com.unico.message.consumer;

import au.com.unico.dao.ItemRepository;
import au.com.unico.domain.Item;
import au.com.unico.message.JMSConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by xiaofei on 2017/12/9.
 */
@Component
public class JMSConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private JmsTemplate jmsTemplate;
    private ItemRepository itemRepository;

    @Autowired
    public JMSConsumer(JmsTemplate jmsTemplate, ItemRepository itemRepository) {
        this.jmsTemplate = jmsTemplate;
        this.itemRepository = itemRepository;
    }

    @JmsListener(destination = JMSConfiguration.QUEUE_NAME)
    public void processMessage(String content) {
        logger.debug("Received message:{}", content);
        Item item = new Item();
        try {
            item.setNumber(Integer.parseInt(content));
        } catch (Exception e) {
            logger.error("Failed to convert message:{} to Integer. Can't convert to Item. Error:", content, e.getMessage());
            return;
        }

        itemRepository.save(item);
    }
}
