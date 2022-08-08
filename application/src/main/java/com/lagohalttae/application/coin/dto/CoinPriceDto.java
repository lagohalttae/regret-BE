package com.lagohalttae.application.coin.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoinPriceDto {
    private Double won;
    private Long atMillis;
}