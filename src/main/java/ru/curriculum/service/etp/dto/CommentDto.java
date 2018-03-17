package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.etp.entity.Comment;

import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Integer id;

    private String content;

    private String author;

    private Integer curatorId;

    public CommentDto(Comment comment) {
        this.id = comment.id();
        this.content = comment.content();
        this.curatorId = comment.curator().id();
        this.author = String.format(
                "%s (%s), %s",
                comment.curator().fullName(),
                comment.curator().login(),
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(comment.date())
        );
    }
}
