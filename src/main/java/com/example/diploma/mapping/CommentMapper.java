package com.example.diploma.mapping;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.entity.CommentEntity;
import com.example.diploma.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author anna
 */
@Component
public class CommentMapper {
    public Comment entityToCommentDto(CommentEntity entity) {
        return new Comment(entity.getAuthor().getId(), entity.getAuthor().getImagePath(),
                entity.getAuthor().getFirstName(), getMillis(entity.getCreatedAt()),
                entity.getPk(), entity.getText());
    }

    //из Authentication берем name, по нему ищем UserEntity и передаем сюда
    //AdEntity ищем по id
    public CommentEntity createCommentToEntity(CreateComment createComment, int ad, UserEntity author) {
        return new CommentEntity(author, LocalDateTime.now(), createComment.getText(), ad);
    }

    private long getMillis(LocalDateTime time) {
        return time.toInstant(ZoneOffset.ofHours(0)).toEpochMilli();
    }

}
