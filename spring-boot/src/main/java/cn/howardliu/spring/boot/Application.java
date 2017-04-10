package cn.howardliu.spring.boot;

import javafx.scene.Parent;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <br>created at 17-3-13
 *
 * @author liuxh
 * @since 1.0.0
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class);
//        SpringApplication app = new SpringApplication(Application.class);
//        app.setBannerMode(Banner.Mode.LOG);
//        app.run(args);
        new SpringApplicationBuilder()
                .sources(Parent.class)
                .child(Application.class)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }
}
