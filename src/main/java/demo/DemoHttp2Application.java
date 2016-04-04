package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
public class DemoHttp2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoHttp2Application.class, args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer jettyHttp2Customizer(ServerProperties serverProperties) {
    	return new JettyHttp2Customizer(serverProperties);
    }
    
    @Bean
    public HelloController helloControllerBean() {
        return new HelloController();
    }
}

@RestController
class HelloController {

    @RequestMapping(path="/")
    String hello() {
        return "hello!";
    }
}