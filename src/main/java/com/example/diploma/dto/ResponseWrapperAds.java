package com.example.diploma.dto;

import lombok.Data;

/**
 * @author anna
 */
@Data
public class ResponseWrapperAds {
    private int count;
    private Ads[] results;
}
