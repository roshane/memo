package ro.ducati.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.ducati.entity.Category;

/**
 * Created by Roshane on 9/17/2015.
 */
public interface CategoryRepository extends CrudRepository<Category,String> {
}
