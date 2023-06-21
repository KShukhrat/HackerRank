package uz.pdp.hackerrank.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hackerrank.entity.userQuestion.UserQuestion;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserQuestionRepository extends JpaRepository<UserQuestion, UUID> {
  List<UserQuestion> findUserQuestionsByUserId(UUID userId, Sort status);
  List<UserQuestion> findUserQuestionsByQuestionId(UUID questionId, Sort sort);
  List<UserQuestion> findUserQuestionsByQuestionId(UUID questionId);
  List<UserQuestion> findUserQuestionByUserId(UUID userId);

}
