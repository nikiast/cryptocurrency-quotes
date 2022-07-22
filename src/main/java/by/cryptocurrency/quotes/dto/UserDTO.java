package by.cryptocurrency.quotes.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {
    private String username;
    private String symbol;

    public UserDTO() {
    }

    public UserDTO(String username, String symbol) {
        this.username = username;
        this.symbol = symbol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getUsername(), userDTO.getUsername()) && Objects.equals(getSymbol(), userDTO.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getSymbol());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}