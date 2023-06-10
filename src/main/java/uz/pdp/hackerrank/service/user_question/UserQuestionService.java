package uz.pdp.hackerrank.service.user_question;

import uz.pdp.hackerrank.entity.UserQuestion;
import uz.pdp.hackerrank.entity.dto.UserQuestionDto;
import uz.pdp.hackerrank.entity.user.UserEntity;

import java.util.List;

public interface UserQuestionService{
    UserQuestion add(UserQuestionDto userQuestionDto);
    List<UserQuestion> getUserQuestions(UserEntity userEntity);
}
