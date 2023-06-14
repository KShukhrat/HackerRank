package uz.pdp.hackerrank.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.question.QuestionTheme;
import uz.pdp.hackerrank.entity.question.QuestionType;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository  extends JpaRepository<QuestionEntity, UUID>{

    List<QuestionEntity> findQuestionEntitiesByThemeAndQuestionType(QuestionTheme theme, QuestionType questionType);
    QuestionEntity findQuestionEntityById(UUID id);
}
