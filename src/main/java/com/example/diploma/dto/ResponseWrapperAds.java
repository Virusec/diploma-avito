package com.example.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author anna
 */
@Data
@AllArgsConstructor
public class ResponseWrapperAds {
    private int count;
    private List<Ads> results;
}
