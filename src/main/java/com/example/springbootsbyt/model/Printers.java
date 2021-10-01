package com.example.springbootsbyt.model;

import lombok.Data;
import org.aspectj.weaver.GeneratedReferenceTypeDelegate;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

@Data
@Entity
@Table(name = "printers")
public class Printers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrinters;

    @Column(name = "type_printers")
    private String typePrinters;

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
}
