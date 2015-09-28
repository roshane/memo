package ro.ducati.repo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ducati.BaseTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Roshane on 9/11/2015.
 */
public class ScratchTests extends BaseTest {

    @Autowired
    MemoItemRepository memoItemRepository;

    private final Logger logger= LoggerFactory.getLogger(ScratchTests.class);

    @Test
    public void testDate() throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final Date parse = dateFormat.parse("12/05/2015");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        memoItemRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testName() throws Exception {
        System.out.println();

    }
}
