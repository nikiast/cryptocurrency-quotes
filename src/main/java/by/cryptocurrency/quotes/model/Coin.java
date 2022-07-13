package by.cryptocurrency.quotes.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coin_id")
    private Long coinId;

    private Integer id;
    private String symbol;
    private Double price_usd;

    public Coin() {
    }

    public Coin(Integer id, String symbol, Double price_usd) {
        this.id = id;
        this.symbol = symbol;
        this.price_usd = price_usd;
    }

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(Double price_usd) {
        this.price_usd = price_usd;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", price_usd=" + price_usd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return Objects.equals(coinId, coin.coinId) && Objects.equals(getId(), coin.getId())
                && Objects.equals(getSymbol(), coin.getSymbol())
                && Objects.equals(getPrice_usd(), coin.getPrice_usd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinId, getId(), getSymbol(), getPrice_usd());
    }
}