package com.example.springbootsbyt.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "partylots")
public class Partylots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartylots;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "lot_number")
    private String lotNumber;

    @Column(name = "cartridges_id")
    private Long cartridgesId;

    @Column(name = "history_id_history")
    private Long historyIdHistory;

    @Column(name = "party_status")
    private Long partyStatus;

    @Column(name = "party_comments")
    private String partyComments;

    @Column(name = "history_id_history_return")
    private Long historyIdHistoryReturn;

    public Partylots() {
    }

    public Partylots(Long idPartylots, String lotNumber, Long cartridgesId, Long historyIdHistory, Long partyStatus, String partyComments, Long historyIdHistoryReturn) {
        this.idPartylots = idPartylots;
        this.lotNumber = lotNumber;
        this.cartridgesId = cartridgesId;
        this.historyIdHistory = historyIdHistory;
        this.partyStatus = partyStatus;
        this.partyComments = partyComments;
        this.historyIdHistoryReturn = historyIdHistoryReturn;
    }

    public Long getIdPartylots() {
        return idPartylots;
    }

    public void setIdPartylots(Long idPartylots) {
        this.idPartylots = idPartylots;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Long getCartridgesId() {
        return cartridgesId;
    }

    public void setCartridgesId(Long cartridgesId) {
        this.cartridgesId = cartridgesId;
    }

    public Long getHistoryIdHistory() {
        return historyIdHistory;
    }

    public void setHistoryIdHistory(Long historyIdHistory) {
        this.historyIdHistory = historyIdHistory;
    }

    public Long getPartyStatus() {
        return partyStatus;
    }

    public void setPartyStatus(Long partyStatus) {
        this.partyStatus = partyStatus;
    }

    public String getPartyComments() {
        return partyComments;
    }

    public void setPartyComments(String partyComments) {
        this.partyComments = partyComments;
    }

    public Long getHistoryIdHistoryReturn() {
        return historyIdHistoryReturn;
    }

    public void setHistoryIdHistoryReturn(Long historyIdHistoryReturn) {
        this.historyIdHistoryReturn = historyIdHistoryReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partylots partylots = (Partylots) o;
        return Objects.equals(idPartylots, partylots.idPartylots) &&
                Objects.equals(lotNumber, partylots.lotNumber) &&
                Objects.equals(cartridgesId, partylots.cartridgesId) &&
                Objects.equals(historyIdHistory, partylots.historyIdHistory) &&
                Objects.equals(partyStatus, partylots.partyStatus) &&
                Objects.equals(partyComments, partylots.partyComments) &&
                Objects.equals(historyIdHistoryReturn, partylots.historyIdHistoryReturn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPartylots, lotNumber, cartridgesId, historyIdHistory, partyStatus, partyComments, historyIdHistoryReturn);
    }

    @Override
    public String toString() {
        return "Partylots{" +
                "idPartylots=" + idPartylots +
                ", lotNumber='" + lotNumber + '\'' +
                ", cartridgesId=" + cartridgesId +
                ", historyIdHistory=" + historyIdHistory +
                ", partyStatus=" + partyStatus +
                ", partyComments='" + partyComments + '\'' +
                ", historyIdHistoryReturn=" + historyIdHistoryReturn +
                '}';
    }
}
