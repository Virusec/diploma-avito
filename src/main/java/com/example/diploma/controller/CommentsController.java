package com.example.diploma.controller;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.dto.ResponseWrapperComment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author anna
 */
@RestController
@RequestMapping("/ads")
public class CommentsController {

    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable int id) {
        return ResponseEntity.ok(new ResponseWrapperComment());
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable int id, @RequestBody CreateComment comment) {
        return ResponseEntity.ok(new Comment());
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int adId, @PathVariable int commentId,
                                           @RequestBody Comment newComment) {
        return ResponseEntity.ok(new Comment());
    }
}
