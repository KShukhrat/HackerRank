package uz.pdp.hackerrank.service.question;

import uz.pdp.hackerrank.entity.dto.QuestionCreateDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionService{

    QuestionEntity save(QuestionCreateDto questionCreateDto, Principal principal);
    List<QuestionEntity> getAll(int page, int size);
    QuestionEntity updateQuestion(UUID questionId, QuestionCreateDto questionCreateDto);
    Boolean deleteQuestion(UUID questionId);
    List<QuestionEntity> getUserQuestions(UUID userId);
    Integer getByQuestionId(UUID questionId);
    Optional<QuestionEntity> getById(UUID id);

}
