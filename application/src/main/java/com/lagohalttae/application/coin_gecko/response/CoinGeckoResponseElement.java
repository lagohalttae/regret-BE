package com.lagohalttae.application.coin_gecko.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Builder
@Data
public class CoinGeckoResponseElement implements Serializable {

    private Double high;
    private Double open;
    private Double low;
    private Double close;
    private Long datetime;

    static CoinGeckoResponseElement makeCoinGeckoResponseElement(List<Number> list) {
        return CoinGeckoResponseElement.builder()
                .datetime(list.get(0).longValue())
                .open(list.get(1).doubleValue())
                .high(list.get(2).doubleValue())
                .low(list.get(3).doubleValue())
                .close(list.get(4).doubleValue())
                .build();
    }
}