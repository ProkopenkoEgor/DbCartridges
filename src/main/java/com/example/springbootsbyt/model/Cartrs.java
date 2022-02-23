package com.example.springbootsbyt.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "cartrs")
public class Cartrs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartrs;

    @Column(name = "chip")
    private String chip;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "type_cartr")
    private String typeCartr;

    @Column(name = "printers_id_printers")
    private Long printersIdPrinters;

    public Long getIdCartrs() {
        return idCartrs;
    }

    public void setIdCartrs(Long idCartrs) {
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

    public Long getPrintersIdPrinters() {
        return printersIdPrinters;
    }

    public void setPrintersIdPrinters(Long printersIdPrinters) {
        this.printersIdPrinters = printersIdPrinters;
    }

    public Cartrs() {
           }

    public Cartrs(Long idCartrs, String chip, String typeCartr, Long printersIdPrinters) {
        this.idCartrs = idCartrs;
        this.chip = chip;
        this.typeCartr = typeCartr;
        this.printersIdPrinters = printersIdPrinters;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartrs cartrs = (Cartrs) o;
        return Objects.equals(idCartrs, cartrs.idCartrs) &&
                Objects.equals(chip, cartrs.chip) &&
                Objects.equals(typeCartr, cartrs.typeCartr) &&
                Objects.equals(printersIdPrinters, cartrs.printersIdPrinters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCartrs, chip, typeCartr, printersIdPrinters);
    }

    @Override
    public String toString() {
        return "Cartrs{" +
                "idCartrs=" + idCartrs +
                ", chip='" + chip + '\'' +
                ", typeCartr='" + typeCartr + '\'' +
                ", printersIdPrinters='" + printersIdPrinters + '\'' +
                '}';
    }
}
