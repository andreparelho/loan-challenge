package br.com.bank.app.validator.location.validate.sp;

import br.com.bank.app.validator.LoanValidator;
import br.com.bank.app.validator.location.rules.SpLocation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpLocationSalaryUntil5000Validator extends SpLocation implements LoanValidator {
    @Override
    public boolean validate(BigDecimal income, int age, String location) {
        return income.doubleValue() > 3000 && income.doubleValue() < 5000 && LOCATION.equalsIgnoreCase(location) && age >= MINIMUM_AGE;
    }
}
