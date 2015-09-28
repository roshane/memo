package ro.ducati.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ducati.entity.Category;
import ro.ducati.entity.MemoItem;
import ro.ducati.repo.CategoryRepository;
import ro.ducati.repo.MemoItemRepository;
import ro.ducati.service.CoreService;

/**
 * Created by roshane on 9/21/2015.
 */
@Service(value = "coreService")
public class DefaultCoreService implements CoreService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MemoItemRepository memoItemRepository;

    @Override
    public MemoItem save(MemoItem memoItem) {
        return memoItemRepository.save(memoItem);
    }

    @Override
    public Iterable<MemoItem> find(String shortDescription) {
        return memoItemRepository.findByShortDescriptionContainingIgnoreCase(shortDescription);
    }

    @Override
    public Iterable<MemoItem> findAllMemoItems() {
         return memoItemRepository.findAll();
    }

    @Override
    public MemoItem delete(MemoItem memoItem) {
        memoItemRepository.delete(memoItem);
        return memoItem;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
