package by.cryptocurrency.quotes.dto;

import java.io.Serializable;
import java.util.Objects;

public class AvailableCoin implements Serializable {
    private Integer id;
    private String symbol;

    public AvailableCoin() {
    }

    public AvailableCoin(Integer id, String symbol) {
        this.id = id;
        this.symbol = symbol;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableCoin that = (AvailableCoin) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSymbol(), that.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSymbol());
    }

    @Override
    public String toString() {
        return "AvailableCoin{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
