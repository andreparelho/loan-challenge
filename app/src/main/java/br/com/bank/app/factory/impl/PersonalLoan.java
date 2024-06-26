package br.com.bank.app.factory.impl;

import br.com.bank.app.factory.LoanFactory;
import br.com.bank.app.model.LoanModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class PersonalLoan implements LoanFactory {
    private static final String TYPE = "personal";

    @Override
    public LoanModel getLoanModel() {
        return LoanModel
                .builder()
                .type(TYPE)
                .taxes(4)
                .build();
    }

    @Override
    public boolean isAvailableLoan(BigDecimal income, int age, String location) {
        return true;
    }
}
