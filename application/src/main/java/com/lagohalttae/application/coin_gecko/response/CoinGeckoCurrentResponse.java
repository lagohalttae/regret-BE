package com.lagohalttae.application.coin_gecko.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class CoinGeckoCurrentResponse implements Serializable {
    private String id;
    private String symbol;
    private String name;

    @JsonProperty("last_updated")
    private String lastUpdated;

    private CoinGeckoCurrentImageResponse image;

    @JsonProperty("market_data")
    private CoinGeckoCurrentMarketData marketData;

    public Long getLastUpdatedMillis() {
        LocalDateTime localDateTime = LocalDateTime.parse(
                this.lastUpdated,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        );
        return localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();
    }

    @Data
    @NoArgsConstructor
    public class CoinGeckoCurrentImageResponse implements Serializable {
        private String thumb;
        private String small;
        private String large;
    }

    @Data
    @NoArgsConstructor

    public class CoinGeckoCurrentMarketData implements Serializable {
        @JsonProperty("current_price")
        private CoinGeckoCurrentPrice currentPrice;

        @Data
        @NoArgsConstructor

        public class CoinGeckoCurrentPrice implements Serializable {
            @JsonProperty("krw")
            private double won;
        }
    }
}
