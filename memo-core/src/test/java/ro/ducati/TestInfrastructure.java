package ro.ducati;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ro.ducati.conf.Infrastructure;
import ro.ducati.service.CoreService;
import ro.ducati.service.impl.DefaultCoreService;

/**
 * @author roshanep@hsenidmobile.com
 */
@Configuration
@Import(Infrastructure.class)
public class TestInfrastructure {

    @Bean
    public CoreService coreService() {
        return new DefaultCoreService();
    }
}
