package br.com.bank.app.factory.impl;

import br.com.bank.app.controller.dto.CustomerDTO;
import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.model.LoanModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PersonalLoanTest {

    @InjectMocks
    private PersonalLoan personalLoan;

    @Test
    @DisplayName("Deve retornar um objeto do tipo PersonalLoan")
    public void testGetLoanModelShouldReturnModel(){
        String type = "personal";
        int taxes = 4;

        var loanModel = this.personalLoan.getLoanModel();

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

        var availableLoan = this.personalLoan.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());

        assertTrue(availableLoan);
    }

    @Test
    @DisplayName("Deve retornar false quando os parametros estiverem errados")
    public void testIsAvaiableLoanShouldReturnFalseWhenParametersIsValid(){
        LoanRequest loanRequest = new LoanRequest(CustomerDTO
                .builder()
                .name("name")
                .cpf("690.243.518-98")
                .age(26)
                .location("SP")
                .income(BigDecimal.valueOf(900))
                .build());

        var availableLoan = this.personalLoan.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());

        assertFalse(availableLoan);
    }
}