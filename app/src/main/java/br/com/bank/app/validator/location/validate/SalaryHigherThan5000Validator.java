package br.com.bank.app.validator.location.validate;

import br.com.bank.app.validator.LoanValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SalaryHigherThan5000Validator implements LoanValidator {
    public static final int AGE = 30;

    @Override
    public boolean validate(BigDecimal income, int age, String location) {
        return income.doubleValue() >= 5000 && age >= MINIMUM_AGE && age < AGE;
    }
}
