package uz.pdp.hackerrank.entity.question;

import jakarta.persistence.Entity;
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
    private QuestionTheme theme;
    private QuestionType questionType;
}
