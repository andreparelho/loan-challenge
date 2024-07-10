package br.com.bank.app.validator.location.validate.sp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class SpLocationSalaryLowerThan3000ValidatorTest {
    @InjectMocks
    private SpLocationSalaryLowerThan3000Validator spLocationSalaryLowerThan3000Validator;

    @ParameterizedTest()
    @DisplayName("Deve validar como true quando os parametros forem corretos")
    @CsvSource({"2500, 25, SP", "3000, 20, SP", "1000, 29, SP"})
    public void testValidateShouldReturnTrueWhenSendValidParams(BigDecimal income, int age, String location){
        var validation = this.spLocationSalaryLowerThan3000Validator.validate(income, age, location);

        assertTrue(validation);
    }

    @ParameterizedTest()
    @DisplayName("Deve validar como false quando os parametros forem errados")
    @CsvSource({"2500, 25, MG", "5000, 20, SP", "1000, 29, RJ"})
    public void testValidateShouldReturnFalseWhenSendValidParams(BigDecimal income, int age, String location){
        var validation = this.spLocationSalaryLowerThan3000Validator.validate(income, age, location);

        assertFalse(validation);
    }
}