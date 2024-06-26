package br.com.bank.app.validator.impl;

import br.com.bank.app.validator.LoanValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SalaryUntil5000Validator implements LoanValidator {
    @Override
    public boolean validate(BigDecimal income, int age, String location) {
        return income.doubleValue() > 3000 && income.doubleValue() < 4000 && LOCATION.equalsIgnoreCase(location);
    }
}
