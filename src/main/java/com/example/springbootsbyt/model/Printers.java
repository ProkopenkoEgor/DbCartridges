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

    @Column(name = "type_print")
    private String typePrint;
}
