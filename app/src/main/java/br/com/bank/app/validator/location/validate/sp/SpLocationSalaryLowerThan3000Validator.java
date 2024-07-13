package br.com.bank.app.validator.location.validate.sp;

import br.com.bank.app.exception.request.AgeLessThanMinimumException;
import br.com.bank.app.validator.LoanValidator;

import br.com.bank.app.validator.location.rules.SpLocation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.bank.app.util.LoanConstants.MINIMUM_AGE_MSG;

@Component
public class SpLocationSalaryLowerThan3000Validator extends SpLocation implements LoanValidator {
    @Override
    public boolean validate(BigDecimal income, int age, String location) {
        if (age < MINIMUM_AGE){
            throw new AgeLessThanMinimumException(MINIMUM_AGE_MSG);
        }

        return income.doubleValue() <= 3000 && age < AGE && LOCATION.equalsIgnoreCase(location);
    }
}
