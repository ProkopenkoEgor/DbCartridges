package com.example.springbootsbyt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cartrs")
public class Cartrs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCartrs;

    @Column(name = "chip")
    private String chip;

    @Column(name = "type_cartr")
    private String typeCartr;

    public Integer getIdCartrs() {
        return idCartrs;
    }

    public void setIdCartrs(Integer idCartrs) {
        this.idCartrs = idCartrs;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getTypeCartr() {
        return typeCartr;
    }

    public void setTypeCartr(String typeCartr) {
        this.typeCartr = typeCartr;
    }
}
