package r2.llc.communal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import r2.llc.communal.model.entity.CategoryEntity;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
