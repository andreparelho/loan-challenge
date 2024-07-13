package br.com.bank.app.factory;

import br.com.bank.app.controller.dto.CustomerDTO;
import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.model.LoanModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LoanEngineTest {
    @InjectMocks
    private LoanEngine loanEngine;

    @Mock
    private LoanFactory loanFactory;

    @BeforeEach
    public void setup() {
        List<LoanFactory> factoryList = List.of(loanFactory);
        loanEngine = new LoanEngine(factoryList);
    }

    @Test
    @DisplayName("Deve fazer o processamento com sucesso")
    public void testGetLoanListShouldReturnSuccess(){
        LoanRequest loanRequest = new LoanRequest(CustomerDTO
                .builder()
                .name("name")
                .cpf("690.243.518-98")
                .age(26)
                .location("SP")
                .income(BigDecimal.valueOf(6500))
                .build());

        LoanModel loanModel = LoanModel
                .builder()
                .taxes(2)
                .type("consigned")
                .build();

        when(loanFactory.getLoanModel()).thenReturn(loanModel);
        when(loanFactory.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation())).thenReturn(true);

        var loanList = loanEngine.getLoanList(loanRequest);

        assertNotNull(loanList);
        assertInstanceOf(List.class, loanList);

        verify(loanFactory, times(1)).isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());
        verify(loanFactory, times(1)).getLoanModel();
    }
}