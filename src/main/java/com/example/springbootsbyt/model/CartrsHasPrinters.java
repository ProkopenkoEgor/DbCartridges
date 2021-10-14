package com.example.springbootsbyt.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cartrs_has_printers", schema = "sbyt")
@IdClass(CartrsHasPrintersPK.class)
public class CartrsHasPrinters {
    private int cartrsIdCartrs;
    private int printersIdPrinters;

    @Id
    @Column(name = "cartrs_id_cartrs", nullable = false)
    public int getCartrsIdCartrs() {
        return cartrsIdCartrs;
    }

    public void setCartrsIdCartrs(int cartrsIdCartrs) {
        this.cartrsIdCartrs = cartrsIdCartrs;
    }

    @Id
    @Column(name = "printers_id_printers", nullable = false)
    public int getPrintersIdPrinters() {
        return printersIdPrinters;
    }

    public void setPrintersIdPrinters(int printersIdPrinters) {
        this.printersIdPrinters = printersIdPrinters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartrsHasPrinters that = (CartrsHasPrinters) o;
        return cartrsIdCartrs == that.cartrsIdCartrs &&
                printersIdPrinters == that.printersIdPrinters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartrsIdCartrs, printersIdPrinters);
    }
}
