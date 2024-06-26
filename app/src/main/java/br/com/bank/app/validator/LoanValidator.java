package br.com.bank.app.validator;

import java.math.BigDecimal;

public interface LoanValidator {
    static final String LOCATION = "SP";
    static final int AGE = 30;
    boolean validate(BigDecimal income, int age, String location);
}
