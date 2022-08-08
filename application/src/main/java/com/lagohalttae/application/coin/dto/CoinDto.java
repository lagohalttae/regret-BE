package com.lagohalttae.application.coin.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoinDto {
    private String coinId;
    private String label;
    private CoinPriceDto maxPrice;
    private CoinPriceDto minPrice;
    private String imgUrl;
}
