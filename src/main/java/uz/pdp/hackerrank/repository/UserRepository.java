package uz.pdp.hackerrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hackerrank.entity.user.UserEntity;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
