package uz.pdp.hackerrank.service.question;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.hackerrank.entity.UserQuestion;
import uz.pdp.hackerrank.entity.dto.QuestionCreateDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.question.QuestionTheme;
import uz.pdp.hackerrank.entity.question.QuestionType;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.exception.DataNotFoundException;
import uz.pdp.hackerrank.exception.RequestValidationException;
import uz.pdp.hackerrank.repository.QuestionRepository;
import uz.pdp.hackerrank.repository.UserRepository;
import uz.pdp.hackerrank.service.user.UserService;
import uz.pdp.hackerrank.service.user_question.UserQuestionService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserQuestionService userQuestionService;

    @Override
    public QuestionEntity save(QuestionCreateDto questionCreateDto, Principal principal) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(principal.getName())
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
        UserEntity user = userService.getById(userId);
        List<UserQuestion> userQuestions = userQuestionService.getUserQuestions(user);
        if(userQuestions.isEmpty()){
          return null;
        }
        List<QuestionEntity> questions=new ArrayList<>();
        for (UserQuestion userQuestion : userQuestions) {
            questions.add(questionRepository.findQuestionEntityById(userQuestion.getQuestion().getId()));
        }
        return questions;
    }

    public List<QuestionEntity> findByThemeAndType(QuestionTheme theme, QuestionType type){
        return questionRepository.findQuestionEntitiesByThemeAndQuestionType(theme, type);
    }

}
