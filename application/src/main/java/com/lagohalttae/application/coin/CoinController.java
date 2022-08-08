package com.lagohalttae.application.coin;

import com.lagohalttae.application.coin.dto.CoinDto;
import com.lagohalttae.application.coin.dto.CoinNameDto;
import com.lagohalttae.application.coin.dto.CurrentCoinDto;
import com.lagohalttae.application.coin_gecko.CoinGeckoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class CoinController {

    @Autowired
    CoinGeckoClient coinGeckoClient;

    @GetMapping("/coins/{coinId}")
    public CoinDto getCoinInfo(@PathVariable String coinId) {
        Coins coin = Coins.findCoinByCoinId(coinId);
        return coinGeckoClient.getCoinInfo(coin);
    }

    @GetMapping("/coins/titles")
    public List<CoinNameDto> getCoinTitleList() {
        List<Coins> coins = Stream.of(Coins.values()).collect(Collectors.toList());
        return coins.stream().map(it ->
                CoinNameDto.builder()
                        .coinId(it.getCoinId())
                        .label(it.getLabel())
                        .imageUrl(it.getImgUrl())
                        .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/coins/{coinId}/current")
    public CurrentCoinDto getCurrentCoinInfo(@PathVariable String coinId) {
        Coins coin = Coins.findCoinByCoinId(coinId);
        return coinGeckoClient.getCurrentCoinInfo(coin);
    }
}