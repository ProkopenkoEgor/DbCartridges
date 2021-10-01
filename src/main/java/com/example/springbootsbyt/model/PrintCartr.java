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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCartrs() {
        return idCartrs;
    }

    public void setIdCartrs(Integer idCartrs) {
        this.idCartrs = idCartrs;
    }

    public Integer getIdPrinters() {
        return idPrinters;
    }

    public void setIdPrinters(Integer idPrinters) {
        this.idPrinters = idPrinters;
    }
}
