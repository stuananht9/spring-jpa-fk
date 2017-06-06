package repositories;

import entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tuananh on 06/06/17.
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
}
