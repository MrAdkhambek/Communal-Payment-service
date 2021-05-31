package r2.llc.communal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import r2.llc.communal.model.entity.RegionEntity;


public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
}
