package au.com.unico.service.impl;

import au.com.unico.dao.GcdRepository;
import au.com.unico.domain.Gcd;
import au.com.unico.message.consumer.JMSConsumer;
import au.com.unico.service.GcdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by xiaofei on 2017/12/10.
 */
@Service
public class GcdServiceImpl implements GcdService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private GcdRepository gcdRepository;

    private JMSConsumer consumer;

    @Autowired
    public GcdServiceImpl(GcdRepository gcdRepository, JMSConsumer consumer) {
        this.gcdRepository = gcdRepository;
        this.consumer = consumer;
    }

    @Override
    public Optional<Integer> getGcd() {
        Optional<Integer[]> params = getTwoMessagesFromQueue();
        if (!params.isPresent()) {
            return Optional.empty();
        }
        Integer i1 = params.get()[0];
        Integer i2 = params.get()[1];
        Integer result = 1;
        if (i1 > i2) {
            result = gcd(i1, i2);
        } else {
            result = gcd(i2, i1);
        }
        Gcd gcd = new Gcd(i1, i2, result);
        try {
            gcd = gcdRepository.save(gcd);
            logger.info("Saved gcd id:{} to database", gcd.getId());
        } catch (Exception e) {
            logger.error("Error when saving gcd to database. Error msg:{}" + e.getMessage());
            e.printStackTrace();
        }

        logger.info("Gcd of {} and {} is {}", i1, i2, result);
        return Optional.of(result);
    }

    private synchronized Optional<Integer[]> getTwoMessagesFromQueue() {
        try {
            Integer i1 = Integer.parseInt((String) consumer.getMessage());
            Integer i2 = Integer.parseInt((String) consumer.getMessage());
            return Optional.of(new Integer[]{i1, i2});
        } catch (Exception e) {
            logger.error("Error when getting 2 messages from queue and converting to Integer. Error msg:{}", e.getMessage());
            return Optional.empty();
        }
    }

    private int gcd(Integer a, Integer b) {
        if (b == 0) {
            return a;
        }
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }
}
