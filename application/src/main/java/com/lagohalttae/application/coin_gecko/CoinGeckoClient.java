package com.lagohalttae.application.coin_gecko;

import com.lagohalttae.application.coin.Coins;
import com.lagohalttae.application.coin.dto.CoinDto;
import com.lagohalttae.application.coin.dto.CoinPriceDto;
import com.lagohalttae.application.coin.dto.CurrentCoinDto;
import com.lagohalttae.application.coin_gecko.response.CoinGeckoCurrentResponse;
import com.lagohalttae.application.coin_gecko.response.CoinGeckoResponse;
import com.lagohalttae.application.coin_gecko.response.CoinGeckoResponseElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CoinGeckoClient {
    @Autowired
    CoinGeckoProperties properties;

    public CoinDto getCoinInfo(Coins targetCoin) {
        RestTemplate restTemplate = new RestTemplate();
        String url = properties.baseUrl + targetCoin.getCoinId() + properties.ohlcQueryParams;
        List<List<Number>> resultList
                = restTemplate.getForObject(url, List.class);
        CoinGeckoResponse response = CoinGeckoResponse.makeCoinGeckoResponse(resultList);
        CoinGeckoResponseElement maxElement = response.getMaxPriceElement();
        CoinGeckoResponseElement minElement = response.getMinPriceElement();
        return CoinDto.builder()
                .coinId(targetCoin.getCoinId())
                .label(targetCoin.getLabel())
                .maxPrice(
                        CoinPriceDto.builder()
                                .won(maxElement.getHigh())
                                .atMillis(maxElement.getDatetime())
                                .build()
                )
                .minPrice(
                        CoinPriceDto.builder()
                                .won(minElement.getLow())
                                .atMillis(minElement.getDatetime())
                                .build()
                ).imgUrl(
                        targetCoin.getImgUrl()
                )
                .build();
    }

    public CurrentCoinDto getCurrentCoinInfo(Coins targetCoin) {
        RestTemplate restTemplate = new RestTemplate();
        CoinGeckoProperties properties = new CoinGeckoProperties();
        String url
                = properties.getBaseUrl() + targetCoin.getCoinId() + properties.getCurrentQueryParams();
        CoinGeckoCurrentResponse response
                = restTemplate.getForObject(url, CoinGeckoCurrentResponse.class);
        return CurrentCoinDto.builder()
                .coinId(targetCoin.getCoinId())
                .label(targetCoin.getLabel())
                .lastUpdated(response.getLastUpdatedMillis())
                .images(
                        CurrentCoinDto.CurrentCoinImageDto.builder()
                                .thumb(response.getImage().getThumb())
                                .large(response.getImage().getLarge())
                                .small(response.getImage().getSmall())
                                .build()
                )
                .price(
                        CurrentCoinDto.CurrentCoinPriceDto.builder()
                                .won(response.getMarketData().getCurrentPrice().getWon())
                                .build()
                )
                .build();
    }
}
