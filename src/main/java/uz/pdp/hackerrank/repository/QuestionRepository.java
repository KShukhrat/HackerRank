package uz.pdp.hackerrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hackerrank.entity.question.QuestionEntity;

import java.util.UUID;

@Repository
public interface QuestionRepository  extends JpaRepository<QuestionEntity, UUID>{

}
