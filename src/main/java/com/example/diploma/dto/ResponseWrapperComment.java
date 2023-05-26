package com.example.diploma.dto;

import lombok.Data;

/**
 * @author anna
 */
@Data
public class ResponseWrapperComment {
    private int count;
    private Comment[] results;
}
