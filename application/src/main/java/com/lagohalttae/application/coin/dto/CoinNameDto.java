package com.lagohalttae.application.coin.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoinNameDto {
    private String coinId;
    private String label;
    private String imageUrl;
}
