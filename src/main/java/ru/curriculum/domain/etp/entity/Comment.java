package ru.curriculum.domain.etp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.admin.curator.entity.Curator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "etp_comment")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @Setter
    @ManyToOne(targetEntity = Curator.class)
    private Curator curator;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "etp_id")
    @Setter
    private ETP etp;
}
