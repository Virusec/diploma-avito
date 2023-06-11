package com.example.diploma.service.impl;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.dto.ResponseWrapperComment;
import com.example.diploma.entity.CommentEntity;
import com.example.diploma.mapping.CommentMapper;
import com.example.diploma.repository.CommentRepository;
import com.example.diploma.service.CommentService;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    @Override
    public ResponseWrapperComment getComments(int id) {
        List<Comment> result = new LinkedList<>();
        commentRepository.findAllByAd(id).forEach(entity -> result.add(mapper.entityToCommentDto(entity)));
        return new ResponseWrapperComment(result.size(), result);
    }

    @Override
    public Comment add(int id, CreateComment comment, String name) {
        CommentEntity entity = mapper.createCommentToEntity(comment, id, userService.getEntity(name));
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public void delete(int commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment update(int adId, int commentId, Comment newComment) {
        return null;
    }
}
