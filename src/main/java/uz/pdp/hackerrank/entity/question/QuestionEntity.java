package uz.pdp.hackerrank.entity.question;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.hackerrank.entity.BaseModel;

import java.util.UUID;

@Entity(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionEntity extends BaseModel {

    private String title;
    private QuestionTheme theme;
    private QuestionType questionType;

}
