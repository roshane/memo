package ro.ducati.repo;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ducati.BaseTest;
import ro.ducati.entity.MemoItem;

import java.time.LocalDate;


public class MemoItemRepositoryTestTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(MemoItemRepositoryTestTest.class);

    @Autowired
    private MemoItemRepository memoItemRepository;

    @Before
    public void setUp() throws Exception {
        final MemoItem memoItem = new MemoItem("Java", "Simple Test", LocalDate.now(), LocalDate.now(), "Sample content");
        memoItemRepository.save(memoItem);
    }

    @Test
    public void testFindAllMemoItems() throws Exception {
        setUp();
        System.out.println("##########################################################");
        memoItemRepository.findAll().forEach(item->logger.debug("item : [{}]",item));
        System.out.println("##########################################################");
    }

    @Test
    public void testFindByDescription() throws Exception {
        Iterable<MemoItem> items = memoItemRepository.findByShortDescriptionContainingIgnoreCase("SimPle");
        logger.debug("item[{}]",items);
    }

    @Test
    public void testDeleteAllMemoItems() throws Exception {
        memoItemRepository.deleteAll();
    }
}