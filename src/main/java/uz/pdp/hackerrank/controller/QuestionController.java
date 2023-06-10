package uz.pdp.hackerrank.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hackerrank.entity.dto.QuestionCreateDto;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.service.question.QuestionService;

import java.security.Principal;
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
        return ResponseEntity.status(200).body(questionService.getAll(page, size));
    }

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<QuestionEntity> addQuestion(
            Principal principal,
            @RequestBody QuestionCreateDto questionCreateDto
    ) {
        return ResponseEntity.ok(questionService.save(questionCreateDto, principal));
    }
}
