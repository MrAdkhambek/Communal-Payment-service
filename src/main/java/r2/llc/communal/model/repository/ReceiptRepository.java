package r2.llc.communal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import r2.llc.communal.model.entity.ReceiptEntity;


public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {
}
