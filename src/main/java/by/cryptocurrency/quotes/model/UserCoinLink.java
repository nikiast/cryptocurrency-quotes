package by.cryptocurrency.quotes.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserCoinLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    User userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coin_symbol", nullable = false)
    Coin symbol;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coin_price", nullable = false)
    CoinPrice price;

    public UserCoinLink() {
    }

    public UserCoinLink(User userId, Coin symbol, CoinPrice price) {
        this.userId = userId;
        this.symbol = symbol;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Coin getSymbol() {
        return symbol;
    }

    public void setSymbol(Coin symbol) {
        this.symbol = symbol;
    }

    public CoinPrice getPrice() {
        return price;
    }

    public void setPrice(CoinPrice price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCoinLink that = (UserCoinLink) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getSymbol(), that.getSymbol()) && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getSymbol(), getPrice());
    }

    @Override
    public String toString() {
        return "UserCoinLink{" +
                "id=" + id +
                ", userId=" + userId +
                ", symbol=" + symbol +
                ", price=" + price +
                '}';
    }
}