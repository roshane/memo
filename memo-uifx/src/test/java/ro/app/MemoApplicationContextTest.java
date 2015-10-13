package ro.app;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.core.MemoApplicationContext;
import ro.ducati.entity.Category;
import ro.ducati.service.CoreService;

public class MemoApplicationContextTest extends BaseTest {

    @Autowired
    CoreService coreService;

    private static final Logger LOGGER= LoggerFactory.getLogger(MemoApplicationContextTest.class);

    @Test
    public void testSample() throws Exception {
        Assert.assertNotNull(coreService);
        coreService.findAllMemoItems().forEach(System.out::println);
        coreService.findAllCategories().forEach(System.out::println);
    }

    @Test
    public void testInsertCategory() throws Exception {
//        Category category = coreService.save(new Category("maven"));
//        LOGGER.debug("saved category [{}]",category);
        printAll(coreService.findAllCategories());
    }

    private void printAll(Iterable<Category> categories){
        System.out.println("All available categories");
        categories.forEach(c -> LOGGER.debug("category [{}]", c));
    }
}