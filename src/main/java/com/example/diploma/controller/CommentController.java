package com.example.diploma.controller;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.dto.ResponseWrapperComment;
import com.example.diploma.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author anna
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable int id, @RequestBody CreateComment comment,
                                              Authentication authentication) {
        return ResponseEntity.ok(commentService.add(id, comment, authentication.getName()));
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int commentId, @RequestBody Comment newComment,
                                                 Authentication authentication, @PathVariable String adId) {
        return ResponseEntity.ok(commentService.update(commentId, newComment, authentication.getName()));
    }
}
