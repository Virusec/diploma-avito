package com.example.diploma.service.impl;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.dto.ResponseWrapperComment;
import com.example.diploma.entity.CommentEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.mapping.CommentMapper;
import com.example.diploma.repository.CommentRepository;
import com.example.diploma.service.AdService;
import com.example.diploma.service.CommentService;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * @author anna
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final UserService userService;
    private final AdService adService;
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    @Override
    public ResponseWrapperComment getComments(int id) {
        List<Comment> result = new LinkedList<>();
        commentRepository.findAllByAd_Pk(id).forEach(entity -> result.add(mapper.entityToCommentDto(entity)));
        return new ResponseWrapperComment(result.size(), result);
    }

    @Override
    public Comment add(int id, CreateComment comment, String name) {
        CommentEntity entity = mapper.createCommentToEntity(comment, adService.getEntity(id), userService.getEntity(name));
        log.info("Добавление комментария к объявлению с id " + id);
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public void delete(int commentId) {
        commentRepository.deleteById(commentId);
        log.info("Удаление комментария с id " + commentId);
    }

    @Override
    public Comment update(int commentId, Comment comment, String email) {
        CommentEntity entity = getEntity(commentId);
        entity.setText(comment.getText() + "(отредактировал(а) " + userService.getEntity(email).getFirstName() +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(" dd MMMM yyyy в HH:mm:ss)")));
        log.info("Редактирование комментраия с id " + commentId);
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public CommentEntity getEntity(int commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new FindNoEntityException("комментарий"));
    }
}
