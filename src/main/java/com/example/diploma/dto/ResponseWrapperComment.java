package com.example.diploma.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author anna
 */
@Data
public class ResponseWrapperComment {
    private int count;
    private ArrayList<Comment> results;
}
