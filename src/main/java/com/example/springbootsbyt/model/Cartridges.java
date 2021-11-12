package com.example.springbootsbyt.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "cartridges")
public class Cartridges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Поле не может быть пустым")
    @Digits(integer = 15,fraction = 0,message = "Неверный ввод, ожидается только цифры(не более 15 цифр)")
    @Column(name = "inventory_number")
    private String inventoryNumber;

    @NotBlank(message = "Поле не может быть пустым")
    @Pattern(regexp = "[0-9.,-]+",message = "Неверный ввод, ожидаются цифры или символы")
    @Column(name = "resource")
    private String resource;

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

    @PositiveOrZero(message = "Число не должно быть меньше нуля")
    @Column(name = "count")
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
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


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cartridges{" +
                "id=" + id +
                ", inventoryNumber=" + inventoryNumber +
                ", resource=" + resource +
                ", barcode=" + barcode +
                ", comments='" + comments + '\'' +
                ", city='" + city + '\'' +
                ", executor='" + executor + '\'' +
                ", cartrsIdCartrs=" + cartrsIdCartrs +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartridges that = (Cartridges) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(inventoryNumber, that.inventoryNumber) &&
                Objects.equals(resource, that.resource) &&
                Objects.equals(barcode, that.barcode) &&
                Objects.equals(comments, that.comments) &&
                Objects.equals(city, that.city) &&
                Objects.equals(executor, that.executor) &&
                Objects.equals(cartrsIdCartrs, that.cartrsIdCartrs) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inventoryNumber, resource, barcode, comments, city, executor, cartrsIdCartrs, count);
    }

}
