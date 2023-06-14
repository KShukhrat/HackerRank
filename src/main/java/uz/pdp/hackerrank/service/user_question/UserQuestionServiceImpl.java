package uz.pdp.hackerrank.service.user_question;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.hackerrank.entity.UserQuestion;
import uz.pdp.hackerrank.entity.dto.UserQuestionDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.question.QuestionType;
import uz.pdp.hackerrank.entity.user.UserEntity;
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
            if (question.getQuestionType().equals(QuestionType.EASY)) {
                user.setUserScore(user.getUserScore() + 3);
            } else if (question.getQuestionType().equals(QuestionType.MEDIUM)) {
                user.setUserScore(user.getUserScore() + 5);
            } else {
                user.setUserScore(user.getUserScore() + 10);
            }
            user.setUserPoints(user.getUserPoints() + 1);
            userRepository.save(user);
            UserQuestionDto userQuestionDto = new UserQuestionDto(user, question);
            UserQuestion userQuestion = modelMapper.map(userQuestionDto, UserQuestion.class);
            return userQuestionRepository.save(userQuestion);
        }
        return null;
    }

    @Override
    public List<UserQuestion> getUserQuestions(UserEntity userEntity) {
        return userQuestionRepository.findUserQuestionsByUser(userEntity);
    }

    @Override
    public List<UserQuestion> getByQuestionId(UUID questionId) {
        return userQuestionRepository.findUserQuestionsByQuestionId(questionId);
    }
}
