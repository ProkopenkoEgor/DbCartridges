package com.example.springbootsbyt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "printcartr")
public class PrintCartr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_cartrs")
    private Integer idCartrs;

    @Column(name = "id_printers")
    private Integer idPrinters;

}
