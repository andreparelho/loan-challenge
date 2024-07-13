package br.com.bank.app.factory.impl;

import br.com.bank.app.controller.dto.CustomerDTO;
import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.model.LoanModel;
import br.com.bank.app.validator.LoanValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GuaranteeLoanTest {
    @InjectMocks
    private GuaranteeLoan guaranteeLoan;

    @Mock
    private LoanValidator loanValidator;

    @BeforeEach
    public void setup(){
        List<LoanValidator> loanValidatorList = Arrays.asList(loanValidator);
        this.guaranteeLoan = new GuaranteeLoan(loanValidatorList);
    }

    @Test
    @DisplayName("Deve retornar um objeto do tipo GuaranteeLoan")
    public void testGetLoanModelShouldReturnModel(){
        String type = "guarantee";
        int taxes = 3;

        var loanModel = this.guaranteeLoan.getLoanModel();

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

        when(this.loanValidator.validate(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation())).thenReturn(true);

        var availableLoan = this.guaranteeLoan.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());

        assertTrue(availableLoan);

        verify(this.loanValidator, times(1)).validate(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());
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
                .income(BigDecimal.valueOf(6500))
                .build());

        when(this.loanValidator.validate(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation())).thenReturn(false);

        var availableLoan = this.guaranteeLoan.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());

        assertFalse(availableLoan);

        verify(this.loanValidator, times(1)).validate(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());
    }
}