package com.example.diploma.dto;

import lombok.Data;

import java.util.List;

/**
 * @author anna
 */
@Data
public class ResponseWrapperAds {
    private int count;
    private List<Ads> results;
}
