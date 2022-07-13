package by.cryptocurrency.quotes.model;

import javax.persistence.*;

@Entity
public class CoinPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coin_id", nullable = false)
    Coin coinId;

    public CoinPrice() {
    }

    public CoinPrice(Double price, Coin coinId) {
        this.price = price;
        this.coinId = coinId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Coin getCoinId() {
        return coinId;
    }

    public void setCoinId(Coin coinId) {
        this.coinId = coinId;
    }
}
