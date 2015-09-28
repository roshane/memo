package ro.ducati.repo;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ducati.BaseTest;
import ro.ducati.entity.Category;

import static org.junit.Assert.assertTrue;

/**
 * Created by Roshane on 9/17/2015.
 */
public class CategoryRepositoryTestTest extends BaseTest {


    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryTestTest.class);

    @Autowired
    CategoryRepository categoryRepository;

    final String java = "Java";

    @Before
    public void setUp() throws Exception {
        final Category category = new Category(java);
        categoryRepository.save(category);

    }

    @Test
    public void testAddCategory() throws Exception {
        final Category category = categoryRepository.findOne("Java");
        logger.debug("category [{}]",category);
        assertTrue(category.getLabel().equals(java));
    }
}