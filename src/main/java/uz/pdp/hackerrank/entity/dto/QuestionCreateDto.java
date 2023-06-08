package uz.pdp.hackerrank.entity.dto;


import lombok.*;
import uz.pdp.hackerrank.entity.question.QuestionTheme;
import uz.pdp.hackerrank.entity.question.QuestionType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionCreateDto {
    private String title;
    private QuestionType type;
    private QuestionTheme theme;

}
