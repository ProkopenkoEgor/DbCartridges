package com.example.springbootsbyt.model;

//import com.example.springbootsbyt.entity.Cartrs;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "cartridges")
public class Cartridges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Поле не может быть пустым")
    @Column(name = "inventory_number")
    private Integer inventoryNumber;

    @NotNull(message = "Поле не может быть пустым")
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

    @Column(name = "printers_id_printers")
    private Integer printersIdPrinters;

    @Column(name = "count")
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Integer inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public Integer getBarcode() {
        return barcode;
    }

    public void setBarcode(Integer barcode) {
        this.barcode = barcode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Integer getCartrsIdCartrs() {
        return cartrsIdCartrs;
    }

    public void setCartrsIdCartrs(Integer cartrsIdCartrs) {
        this.cartrsIdCartrs = cartrsIdCartrs;
    }

    public Integer getPrintersIdPrinters() {
        return printersIdPrinters;
    }

    public void setPrintersIdPrinters(Integer printersIdPrinters) {
        this.printersIdPrinters = printersIdPrinters;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
