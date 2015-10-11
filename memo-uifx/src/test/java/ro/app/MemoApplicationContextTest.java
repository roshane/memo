package ro.app;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.core.MemoApplicationContext;
import ro.ducati.service.CoreService;

public class MemoApplicationContextTest extends BaseTest {

    @Autowired
    CoreService coreService;

    @Test
    public void testSample() throws Exception {
        Assert.assertNotNull(coreService);
        coreService.findAllMemoItems().forEach(System.out::println);
    }
}