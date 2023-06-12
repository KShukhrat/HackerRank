package uz.pdp.hackerrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hackerrank.entity.UserQuestion;
import uz.pdp.hackerrank.entity.user.UserEntity;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserQuestionRepository extends JpaRepository<UserQuestion, UUID> {
  List<UserQuestion> findUserQuestionsByUser(UserEntity userEntity);
  List<UserQuestion> findUserQuestionsByQuestionId(UUID questionId);
}
