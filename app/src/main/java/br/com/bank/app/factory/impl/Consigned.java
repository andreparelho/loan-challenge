package br.com.bank.app.factory.impl;

import br.com.bank.app.factory.LoanFactory;
import br.com.bank.app.model.LoanModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class Consigned implements LoanFactory {
    private static final String TYPE = "consigned";

    @Override
    public LoanModel getLoanModel() {
        return LoanModel
                .builder()
                .type(TYPE)
                .taxes(2)
                .build();
    }

    @Override
    public boolean isAvailableLoan(BigDecimal income, int age, String location) {
        return income.doubleValue() >= 5000;
    }
}
