package com.example.diploma.dto;

import lombok.Data;

/**
 * @author anna
 */
@Data
public class Comment {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
    private String text;
}
