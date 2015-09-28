package ro.ducati.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ducati.BaseTest;
import ro.ducati.entity.Category;
import ro.ducati.entity.MemoItem;

import java.time.LocalDate;

/**
 * Created by roshane on 9/21/2015.
 */

public class DefaultCoreServiceTestTest extends BaseTest {

    @Autowired(required = true)
    private CoreService coreService;

    private static final Logger logger = LoggerFactory.getLogger(DefaultCoreServiceTestTest.class);

    public void testSave() throws Exception {

    }

    @Before
    public void feedData() throws Exception {
        final MemoItem memoItem = new MemoItem("Java", "Simple Test", LocalDate.now(), LocalDate.now(), "Sample content");
        Category category = new Category("java");
        coreService.save(category);
        coreService.save(memoItem);
    }

    @Test
    public void testFind() throws Exception {
        Iterable<MemoItem> memoItem = coreService.find("imple");
        logger.debug("find memo-item by short-description [{}]", memoItem);
        Assert.assertNotNull(memoItem);
    }

    @Test
    public void testFindAllMemoItems() throws Exception {
        Iterable<MemoItem> allMemoItems = coreService.findAllMemoItems();
        allMemoItems.forEach(item -> logger.debug("find all memo-items [{}]", item));
        Assert.assertFalse(allMemoItems.iterator().next().equals(null));
    }

    @Test
    public void testFindAllCategories() throws Exception {
        Iterable<Category> allCategories = coreService.findAllCategories();
        allCategories.forEach(item->logger.debug("find all category-teims [{}]",item));
        Assert.assertFalse(allCategories.iterator().next().equals(null));
    }

    //    @Test
    public void testRemoveMemo() throws Exception {
        final MemoItem memoItem = new MemoItem("Java", "Simple Test", LocalDate.now(), LocalDate.now(), "Sample content");
        memoItem.setId(1L);
        MemoItem delete = coreService.delete(memoItem);
        logger.debug("delete memo-item [{}]", delete);

    }

    @Test
    public void testDeleteAllMemos() throws Exception {

    }
}