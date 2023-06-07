package uz.pdp.hackerrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hackerrank.entity.user.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
