package demo;

import org.eclipse.jetty.servlets.PushCacheFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public FilterRegistrationBean pushCacheFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new PushCacheFilter());
        return registration;
    }
}
