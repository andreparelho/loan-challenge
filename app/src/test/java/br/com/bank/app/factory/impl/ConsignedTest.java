package br.com.bank.app.factory.impl;

import br.com.bank.app.controller.dto.CustomerDTO;
import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.exception.request.AgeLessThanMinimumException;
import br.com.bank.app.model.LoanModel;
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
class ConsignedTest {
    @InjectMocks
    private Consigned consigned;

    @Test
    @DisplayName("Deve retornar um objeto do tipo Consigned")
    public void testGetLoanModelShouldReturnModel(){
        String type = "consigned";
        int taxes = 2;

        var loanModel = this.consigned.getLoanModel();

        assertNotNull(loanModel);
        assertEquals(type, loanModel.getType());
        assertEquals(taxes, loanModel.getTaxes());
        assertInstanceOf(LoanModel.class, loanModel);
        assertInstanceOf(String.class, loanModel.getType());
        assertInstanceOf(Integer.class, loanModel.getTaxes());
    }

    @Test
    @DisplayName("Deve retornar true quando os parametros estiverem corretos")
    public void testIsAvaiableLoanShouldReturnTrueWhenParametersIsValid(){
        LoanRequest loanRequest = new LoanRequest(CustomerDTO
                .builder()
                .name("name")
                .cpf("690.243.518-98")
                .age(26)
                .location("SP")
                .income(BigDecimal.valueOf(6500))
                .build());

        var availableLoan = this.consigned.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());

        assertTrue(availableLoan);
    }

    @Test
    @DisplayName("Deve retornar false quando os parametros estiverem errados")
    public void testIsAvaiableLoanShouldReturnFalseWhenParametersIsInvalid(){
        LoanRequest loanRequest = new LoanRequest(CustomerDTO
                .builder()
                .name("name")
                .cpf("690.243.518-98")
                .age(26)
                .location("SP")
                .income(BigDecimal.valueOf(3000))
                .build());

        var availableLoan = this.consigned.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());

        assertFalse(availableLoan);
    }

    @ParameterizedTest()
    @DisplayName("Deve lancar uma exception quando os a idade for menor que 18")
    @CsvSource({"2500, 14, MG", "5000, 17, SP", "1000, 16, RJ"})
    public void testValidateShouldReturnExceptionWhenSendInvalidAge(BigDecimal income, int age, String location){
        Throwable exceptionMethod = assertThrows(Throwable.class, () -> {
            this.consigned.isAvailableLoan(income, age, location);
        });

        assertThrows(AgeLessThanMinimumException.class, () -> this.consigned.isAvailableLoan(income, age, location));
        assertEquals("your age is not allowed", exceptionMethod.getMessage());
    }
}