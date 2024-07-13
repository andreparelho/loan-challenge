package br.com.bank.app.validator.location.validate;

import br.com.bank.app.exception.request.AgeLessThanMinimumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class SalaryHigherThan5000ValidatorTest {
    @InjectMocks
    private SalaryHigherThan5000Validator salaryHigherThan5000Validator;

    @ParameterizedTest()
    @DisplayName("Deve validar como true quando os parametros forem corretos")
    @CsvSource({"7500, 25, BA", "8000, 20, RJ", "5000, 29, SC"})
    public void testValidateShouldReturnTrueWhenSendValidParams(BigDecimal income, int age, String location){
        var validation = this.salaryHigherThan5000Validator.validate(income, age, location);

        assertTrue(validation);
    }

    @ParameterizedTest()
    @DisplayName("Deve validar como false quando os parametros forem errados")
    @CsvSource({"7500, 35, MG", "5000, 45, SP", "1000, 29, RJ"})
    public void testValidateShouldReturnFalseWhenSendInvalidParams(BigDecimal income, int age, String location){
        var validation = this.salaryHigherThan5000Validator.validate(income, age, location);

        assertFalse(validation);
    }

    @ParameterizedTest()
    @DisplayName("Deve lancar uma exception quando os a idade for menor que 18")
    @CsvSource({"2500, 14, MG", "5000, 17, SP", "1000, 16, RJ"})
    public void testValidateShouldReturnExceptionWhenSendInvalidAge(BigDecimal income, int age, String location){
        Throwable exceptionMethod = assertThrows(Throwable.class, () -> {
            this.salaryHigherThan5000Validator.validate(income, age, location);
        });

        assertThrows(AgeLessThanMinimumException.class, () -> this.salaryHigherThan5000Validator.validate(income, age, location));
        assertEquals("your age is not allowed", exceptionMethod.getMessage());
    }
}