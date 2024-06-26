package br.com.bank.app.validator;

import java.math.BigDecimal;

public interface LoanValidator {
    boolean validate(BigDecimal income, int age, String location);
}
