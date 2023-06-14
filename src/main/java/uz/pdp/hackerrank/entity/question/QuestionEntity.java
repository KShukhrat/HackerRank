package uz.pdp.hackerrank.entity.question;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.pdp.hackerrank.entity.BaseModel;

@Entity(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionEntity extends BaseModel {

    private String title;
    private String answer;
    @Enumerated(EnumType.STRING)
    private QuestionTheme theme;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    private int score=5;
    private int point=1;
}
