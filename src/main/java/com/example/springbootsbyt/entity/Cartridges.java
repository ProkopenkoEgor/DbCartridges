package com.example.springbootsbyt.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

//@Entity
//public class Cartridges {
//    private int id;
//    private int inventoryNumber;
//    private Integer resource;
//    private Integer barcode;
//    private String comments;
//    private String city;
//    private String executor;
//    private int cartrsIdCartrs;
//
//
//
//    @Id
//    @Column(name = "id", nullable = false)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "inventory_number", nullable = false)
//    public int getInventoryNumber() {
//        return inventoryNumber;
//    }
//
//    public void setInventoryNumber(int inventoryNumber) {
//        this.inventoryNumber = inventoryNumber;
//    }
//
//    @Basic
//    @Column(name = "resource", nullable = true)
//    public Integer getResource() {
//        return resource;
//    }
//
//    public void setResource(Integer resource) {
//        this.resource = resource;
//    }
//
//    @Basic
//    @Column(name = "barcode", nullable = true)
//    public Integer getBarcode() {
//        return barcode;
//    }
//
//    public void setBarcode(Integer barcode) {
//        this.barcode = barcode;
//    }
//
//    @Basic
//    @Column(name = "comments", nullable = true, length = 100)
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    @Basic
//    @Column(name = "city", nullable = true, length = 45)
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    @Basic
//    @Column(name = "executor", nullable = true, length = 45)
//    public String getExecutor() {
//        return executor;
//    }
//
//    public void setExecutor(String executor) {
//        this.executor = executor;
//    }
//
//    @Column(name="cartrs_id_cartrs")
//    public int getCartrsIdCartrs() {
//        return cartrsIdCartrs;
//    }
//
//    public void setCartrsIdCartrs(int cartrsIdCartrs) {
//        this.cartrsIdCartrs = cartrsIdCartrs;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Cartridges that = (Cartridges) o;
//        return id == that.id &&
//                inventoryNumber == that.inventoryNumber &&
//                Objects.equals(resource, that.resource) &&
//                Objects.equals(barcode, that.barcode) &&
//                Objects.equals(comments, that.comments) &&
//                Objects.equals(city, that.city) &&
//                Objects.equals(executor, that.executor);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, inventoryNumber, resource, barcode, comments, city, executor);
//    }
//
//    public Cartridges() {
//    }
//}
