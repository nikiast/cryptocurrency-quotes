package by.cryptocurrency.quotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CoinPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private Double price;
    private String symbol;

    protected CoinPrice() {
    }

    public CoinPrice(Double price, String symbol) {
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinPrice coinPrice = (CoinPrice) o;
        return Objects.equals(getId(), coinPrice.getId()) && Objects.equals(getPrice(), coinPrice.getPrice()) && Objects.equals(getSymbol(), coinPrice.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getSymbol());
    }

    @Override
    public String toString() {
        return "CoinPrice{" +
                "id=" + id +
                ", price=" + price +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
