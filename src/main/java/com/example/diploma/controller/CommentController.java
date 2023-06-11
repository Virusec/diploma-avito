package com.example.diploma.controller;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.dto.ResponseWrapperComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;

    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable int id) {
        return ResponseEntity.ok(new ResponseWrapperComment());
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable int id, @RequestBody CreateComment comment) {
        return ResponseEntity.ok(new Comment());
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int adId, @PathVariable int commentId,
                                           @RequestBody Comment newComment) {
        return ResponseEntity.ok(new Comment());
    }
}
