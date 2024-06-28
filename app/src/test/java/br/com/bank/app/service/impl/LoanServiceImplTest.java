package br.com.bank.app.service.impl;

import br.com.bank.app.controller.dto.CustomerDTO;
import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.controller.dto.LoanResponse;
import br.com.bank.app.factory.LoanEngine;
import br.com.bank.app.model.LoanModel;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LoanServiceImplTest {
    @InjectMocks
    private LoanServiceImpl loanService;
    @Mock
    private LoanEngine loanEngine;

    private LoanRequest loanRequest;
    private CustomerDTO customerDTO;
    private Faker faker;

    @BeforeEach
    public void init() {
        this.faker = new Faker(new Locale("pt-BR"));

        this.customerDTO = CustomerDTO
                .builder()
                .name(faker.name().firstName())
                .cpf("690.243.518-98")
                .age(26)
                .location("SP")
                .income(BigDecimal.valueOf(6500))
                .build();

        this.loanRequest = new LoanRequest(customerDTO);
    }

    @Test
    @DisplayName("Deve chamar corretamente a dependencia quando request estiver de acordo com contrato")
    public void testGetLoansShouldReturnSuccess(){
        List<LoanModel> listModels = createModels();

        when(this.loanEngine.getLoanList(loanRequest)).thenReturn(listModels);

        var response = this.loanService.getLoans(loanRequest);

        assertNotNull(response);
        assertInstanceOf(LoanResponse.class, response);

        verify(this.loanEngine, timeout(1)).getLoanList(loanRequest);
    }

    private List<LoanModel> createModels(){
        List<LoanModel> listModels = new ArrayList<>();

        List<String> types = new ArrayList<>();
        types.add("consigned");
        types.add("guarantee");
        types.add("personal");

        for (int i = 0; i < types.size(); i++) {
            LoanModel model = LoanModel
                    .builder()
                    .type(types.get(i))
                    .taxes(i+1)
                    .build();

            listModels.add(i, model);
        }

        return listModels;
    }
}