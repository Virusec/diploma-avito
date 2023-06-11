package com.example.diploma.service;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.dto.ResponseWrapperComment;

/**
 * @author anna
 */
public interface CommentService {
    ResponseWrapperComment getComments(int id);

    Comment add(int id, CreateComment comment, String name);

    void delete(int commentId);

    Comment update(int adId, int commentId, Comment newComment);
}
