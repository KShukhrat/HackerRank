package uz.pdp.hackerrank.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hackerrank.entity.dto.QuestionCreateDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.exception.RequestValidationException;
import uz.pdp.hackerrank.service.question.QuestionService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/get-all")
    public ResponseEntity<List<QuestionEntity>> getAll(
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.status(200).body(questionService.getAll(page, size));
    }
    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<QuestionEntity> addQuestion(
            Principal principal,
           @Valid @RequestBody QuestionCreateDto questionCreateDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            throw new RequestValidationException(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(questionService.save(questionCreateDto, principal));
    }
    @PutMapping("/edit")
    @PreAuthorize(value ="hasRole('ADMIN')")
    public ResponseEntity<QuestionEntity> updateQuestion(
            @RequestBody QuestionCreateDto questionCreateDto,
            @RequestParam UUID questionId
            ){
        return ResponseEntity.ok(questionService.updateQuestion(questionId,questionCreateDto));
    }
    @DeleteMapping(value = "/delete/question")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteQuestion(
            @RequestParam UUID questionId
    ){
       return ResponseEntity.ok(questionService.deleteQuestion(questionId));
    }
   @GetMapping(value = "/get/question/statistic")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Integer> getQuestionStatistic(
            @RequestParam UUID questionId
   ){
       return ResponseEntity.ok(questionService.getByQuestionId(questionId));
   }
}
