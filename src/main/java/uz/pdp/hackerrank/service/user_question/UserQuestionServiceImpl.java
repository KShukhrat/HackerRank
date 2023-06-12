package uz.pdp.hackerrank.service.user_question;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.hackerrank.entity.UserQuestion;
import uz.pdp.hackerrank.entity.dto.UserQuestionDto;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.repository.UserQuestionRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserQuestionServiceImpl implements UserQuestionService{
    private final UserQuestionRepository userQuestionRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserQuestion add(UserQuestionDto userQuestionDto) {
        UserQuestion userQuestion=modelMapper.map(userQuestionDto,UserQuestion.class);
       return userQuestionRepository.save(userQuestion);
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
