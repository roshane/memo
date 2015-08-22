package com.ducati.repository;

import com.ducati.entity.MemoItem;
import com.ducati.entity.data.MemoKeyword;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Roshane on 8/22/2015.
 */
public interface MemoItemRepository extends Repository<MemoItem, String> {

    List<MemoItem> findAll();

    List<MemoItem> findByMemoKeyword(MemoKeyword memoKeyword);
}
