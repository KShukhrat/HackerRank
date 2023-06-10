package uz.pdp.hackerrank.entity.dto;

import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.user.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserQuestionDto {
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private QuestionEntity question;
}
