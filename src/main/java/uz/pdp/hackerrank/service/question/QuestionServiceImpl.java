package uz.pdp.hackerrank.service.question;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.hackerrank.entity.dto.QuestionCreateDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.question.QuestionTheme;
import uz.pdp.hackerrank.entity.question.QuestionType;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.exception.DataNotFoundException;
import uz.pdp.hackerrank.repository.QuestionRepository;
import uz.pdp.hackerrank.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

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

    public List<QuestionEntity> findByThemeAndType(QuestionTheme theme, QuestionType type){
        return questionRepository.findQuestionEntitiesByThemeAndQuestionType(theme, type);
    }

}
