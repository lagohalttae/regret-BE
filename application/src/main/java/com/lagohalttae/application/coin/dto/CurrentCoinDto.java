package com.lagohalttae.application.coin.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class CurrentCoinDto {
    private String coinId;
    private String label;
    private Long lastUpdated;
    private CurrentCoinImageDto images;
    private CurrentCoinPriceDto price;

    @Builder
    @Data
    static public class CurrentCoinImageDto {
        private String thumb;
        private String small;
        private String large;
    }

    @Builder
    @Data
    static public class CurrentCoinPriceDto implements Serializable {
        private double won;
    }
}



