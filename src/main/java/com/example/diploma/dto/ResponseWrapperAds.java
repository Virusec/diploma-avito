package com.example.diploma.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author anna
 */
@Data
public class ResponseWrapperAds {
    private int count;
    private ArrayList<Ads> results;
}
