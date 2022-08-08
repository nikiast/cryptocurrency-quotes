package by.cryptocurrency.quotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @JsonProperty("price_usd")
    private Double priceUsd;
    private String symbol;

    protected Coin() {
    }

    public Coin(Double priceUsd, String symbol) {
        this.priceUsd = priceUsd;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Coin coin = (Coin) o;
        return Objects.equals(getId(), coin.getId()) && Objects.equals(getPriceUsd(), coin.getPriceUsd()) && Objects.equals(getSymbol(), coin.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPriceUsd(), getSymbol());
    }

    @Override
    public String toString() {
        return "CoinPrice{" +
                "id=" + id +
                ", priceUsd=" + priceUsd +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
