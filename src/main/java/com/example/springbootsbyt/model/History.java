package com.example.springbootsbyt.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistory;

    @Column(name = "date_of_status")
    private Date dateOfStatus;

    @Column(name = "status")
    private String status;

    @Column(name = "cartridges_id")
    private Long cartridgesId;

    @Column(name = "executor")
    private String executor;


    public History() {
    }

    public History(Long idHistory, Date dateOfStatus, String status, Long cartridgesId, String executor) {
        this.idHistory = idHistory;
        this.dateOfStatus = dateOfStatus;
        this.status = status;
        this.cartridgesId = cartridgesId;
        this.executor = executor;
    }

    public Long getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Long idHistory) {
        this.idHistory = idHistory;
    }

    public Date getDateOfStatus() {
        return dateOfStatus;
    }

    public void setDateOfStatus(Date dateOfStatus) {
        this.dateOfStatus = dateOfStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCartridgesId() {
        return cartridgesId;
    }

    public void setCartridgesId(Long cartridgesId) {
        this.cartridgesId = cartridgesId;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(idHistory, history.idHistory) &&
                Objects.equals(dateOfStatus, history.dateOfStatus) &&
                Objects.equals(status, history.status) &&
                Objects.equals(cartridgesId, history.cartridgesId) &&
                Objects.equals(executor, history.executor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistory, dateOfStatus, status, cartridgesId, executor);
    }

    @Override
    public String toString() {
        return "History{" +
                "idHistory=" + idHistory +
                ", dateOfStatus=" + dateOfStatus +
                ", status='" + status + '\'' +
                ", cartridgesId=" + cartridgesId +
                ", executor='" + executor + '\'' +
                '}';
    }
}
