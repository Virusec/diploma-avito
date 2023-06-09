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
public class Ads {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
