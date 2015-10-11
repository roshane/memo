package ro.ducati;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.ducati.conf.Infrastructure;

/**
 * Created by roshane on 9/21/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Infrastructure.class})
public class BaseTest {
}
