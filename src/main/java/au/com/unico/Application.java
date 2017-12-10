package au.com.unico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by xiaofei on 2017/12/9.
 */
@SpringBootApplication
@ComponentScan("au.com.unico")
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
