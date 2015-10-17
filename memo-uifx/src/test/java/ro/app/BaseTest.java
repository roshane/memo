package ro.app;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.core.MemoApplicationContext;

/**
 * @author roshanep@hsenidmobile.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MemoApplicationContext.class})
public abstract class BaseTest {
}
