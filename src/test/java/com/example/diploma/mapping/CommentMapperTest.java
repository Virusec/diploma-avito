package com.example.diploma.mapping;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.entity.AdEntity;
import com.example.diploma.entity.CommentEntity;
import com.example.diploma.entity.ImageEntity;
import com.example.diploma.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Anatoliy Shikin
 */
class CommentMapperTest {

    private final CommentMapper commentMapper = new CommentMapper();

    @Test
    void testEntityToCommentDto() {
        UserEntity author = new UserEntity();
        author.setId(1);
        author.setImage(new ImageEntity());
        author.setFirstName("John Doe");
        author.setLastName("Doe");

        LocalDateTime createdAt = LocalDateTime.now();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(author);
        commentEntity.setCreatedAt(createdAt);
        commentEntity.setPk(1);
        commentEntity.setText("This is a comment");

        Comment expectedComment = new Comment(1, "/users/image/1", "John Doe",
                createdAt.toInstant(ZoneOffset.ofHours(5)).toEpochMilli(), 1, "This is a comment");

        Comment result = commentMapper.entityToCommentDto(commentEntity);

        assertEquals(expectedComment, result);
    }

    @Test
    void testCreateCommentToEntity() {
        CreateComment createComment = new CreateComment();
        createComment.setText("New comment");

        AdEntity ad = new AdEntity();
        UserEntity author = new UserEntity();

        CommentEntity result = commentMapper.createCommentToEntity(createComment, ad, author);

        assertEquals(createComment.getText(), result.getText());
        assertEquals(ad, result.getAd());
        assertEquals(author, result.getAuthor());
    }

}