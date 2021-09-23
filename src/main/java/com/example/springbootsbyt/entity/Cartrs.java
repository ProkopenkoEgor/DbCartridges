package com.example.springbootsbyt.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

//@Entity
//public class Cartrs {
//    private int idCartrs;
//    private String chip;
//    private String typeCartr;
//
//    @Id
//    @Column(name = "id_cartrs", nullable = false)
//    public int getIdCartrs() {
//        return idCartrs;
//    }
//
//    public void setIdCartrs(int idCartrs) {
//        this.idCartrs = idCartrs;
//    }
//
//    @Basic
//    @Column(name = "chip", nullable = true, length = 5)
//    public String getChip() {
//        return chip;
//    }
//
//    public void setChip(String chip) {
//        this.chip = chip;
//    }
//
//    @Basic
//    @Column(name = "type_cartr", nullable = false, length = 50)
//    public String getTypeCartr() {
//        return typeCartr;
//    }
//
//    public void setTypeCartr(String typeCartr) {
//        this.typeCartr = typeCartr;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Cartrs cartrs = (Cartrs) o;
//        return idCartrs == cartrs.idCartrs &&
//                Objects.equals(chip, cartrs.chip) &&
//                Objects.equals(typeCartr, cartrs.typeCartr);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(idCartrs, chip, typeCartr);
//    }
//}
