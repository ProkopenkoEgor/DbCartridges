package com.example.springbootsbyt.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "manufacturers")
public class Manufacturers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModels;

    @Column(name = "model_from_printers")
    private String modelFromPrinters;

    public Manufacturers() {
    }

    public Manufacturers(Integer idModels, String modelFromPrinters) {
        this.idModels = idModels;
        this.modelFromPrinters = modelFromPrinters;
    }

    public Integer getIdModels() {
        return idModels;
    }

    public void setIdModels(Integer idModels) {
        this.idModels = idModels;
    }

    public String getModelFromPrinters() {
        return modelFromPrinters;
    }

    public void setModelFromPrinters(String modelFromPrinters) {
        this.modelFromPrinters = modelFromPrinters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturers manufacturers = (Manufacturers) o;
        return Objects.equals(idModels, manufacturers.idModels) &&
                Objects.equals(modelFromPrinters, manufacturers.modelFromPrinters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModels, modelFromPrinters);
    }

    @Override
    public String toString() {
        return "Manufacturers{" +
                "idModels=" + idModels +
                ", modelFromPrinters='" + modelFromPrinters + '\'' +
                '}';
    }
}
