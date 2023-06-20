package uz.pdp.hackerrank.service.user_question;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.hackerrank.entity.userQuestion.UserQuestion;
import uz.pdp.hackerrank.entity.dto.UserQuestionDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.question.QuestionType;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.entity.userQuestion.UserQuestionStatus;
import uz.pdp.hackerrank.exception.DataNotFoundException;
import uz.pdp.hackerrank.repository.QuestionRepository;
import uz.pdp.hackerrank.repository.UserQuestionRepository;
import uz.pdp.hackerrank.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserQuestionServiceImpl implements UserQuestionService{
    private final UserQuestionRepository userQuestionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    @Override
    public UserQuestion add(UUID userId,UUID questionId,String answer) {
        UserEntity user=userRepository.findUserEntityById(userId);
        QuestionEntity question=questionRepository.findQuestionEntityById(questionId);
        if(question.getAnswer().equals(answer)) {
            user.setUserScore(user.getUserScore()+question.getScore());
            user.setUserPoints(user.getUserPoints() + question.getPoint());
            userRepository.save(user);
            UserQuestionDto userQuestionDto = new UserQuestionDto(user, question);
            UserQuestion userQuestion = modelMapper.map(userQuestionDto, UserQuestion.class);
            userQuestion.setStatus(UserQuestionStatus.DONE);
            return userQuestionRepository.save(userQuestion);
        }
        UserQuestionDto userQuestionDto = new UserQuestionDto(user, question);
        UserQuestion userQuestion = modelMapper.map(userQuestionDto, UserQuestion.class);
        userQuestion.setStatus(UserQuestionStatus.INPROGRESS);
        return userQuestionRepository.save(userQuestion);
    }


    @Override
    public List<UserQuestion> getUserQuestions(UserEntity userEntity) {
        return userQuestionRepository.findUserQuestionsByUser(userEntity);
    }

    @Override
    public List<UserQuestion> getUserQuestions(UUID id) {
        return userQuestionRepository.findUserQuestionByUserId(id);
    }

    @Override
    public Boolean remove(UUID userQuestionId) {
        UserQuestion question = userQuestionRepository.findById(userQuestionId).orElseThrow(
                () -> new DataNotFoundException("User question not found for remove"));
        if(question!=null){
            userQuestionRepository.deleteById(userQuestionId);
        return true;
        }
        return false;
    }

    @Override
    public UserQuestion getuqById(UUID id) {
        return userQuestionRepository.findById(id).orElseThrow(
                ()->new DataNotFoundException("Not found with id")
        );
    }


}
