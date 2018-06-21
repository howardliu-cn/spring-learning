package cn.howardliu.sl.sb2.swagger.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;

/**
 * <br>created at 18-6-21
 *
 * @author liuxh
 * @since 0.1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket api() {
        Contact contact = new Contact("Howard Liu", "http://www.howardliu.cn", "howardliu1988@163.com");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("spring-boot-2-with-swagger")
                .description("Description for spring-boot-2-with-swagger")
                .version("1.0.0")
                .termsOfServiceUrl("urn:tos")
                .contact(contact)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
        HashSet<String> producesAndConsumes = new HashSet<>(Collections.singletonList("application/json"));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .produces(producesAndConsumes)
                .consumes(producesAndConsumes)
                .globalResponseMessage(RequestMethod.GET, Collections.emptyList())
                .globalResponseMessage(RequestMethod.POST, Collections.emptyList())
                .globalOperationParameters(Collections.emptyList());
    }
}
