package uz.pdp.hackerrank.entity.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import uz.pdp.hackerrank.entity.question.QuestionTheme;
import uz.pdp.hackerrank.entity.question.QuestionType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionCreateDto {
    @NotBlank(message = "title cannot be blank or empty")
    @NotNull(message = "title cannot be null")
    private String title;
    @NotBlank(message = "answer cannot be blank or empty")
    @NotNull(message = "answer cannot be null")
    private String answer;
    private QuestionType type;
    private QuestionTheme theme;

}
