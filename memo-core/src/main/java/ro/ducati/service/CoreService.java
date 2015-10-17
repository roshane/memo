package ro.ducati.service;

import org.springframework.transaction.annotation.Transactional;
import ro.ducati.entity.Category;
import ro.ducati.entity.MemoItem;

/**
 * Created by roshane on 9/21/2015.
 */
public interface CoreService {

    MemoItem save(MemoItem memoItem);

    Iterable<MemoItem> find(String shortDescription);

    Iterable<MemoItem> findAllMemoItems();

    MemoItem delete(MemoItem memoItem);

    Category delete(Category category);

    Category save(Category category);

    Iterable<Category> findAllCategories();

}
