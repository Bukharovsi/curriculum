package ru.curriculum.domain.etp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.curriculum.domain.etp.entity.Comment;

import java.util.List;

public interface ETPCommentRepository extends PagingAndSortingRepository<Comment, Integer> {

    List<Comment> findAllByEtpId(int etpId);
}
