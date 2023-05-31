package com.example.diploma.dto;

import lombok.Data;

import java.util.List;

/**
 * @author anna
 */
@Data
public class ResponseWrapperComment {
    private int count;
    private List<Comment> results;
}
