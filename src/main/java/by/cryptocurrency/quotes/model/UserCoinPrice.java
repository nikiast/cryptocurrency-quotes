package by.cryptocurrency.quotes.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserCoinPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coin_price", nullable = false)
    CoinPrice price;

    protected UserCoinPrice() {
    }

    public UserCoinPrice(User userId, CoinPrice price) {
        this.userId = userId;
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
        UserCoinPrice that = (UserCoinPrice) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getUserId(), that.getUserId())
                && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getPrice());
    }

    @Override
    public String toString() {
        return "UserCoinLink{" +
                "id=" + id +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}
