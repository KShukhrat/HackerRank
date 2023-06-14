package uz.pdp.hackerrank.service.user_question;

import uz.pdp.hackerrank.entity.UserQuestion;
import uz.pdp.hackerrank.entity.dto.UserQuestionDto;
import uz.pdp.hackerrank.entity.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserQuestionService{
    UserQuestion add(UUID userId,UUID questionId);
    List<UserQuestion> getUserQuestions(UserEntity userEntity);
    List<UserQuestion> getByQuestionId(UUID questionId);
}
