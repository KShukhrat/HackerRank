package uz.pdp.hackerrank.service.user_question;


import uz.pdp.hackerrank.entity.dto.UserQuestionDto;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.entity.userQuestion.UserQuestion;

import java.util.List;
import java.util.UUID;

public interface UserQuestionService{
    UserQuestion add(UUID userId,UUID questionId,String answer);
    List<UserQuestion> getUserQuestions(UserEntity userEntity);
    List<UserQuestion> getUserQuestions(UUID id);
    Boolean remove(UUID userQuestionId);
    UserQuestion getuqById(UUID id);
}
