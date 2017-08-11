package cn.howardliu.spring.boot.controller;

import com.wfj.exception.client.handler.MyExceptionHandler;
import com.wfj.exception.client.util.PropertiesLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 17-3-13
 *
 * @author liuxh
 * @since 1.0.0
 */
@RestController
//@EnableAutoConfiguration
public class HelloWorld {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(HelloWorld.class);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/test")
    public String test(String id) {
        if ("error".equals(id)) {
            throw new RuntimeException("throw an Exception");
        } else if ("".equals(id)) {
            throw new IllegalArgumentException("wrong arguments");
        }
        return "ID";
    }

    @RequestMapping("exception")
    public void exception() {
        PropertiesLoad.putProperties("exception.brokerList", "10.6.100.4:9092,10.6.100.5:9092,10.6.100.6:9092");
        PropertiesLoad.putProperties("exception.topic", "exception-test");
        PropertiesLoad.putProperties("exception.sysCode", "95");

        new MyExceptionHandler("004", "e", "004", "e", new RuntimeException("test"));
    }
}
