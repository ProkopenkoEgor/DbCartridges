package com.example.springbootsbyt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "history")
@NoArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistory;

    @Column(name = "date_of_status")
    private Date dateOfStatus;

    @Column(name = "status")
    private String status;

    @Column(name = "cartridges_id")
    private Integer cartridgesId;


    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
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

    public Integer getCartridgesId() {
        return cartridgesId;
    }

    public void setCartridgesId(Integer cartridgesId) {
        this.cartridgesId = cartridgesId;
    }

}
