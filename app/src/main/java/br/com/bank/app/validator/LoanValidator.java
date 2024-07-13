package br.com.bank.app.validator;

import java.math.BigDecimal;

public interface LoanValidator {
    int MINIMUM_AGE = 18;
    boolean validate(BigDecimal income, int age, String location);
}
