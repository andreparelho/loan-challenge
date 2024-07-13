package br.com.bank.app.validator.location.validate;

import br.com.bank.app.exception.request.AgeLessThanMinimumException;
import br.com.bank.app.validator.LoanValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.bank.app.util.LoanConstants.MINIMUM_AGE_MSG;

@Component
public class SalaryHigherThan5000Validator implements LoanValidator {
    public static final int AGE = 30;

    @Override
    public boolean validate(BigDecimal income, int age, String location) {
        if (age < MINIMUM_AGE){
            throw new AgeLessThanMinimumException(MINIMUM_AGE_MSG);
        }

        return income.doubleValue() >= 5000 && age < AGE;
    }
}
