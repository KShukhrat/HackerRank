package uz.pdp.hackerrank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hackerrank.entity.UserQuestion;
import uz.pdp.hackerrank.entity.dto.UserQuestionDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.exception.DataNotFoundException;
import uz.pdp.hackerrank.service.question.QuestionService;
import uz.pdp.hackerrank.service.user_question.UserQuestionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/user/question")
@RequiredArgsConstructor
public class UserQuestionController {
    private final UserQuestionService userQuestionS;
    private final QuestionService questionService;
    @PostMapping("/add")
    public ResponseEntity<UserQuestion> add(
            @RequestParam UUID userId,
            @RequestParam UUID questionId,
            @RequestParam String answer
            ){
        UserQuestion userQuestion=userQuestionS.add(userId,questionId,answer);
        if(userQuestion==null){
            throw new DataNotFoundException("Answer is not true");
        }
        return ResponseEntity.ok(userQuestion);
    }
    @GetMapping("/get/user/questions")
    public ResponseEntity<List<QuestionEntity>> getUserQuestions(
            @RequestParam UUID userId
            ){
        List<QuestionEntity> questions=questionService.getUserQuestions(userId);
        if(questions==null){
            throw new DataNotFoundException("Question not found");
        }
        return ResponseEntity.ok(questions);
    }
}
