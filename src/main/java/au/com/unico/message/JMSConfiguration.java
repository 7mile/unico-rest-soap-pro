package au.com.unico.message;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * Created by xiaofei on 2017/12/9.
 */
@Configuration
public class JMSConfiguration {
    public static final String QUEUE_NAME = "user-queue-1";

    @Bean
    public Queue getQueue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }
}
