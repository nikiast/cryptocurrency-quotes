package by.cryptocurrency.quotes.dto;

import java.io.Serializable;
import java.util.Objects;

public class CoinDTO implements Serializable {
    private String symbol;
    private Double priceUsd;

    public CoinDTO() {
    }

    public CoinDTO(String symbol, Double priceUsd) {
        this.symbol = symbol;
        this.priceUsd = priceUsd;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinDTO coinDTO = (CoinDTO) o;
        return Objects.equals(getSymbol(), coinDTO.getSymbol()) && Objects.equals(getPriceUsd(), coinDTO.getPriceUsd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol(), getPriceUsd());
    }

    @Override
    public String toString() {
        return "CoinDTO{" +
                "symbol='" + symbol + '\'' +
                ", priceUsd=" + priceUsd +
                '}';
    }
}