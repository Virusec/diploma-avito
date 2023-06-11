package com.example.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anna
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
    private String text;
}
