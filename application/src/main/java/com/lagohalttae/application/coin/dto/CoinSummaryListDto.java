package com.lagohalttae.application.coin.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CoinSummaryListDto {
    private List<CoinNameDto> coins;
    private String nextCursor;
    private Boolean isLast;
}
