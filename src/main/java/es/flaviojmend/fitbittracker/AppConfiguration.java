package es.flaviojmend.fitbittracker;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@EnableCaching
@EnableAsync
public class AppConfiguration  extends WebMvcConfigurerAdapter {



    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

//
//    @Bean
//    @ConditionalOnProperty("server.ssl.enabled")
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat;
//    }
//
//
//     private Connector initiateHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("https");
//        connector.setSecure(true);
//        connector.setPort(8443);
//
//        return connector;
//    }

}
