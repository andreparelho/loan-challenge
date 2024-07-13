package br.com.bank.app.service.impl;

import br.com.bank.app.controller.dto.CustomerDTO;
import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.controller.dto.LoanResponse;
import br.com.bank.app.exception.request.NameIsBlankRequestException;
import br.com.bank.app.factory.LoanEngine;
import br.com.bank.app.model.LoanModel;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.*;

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
                .cpf("123.456.789-10")
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
        assertTrue(response.loans().stream().anyMatch(loan -> "consigned".equalsIgnoreCase(loan.getType())));
        assertTrue(response.loans().stream().anyMatch(loan -> "guarantee".equalsIgnoreCase(loan.getType())));
        assertTrue(response.loans().stream().anyMatch(loan -> "personal".equalsIgnoreCase(loan.getType())));

        verify(this.loanEngine, times(1)).getLoanList(loanRequest);
    }

    @ParameterizedTest()
    @DisplayName("Deve lancar uma exception quando os a idade for menor que 18")
    @CsvSource({"2500, 14, MG", "5000, 17, SP", "1000, 16, RJ"})
    public void testValidateShouldReturnExceptionWhenSendInvalidAge(BigDecimal income, int age, String location){
        this.customerDTO = CustomerDTO
                .builder()
                .name("")
                .cpf("123.456.789-10")
                .age(age)
                .location(location)
                .income(income)
                .build();

        this.loanRequest = new LoanRequest(customerDTO);

        Throwable exceptionMethod = assertThrows(Throwable.class, () -> {
            this.loanService.getLoans(loanRequest);
        });

        assertThrows(NameIsBlankRequestException.class, () -> this.loanService.getLoans(loanRequest));
        assertEquals("name parameters is blank", exceptionMethod.getMessage());
    }

    private List<LoanModel> createModels(){
        List<LoanModel> listModels = new ArrayList<>();

        Map<String, Integer> typesAndTaxesMap = new HashMap<>();
        typesAndTaxesMap.put("consigned", 2);
        typesAndTaxesMap.put("guarantee", 3);
        typesAndTaxesMap.put("personal", 4);

        for (Map.Entry<String, Integer> loan : typesAndTaxesMap.entrySet()){
            LoanModel model = LoanModel
                    .builder()
                    .type(loan.getKey())
                    .taxes(loan.getValue())
                    .build();

            listModels.add(model);
        }

        return listModels;
    }
}