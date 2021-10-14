package com.example.springbootsbyt.model;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "printers")
public class Printers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrinters;


    @Column(unique = true, name = "type_printers")
    private String typePrinters;

    @Column(name = "model_from_printers")
    private String modelFromPrinters;

    @ManyToMany(mappedBy = "printersSet",fetch = FetchType.LAZY)
    private Set<Cartrs> cartrsSet = new HashSet<>();

    public Integer getIdPrinters() {
        return idPrinters;
    }

    public void setIdPrinters(Integer idPrinters) {
        this.idPrinters = idPrinters;
    }

    public String getTypePrinters() {
        return typePrinters;
    }

    public void setTypePrinters(String typePrinters) {
        this.typePrinters = typePrinters;
    }

    public String getModelFromPrinters() {
        return modelFromPrinters;
    }

    public void setModelFromPrinters(String modelFromPrinters) {
        this.modelFromPrinters = modelFromPrinters;
    }

    public Printers() {
    }

    public Printers(Integer idPrinters, String typePrinters, String modelFromPrinters) {
        this.idPrinters = idPrinters;
        this.typePrinters = typePrinters;
        this.modelFromPrinters = modelFromPrinters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Printers printers = (Printers) o;
        return Objects.equals(idPrinters, printers.idPrinters) &&
                Objects.equals(typePrinters, printers.typePrinters) &&
                Objects.equals(modelFromPrinters, printers.modelFromPrinters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrinters, typePrinters, modelFromPrinters);
    }

    @Override
    public String toString() {
        return "Printers{" +
                "idPrinters=" + idPrinters +
                ", typePrinters='" + typePrinters + '\'' +
                ", modelFromPrinters='" + modelFromPrinters + '\'' +
                '}';
    }
}
