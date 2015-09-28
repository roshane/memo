package ro.ducati.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.ducati.entity.MemoItem;


public interface MemoItemRepository extends CrudRepository<MemoItem, Long> {

    Iterable<MemoItem> findByShortDescriptionContainingIgnoreCase(String description);
}
