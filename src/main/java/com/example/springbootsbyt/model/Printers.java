package com.example.springbootsbyt.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "printers")
public class Printers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrinters;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(unique = true, name = "type_printers")
    private String typePrinters;

    @Column(name = "models_id_models")
    private Integer modelsIdModels;

    public Printers() {
    }

    public Printers(Integer idPrinters, String typePrinters, Integer modelsIdModels) {
        this.idPrinters = idPrinters;
        this.typePrinters = typePrinters;
        this.modelsIdModels = modelsIdModels;
    }

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

    public Integer getModelsIdModels() {
        return modelsIdModels;
    }

    public void setModelsIdModels(Integer modelsIdModels) {
        this.modelsIdModels = modelsIdModels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Printers printers = (Printers) o;
        return Objects.equals(idPrinters, printers.idPrinters) &&
                Objects.equals(typePrinters, printers.typePrinters) &&
                Objects.equals(modelsIdModels, printers.modelsIdModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrinters, typePrinters, modelsIdModels);
    }

    @Override
    public String toString() {
        return "Printers{" +
                "idPrinters=" + idPrinters +
                ", typePrinters='" + typePrinters + '\'' +
                ", modelsIdModels=" + modelsIdModels +
                '}';
    }
}
