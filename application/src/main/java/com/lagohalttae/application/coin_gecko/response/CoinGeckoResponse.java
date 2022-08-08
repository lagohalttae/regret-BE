package com.lagohalttae.application.coin_gecko.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class CoinGeckoResponse implements Serializable {
    private List<CoinGeckoResponseElement> list;

    public static CoinGeckoResponse makeCoinGeckoResponse(List<List<Number>> list) {
        return CoinGeckoResponse.builder()
                .list(
                        list.stream()
                                .map(CoinGeckoResponseElement::makeCoinGeckoResponseElement)
                                .collect(Collectors.toList())
                ).build();
    }


    public CoinGeckoResponseElement getMaxPriceElement() {
        List<Double> listOfAvg = list.stream()
                .map(it -> (it.getHigh() + it.getLow()) / 2)
                .collect(Collectors.toList());
        AbstractMap.SimpleEntry<Integer, Integer> result = calculateSellingAndBuyingTime(listOfAvg);
        return list.get(result.getKey());
    }

    public CoinGeckoResponseElement getMinPriceElement() {
        List<Double> listOfAvg = list.stream()
                .map(it -> (it.getHigh() + it.getLow()) / 2)
                .collect(Collectors.toList());
        AbstractMap.SimpleEntry<Integer, Integer> result = calculateSellingAndBuyingTime(listOfAvg);
        return list.get(result.getValue());
    }

    private AbstractMap.SimpleEntry<Integer, Integer> calculateSellingAndBuyingTime(List<Double> list) {
        Double max = list.get(1) - list.get(0);
        Integer sellingIndex = 1;
        Integer buyingIndex = 0;

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Double profit = list.get(j) - list.get(i);
                if (max <= profit) {
                    max = profit;
                    sellingIndex = j;
                    buyingIndex = i;
                }
            }
        }

        return new AbstractMap.SimpleEntry<Integer, Integer>(sellingIndex, buyingIndex);
    }
}
