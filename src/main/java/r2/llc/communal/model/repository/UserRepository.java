package r2.llc.communal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import r2.llc.communal.model.entity.UserEntity;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByToken(String token);
}
