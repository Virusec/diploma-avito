package com.example.diploma.service.impl;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.dto.ResponseWrapperComment;
import com.example.diploma.entity.AdEntity;
import com.example.diploma.entity.CommentEntity;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.mapping.CommentMapper;
import com.example.diploma.repository.CommentRepository;
import com.example.diploma.service.AdService;
import com.example.diploma.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Anatoliy Shikin
 */
class CommentServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private AdService adService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper mapper;

    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentServiceImpl(userService, adService, commentRepository, mapper);
    }

    @Test
    void testGetComments() {
        int adId = 1;
        CommentEntity commentEntity = new CommentEntity();
        Comment comment = new Comment();
        when(commentRepository.findAllByAd_Pk(adId)).thenReturn(List.of(commentEntity));
        when(mapper.entityToCommentDto(commentEntity)).thenReturn(comment);

        ResponseWrapperComment response = commentService.getComments(adId);

        assertEquals(1, response.getCount());
        assertEquals(List.of(comment), response.getResults());
        verify(commentRepository).findAllByAd_Pk(adId);
        verify(mapper).entityToCommentDto(commentEntity);
    }

    @Test
    void testAdd() {
        int adId = 1;
        String name = "John Doe";
        CreateComment createComment = new CreateComment();
        CommentEntity commentEntity = new CommentEntity();
        Comment comment = new Comment();
        when(adService.getEntity(adId)).thenReturn(new AdEntity());
        when(userService.getEntity(name)).thenReturn(new UserEntity());
        when(mapper.createCommentToEntity(createComment, new AdEntity(), new UserEntity())).thenReturn(commentEntity);
        when(commentRepository.save(commentEntity)).thenReturn(commentEntity);
        when(mapper.entityToCommentDto(commentEntity)).thenReturn(comment);

        Comment result = commentService.add(adId, createComment, name);

        assertSame(comment, result);
        verify(adService).getEntity(adId);
        verify(userService).getEntity(name);
        verify(mapper).createCommentToEntity(createComment, new AdEntity(), new UserEntity());
        verify(commentRepository).save(commentEntity);
        verify(mapper).entityToCommentDto(commentEntity);
    }

    @Test
    void testDelete() {
        int commentId = 1;

        commentService.delete(commentId);

        verify(commentRepository).deleteById(commentId);
    }

    @Test
    void testUpdate() {
        int commentId = 1;
        String email = "john@example.com";
        CommentEntity commentEntity = new CommentEntity();
        UserEntity userEntity = new UserEntity();

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentEntity));
        when(userService.getEntity(email)).thenReturn(userEntity);
        when(commentRepository.save(commentEntity)).thenReturn(commentEntity);
        when(mapper.entityToCommentDto(commentEntity)).thenReturn(new Comment());

        Comment result = commentService.update(commentId, new Comment(), email);

        assertNotNull(result);
        verify(commentRepository).findById(commentId);
        verify(userService).getEntity(email);
        verify(commentRepository).save(commentEntity);
        verify(mapper).entityToCommentDto(commentEntity);

    }

    @Test
    void testGetEntity() {
        int commentId = 1;
        CommentEntity commentEntity = new CommentEntity();

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentEntity));

        CommentEntity result = commentService.getEntity(commentId);

        assertSame(commentEntity, result);
        verify(commentRepository).findById(commentId);
    }

    @Test
    void testGetEntity_ThrowsFindNoEntityException() {
        int commentId = 1;

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThrows(FindNoEntityException.class, () -> commentService.getEntity(commentId));

        verify(commentRepository).findById(commentId);
    }
}