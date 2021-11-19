package com.example.springbootsbyt.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "partylots")
public class Partylots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPartylots;

    @Column(name = "lot_number")
    private String lotNumber;

    @Column(name = "cartridges_id")
    private Integer cartridgesId;

    @Column(name = "history_id_history")
    private Integer historyIdHistory;

    public Partylots() {
    }

    public Partylots(Integer idPartylots, String lotNumber, Integer cartridgesId, Integer historyIdHistory) {
        this.idPartylots = idPartylots;
        this.lotNumber = lotNumber;
        this.cartridgesId = cartridgesId;
        this.historyIdHistory = historyIdHistory;
    }

    public Integer getIdPartylots() {
        return idPartylots;
    }

    public void setIdPartylots(Integer idPartylots) {
        this.idPartylots = idPartylots;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Integer getCartridgesId() {
        return cartridgesId;
    }

    public void setCartridgesId(Integer cartridgesId) {
        this.cartridgesId = cartridgesId;
    }

    public Integer getHistoryIdHistory() {
        return historyIdHistory;
    }

    public void setHistoryIdHistory(Integer historyIdHistory) {
        this.historyIdHistory = historyIdHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partylots partylots = (Partylots) o;
        return Objects.equals(idPartylots, partylots.idPartylots) &&
                Objects.equals(lotNumber, partylots.lotNumber) &&
                Objects.equals(cartridgesId, partylots.cartridgesId) &&
                Objects.equals(historyIdHistory, partylots.historyIdHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPartylots, lotNumber, cartridgesId, historyIdHistory);
    }

    @Override
    public String toString() {
        return "Partylots{" +
                "idPartylots=" + idPartylots +
                ", lotNumber='" + lotNumber + '\'' +
                ", cartridgesId=" + cartridgesId +
                ", historyIdHistory=" + historyIdHistory +
                '}';
    }
}
