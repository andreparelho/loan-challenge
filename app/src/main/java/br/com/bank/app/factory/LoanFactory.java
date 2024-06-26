package br.com.bank.app.factory;

import br.com.bank.app.model.LoanModel;

import java.math.BigDecimal;

public interface LoanFactory {
    LoanModel getLoanModel();
    boolean isAvailableLoan(BigDecimal income, int age, String location);
}
