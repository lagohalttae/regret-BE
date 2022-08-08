package com.lagohalttae.application;

import com.lagohalttae.application.coin.Coins;
import com.lagohalttae.application.coin.dto.CoinDto;
import com.lagohalttae.application.coin.dto.CoinNameDto;
import com.lagohalttae.application.coin.dto.CoinPriceDto;
import com.lagohalttae.application.coin_gecko.CoinGeckoProperties;
import com.lagohalttae.application.coin_gecko.response.CoinGeckoCurrentResponse;
import com.lagohalttae.application.coin_gecko.response.CoinGeckoResponse;
import com.lagohalttae.application.coin_gecko.response.CoinGeckoResponseElement;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {}

	@Test
	void checkListOfCoins() {
		Stream.of(Coins.values()).forEach(System.out::println);
	}

	@Test
	void callCoinGecko() {
		Coins targetCoin = Coins.DOGECOIN;
		RestTemplate restTemplate = new RestTemplate();
		CoinGeckoProperties properties = new CoinGeckoProperties();
		String ohlcUrl
				= properties.getBaseUrl() + targetCoin.getCoinId() + properties.getOhlcQueryParams();
		List<List<Number>> resultList
				= restTemplate.getForObject(ohlcUrl, List.class);
		CoinGeckoResponse response = CoinGeckoResponse.makeCoinGeckoResponse(resultList);
		CoinGeckoResponseElement maxElement = response.getMaxPriceElement();
		CoinGeckoResponseElement minElement = response.getMinPriceElement();
		CoinDto dto = CoinDto.builder()
				.coinId(targetCoin.getCoinId())
				.label(targetCoin.getLabel())
				.maxPrice(
						CoinPriceDto.builder()
								.won(maxElement.getHigh())
								.atMillis(minElement.getDatetime())
								.build()
				)
				.minPrice(
						CoinPriceDto.builder()
								.won(minElement.getLow())
								.atMillis(minElement.getDatetime())
								.build()
				).build();
		System.out.println(Coins.valueOf("bitcoin"));
	}

	@Test
	void callCurrentCoinGeckoApi() {
		Coins targetCoin = Coins.DOGECOIN;
		RestTemplate restTemplate = new RestTemplate();
		CoinGeckoProperties properties = new CoinGeckoProperties();
		String url
				= properties.getBaseUrl() + targetCoin.getCoinId() + properties.getCurrentQueryParams();
		CoinGeckoCurrentResponse response
				= restTemplate.getForObject(url, CoinGeckoCurrentResponse.class);
		System.out.println(response);
	}

	@Test
	void checkCoinListResponse() {
		List<Coins> coins = Stream.of(Coins.values()).collect(Collectors.toList());
		List<CoinNameDto> dtoList = coins.stream().map(it ->
				CoinNameDto.builder()
						.coinId(it.getCoinId())
						.label(it.getLabel())
						.build()
		).collect(Collectors.toList());
		System.out.println(dtoList);
	}
}
