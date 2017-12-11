package au.com.unico.controller.rest;

import au.com.unico.dao.ItemRepository;
import au.com.unico.domain.Item;
import au.com.unico.message.producer.JMSProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaofei on 2017/12/9.
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static final String PUSH_STATE_SUCCESSFUL = "Successful";
    public static final String PUSH_STATE_FAILED = "Failed";

    @Autowired
    private JMSProducer jmsProducer;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * This method provides push (POST) RESTful service which save the 2 integers in DB and JMS Queue
     * Access using curl: curl -d "i1=10&i2=20" http://localhost:8080/item/push
     * @return String returns status of the request
     */
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public String push(@RequestParam("i1") int i1, @RequestParam("i2") int i2) {
        if (logger.isDebugEnabled()) {
            logger.debug("User input i1:{} i2:{}.", i1, i2);
        }

        if (jmsProducer.sendMessage(String.valueOf(i1)) && jmsProducer.sendMessage(String.valueOf(i2))) {
            if (logger.isDebugEnabled()) {
                logger.debug("Push input i1:{} i2:{} to jms queue successfully.", i1, i2);
            }
            Item item1 = new Item(i1);
            Item item2 = new Item(i2);
            try {
                itemRepository.save(item1);
                itemRepository.save(item2);
            } catch (Exception e) {
                logger.error("Error when saving items {} {} to DB. Error msg:{}", i1, i2, e.getMessage());
                return PUSH_STATE_FAILED;
            }
            return PUSH_STATE_SUCCESSFUL;
        }
        return PUSH_STATE_FAILED;
    }

    /**
     * This method provides list (GET) RESTful service which list
     * all of the items. Access using curl: curl http://localhost:8080/item/all
     * @return List<Integer> returns all the items in json format
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Integer> list() {
        if (logger.isDebugEnabled()) {
            logger.debug("User get all integers pushed from database.");
        }
        return ((List<Item>) itemRepository.findAll())
                .stream().map((item) -> item.getNumber()).collect(Collectors.toList());
    }
}
