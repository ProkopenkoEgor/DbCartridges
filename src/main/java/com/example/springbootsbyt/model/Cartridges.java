package com.example.springbootsbyt.model;

//import com.example.springbootsbyt.entity.Cartrs;
import lombok.Data;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Data
@Entity
@Table(name = "cartridges")
public class Cartridges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "inventory_number")
    private Integer inventoryNumber;

    @Column(name = "resource")
    private Integer resource;

    @Column(name = "barcode")
    private Integer barcode;

    @Column(name = "comments")
    private String comments;

    @Column(name = "city")
    private String city;

    @Column(name = "executor")
    private String executor;

    @Column(name = "cartrs_id_cartrs")
    private Integer cartrsIdCartrs;


}
