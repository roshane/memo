package ro.ducati.service;

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


    Category save(Category category);

    Iterable<Category> findAllCategories();

}
