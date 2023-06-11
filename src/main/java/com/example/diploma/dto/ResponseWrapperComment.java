package com.example.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author anna
 */

@AllArgsConstructor
@Data
public class ResponseWrapperComment {
    private int count;
    private List<Comment> results;
}
