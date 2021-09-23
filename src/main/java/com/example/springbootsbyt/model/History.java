package com.example.springbootsbyt.model;

import lombok.Data;
import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "history")
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
}
