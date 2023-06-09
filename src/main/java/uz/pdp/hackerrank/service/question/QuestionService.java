package uz.pdp.hackerrank.service.question;

import uz.pdp.hackerrank.entity.dto.QuestionCreateDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;

import java.security.Principal;
import java.util.List;

public interface QuestionService{

    QuestionEntity save(QuestionCreateDto questionCreateDto, Principal principal);

    List<QuestionEntity> getAll(int page, int size);



}
