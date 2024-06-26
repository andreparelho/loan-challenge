package br.com.bank.app.model;

import lombok.Builder;

@Builder
public class LoanModel {
    private String type;
    private int taxes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTaxes() {
        return taxes;
    }

    public void setTaxes(int taxes) {
        this.taxes = taxes;
    }
}
