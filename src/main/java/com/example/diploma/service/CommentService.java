package com.example.diploma.service;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.dto.ResponseWrapperComment;
import com.example.diploma.entity.CommentEntity;

/**
 * @author anna
 */
public interface CommentService {
    ResponseWrapperComment getComments(int id);

    Comment add(int id, CreateComment comment, String name);

    void delete(int commentId);

    Comment update(int commentId, Comment newComment, String email);

    CommentEntity getEntity(int commentId);
}
