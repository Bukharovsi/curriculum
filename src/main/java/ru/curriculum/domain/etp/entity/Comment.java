package ru.curriculum.domain.etp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import ru.curriculum.domain.admin.curator.entity.Curator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "etp_comment")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @Setter
    @ManyToOne(targetEntity = Curator.class)
    private Curator curator;

    @CreationTimestamp
    private Date date;

    private Integer etpId;

    public Comment(String content, Curator curator, Integer etpId) {
        this.content = content;
        this.curator = curator;
        this.etpId = etpId;
    }
}
