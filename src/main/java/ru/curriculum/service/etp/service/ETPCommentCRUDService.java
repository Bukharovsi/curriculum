package ru.curriculum.service.etp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.curriculum.application.auth.principal.AuthenticationFacade;
import ru.curriculum.application.auth.principal.CuratorPrincipal;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.etp.entity.Comment;
import ru.curriculum.domain.etp.repository.ETPCommentRepository;
import ru.curriculum.service.etp.dto.CommentDto;

import javax.transaction.Transactional;


@Component
public class ETPCommentCRUDService {
    @Autowired
    private ETPCommentRepository etpCommentRepository;
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private CuratorRepository curatorRepository;

    public void create(Integer etpId, CommentDto commentDto) {
        Authentication authentication = authenticationFacade.getAuthentication();
        CuratorPrincipal principal = (CuratorPrincipal) authentication.getPrincipal();
        Curator curator = curatorRepository.findOne(principal.curator().id());
        Comment comment = new Comment(
                commentDto.getContent(),
                curator,
                etpId
        );
        etpCommentRepository.save(comment);
    }

    @Transactional
    public void deleteEtpComment(Integer id) {
        etpCommentRepository.delete(id);
    }
}
