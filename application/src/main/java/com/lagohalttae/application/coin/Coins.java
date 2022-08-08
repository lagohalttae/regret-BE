package com.lagohalttae.application.coin;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Coins {
    BITCOIIN("bitcoin", "비트코인", "bitcoin", "https://cryptologos.cc/logos/bitcoin-btc-logo.png?v=022"),
    EHTERIUM("ethereum", "이더리움", "ethereum", "https://cryptologos.cc/logos/ethereum-eth-logo.png?v=022"),
    TETHER("tether", "테더", "tether", "https://cryptologos.cc/logos/tether-usdt-logo.png?v=022"),
    USDCOIN("usd-coin", "USD 코인", "usd-coin", "https://cryptologos.cc/logos/usd-coin-usdc-logo.png?v=022"),
    BNB("binancecoin", "바이낸스", "binancecoin", "https://cryptologos.cc/logos/binance-coin-bnb-logo.png?v=022"),
    BINANCE_USD("binance-usd", "바이낸스 USD", "binance-usd", "https://cryptologos.cc/logos/binance-usd-busd-logo.png?v=022"),
    CARDANO("cardano", "카르다노", "cardano", "https://cryptologos.cc/logos/cardano-ada-logo.png?v=022"),
    XRP("ripple", "리플", "ripple", "https://i.postimg.cc/1RrX9b80/ripple-logo.png?v=022"),
    SOLANA("solana", "솔라나", "solana", "https://cryptologos.cc/logos/solana-sol-logo.png?v=022"),
    DOGECOIN("dogecoin", "도지코인", "dogecoin", "https://cryptologos.cc/logos/dogecoin-doge-logo.png?v=022"),
    ;

    private final String coinId;
    private final String label;
    private final String geckoId;
    private final String imgUrl;

    Coins(String coinId, String label, String geckoId, String imgUrl) {
        this.coinId = coinId;
        this.label = label;
        this.geckoId = geckoId;
        this.imgUrl = imgUrl;
    }

    public static Coins findCoinByCoinId(String coinId) {
        return Arrays.stream(Coins.values()).filter(v ->
                v.getCoinId().equals(coinId)).findFirst().orElseThrow(() ->
                new IllegalArgumentException(String.format("Unknown coinId: '%s'", coinId))
        );
    }
}
