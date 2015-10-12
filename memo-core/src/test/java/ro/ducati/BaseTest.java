package ro.ducati;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.ducati.conf.Infrastructure;
import ro.ducati.service.CoreService;
import ro.ducati.service.impl.DefaultCoreService;

/**
 * Created by roshane on 9/21/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestInfrastructure.class})
public class BaseTest {
}
