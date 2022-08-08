package com.lagohalttae.application.coin_gecko;

import lombok.Data;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class CoinGeckoProperties {
    String baseUrl = "https://api.coingecko.com/api/v3/coins/";
    String ohlcQueryParams = "/ohlc?vs_currency=krw&days=30";
    String currentQueryParams = "?localization=false&tickers=false&community_data=false&developer_data=false&sparkline=false";
    String apiKey = "";
}
