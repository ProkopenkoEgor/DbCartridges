package com.example.springbootsbyt.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CartrsHasPrintersPK implements Serializable {
    private int cartrsIdCartrs;
    private int printersIdPrinters;

    @Column(name = "cartrs_id_cartrs", nullable = false)
    @Id
    public int getCartrsIdCartrs() {
        return cartrsIdCartrs;
    }

    public void setCartrsIdCartrs(int cartrsIdCartrs) {
        this.cartrsIdCartrs = cartrsIdCartrs;
    }

    @Column(name = "printers_id_printers", nullable = false)
    @Id
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
        CartrsHasPrintersPK that = (CartrsHasPrintersPK) o;
        return cartrsIdCartrs == that.cartrsIdCartrs &&
                printersIdPrinters == that.printersIdPrinters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartrsIdCartrs, printersIdPrinters);
    }
}
