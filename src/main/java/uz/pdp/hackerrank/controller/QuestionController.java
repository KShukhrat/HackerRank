package uz.pdp.hackerrank.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.service.question.QuestionService;

import java.util.List;

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
        return null;
    }
}
