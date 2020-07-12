package com.ilyasidorov.orginformer.model;

import java.time.LocalDate;

public class Security {

    private String code;
    private LocalDate expiryDate;
    private int ownerId;
    private Currency currency;

    public enum Currency {
        EUR, DEM, RUB, USD;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
