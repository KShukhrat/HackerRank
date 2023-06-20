package uz.pdp.hackerrank.entity.userQuestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hackerrank.entity.BaseModel;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.user.UserEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserQuestion extends BaseModel {
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private UserEntity user;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private QuestionEntity question;
    @Enumerated(EnumType.STRING)
    private UserQuestionStatus status;
}
