package uz.pdp.hackerrank.service.question;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.hackerrank.entity.userQuestion.UserQuestion;
import uz.pdp.hackerrank.entity.dto.QuestionCreateDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.question.QuestionTheme;
import uz.pdp.hackerrank.entity.question.QuestionType;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.exception.DataNotFoundException;
import uz.pdp.hackerrank.repository.QuestionRepository;
import uz.pdp.hackerrank.repository.UserQuestionRepository;
import uz.pdp.hackerrank.repository.UserRepository;
import uz.pdp.hackerrank.service.user.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final UserQuestionRepository userQuestionRepository;

    @Override
    public QuestionEntity save(QuestionCreateDto questionCreateDto, Principal principal) {
        UserEntity  userEntity = userRepository.findUserEntityByUsername(principal.getName())
                .orElseThrow(() -> new DataNotFoundException("USER NOT FOUND"));
        QuestionEntity book = modelMapper.map(questionCreateDto, QuestionEntity.class);
        return questionRepository.save(book);
    }

    @Override
    public List<QuestionEntity> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.findAll(pageable).getContent();
    }

    @Override
    public QuestionEntity updateQuestion(UUID questionId, QuestionCreateDto questionCreateDto) {
        QuestionEntity question=modelMapper.map(questionCreateDto,QuestionEntity.class);
        question.setId(questionId);
        return questionRepository.save(question);
    }

    @Override
    public Boolean deleteQuestion(UUID questionId) {
        questionRepository.deleteById(questionId);
        return true;
    }

    @Override
    public List<QuestionEntity> getUserQuestions(UUID userId) {
        List<UserQuestion> userQuestions = userQuestionRepository.findUserQuestionsByUserId(userId, Sort.by(Sort.Direction.DESC, "status"));
        if(userQuestions.isEmpty()){
          return null;
        }
        List<QuestionEntity> questions=new ArrayList<>();
        for (UserQuestion userQuestion : userQuestions) {
            questions.add(questionRepository.findQuestionEntityById(userQuestion.getQuestion().getId()));
        }
        return questions;
    }

    @Override

    public Integer getByQuestionId(UUID questionId) {
     List<UserQuestion> userQuestions=userQuestionRepository.findUserQuestionsByQuestionId(questionId);
     return userQuestions.size();
    }

    public Optional<QuestionEntity> getById(UUID id) {
        return questionRepository.findById(id);

    }

    public List<QuestionEntity> findByThemeAndType(QuestionTheme theme, QuestionType type){
        return questionRepository.findQuestionEntitiesByThemeAndQuestionType(theme, type);
    }

}
