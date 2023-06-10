package uz.pdp.hackerrank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.hackerrank.entity.question.QuestionEntity;
import uz.pdp.hackerrank.entity.user.UserEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserQuestion extends BaseModel{
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private QuestionEntity question;
}
