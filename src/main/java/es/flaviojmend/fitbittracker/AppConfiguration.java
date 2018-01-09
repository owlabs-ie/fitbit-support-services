package es.flaviojmend.fitbittracker;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan
@EnableAutoConfiguration
@PropertySource(Constants.Properties)
@Configuration
@EnableCaching
@EnableAsync
public class AppConfiguration {
}
