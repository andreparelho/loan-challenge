package br.com.bank.app.factory.impl;

import br.com.bank.app.exception.request.AgeLessThanMinimumException;
import br.com.bank.app.factory.LoanFactory;
import br.com.bank.app.model.LoanModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.bank.app.util.LoanConstants.MINIMUM_AGE_MSG;

@Component
public class PersonalLoan implements LoanFactory {
    private static final String TYPE = "personal";
    private static final double MINIMUM_INCOME = 1000;

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
        if (age < MINIMUM_AGE){
            throw new AgeLessThanMinimumException(MINIMUM_AGE_MSG);
        }

        return income.doubleValue() >= MINIMUM_INCOME && age >= MINIMUM_AGE;
    }
}
