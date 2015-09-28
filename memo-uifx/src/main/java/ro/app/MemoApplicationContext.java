package ro.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.ducati.repo.CategoryRepository;
import ro.ducati.repo.MemoItemRepository;
import ro.ducati.service.CoreService;
import ro.ducati.service.impl.DefaultCoreService;

/**
 * @author roshanep@hsenidmobile.com
 */
public class MemoApplicationContext {

    private MemoItemRepository memoItemRepository;
    private CategoryRepository categoryRepository;
    private CoreService coreService;

    private ApplicationContext applicationContext;

    {
        applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        memoItemRepository = applicationContext.getBean("memoItemRepository", MemoItemRepository.class);
        categoryRepository = applicationContext.getBean("categoryRepository", CategoryRepository.class);
        coreService = applicationContext.getBean("coreService", DefaultCoreService.class);
    }
}
