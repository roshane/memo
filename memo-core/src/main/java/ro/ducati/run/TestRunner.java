package ro.ducati.run;

import ro.ducati.repo.MemoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Roshane on 8/23/2015.
 */
public class TestRunner {

    private static final Logger LOGGER= LoggerFactory.getLogger(TestRunner.class);

    @Autowired
    private MemoItemRepository memoItemRepository;

    public static void main(String[] args) {
        LOGGER.debug("Running Main class");
        final TestRunner runner = new TestRunner();
        LOGGER.debug("UserRepostory [{}]",runner.memoItemRepository.toString());
    }

}
