package br.com.bank.app.controller;

import br.com.bank.app.controller.dto.CustomerDTO;
import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.controller.dto.LoanResponse;
import br.com.bank.app.model.LoanModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoanControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private LoanRequest loanRequest;

    @BeforeEach
    public void setup() {
        loanRequest = new LoanRequest(CustomerDTO.builder()
                .name("Name")
                .cpf("690.243.518-98")
                .age(26)
                .location("SP")
                .income(BigDecimal.valueOf(7500))
                .build());
    }

    @Test
    @DisplayName("Deve testar o endpoint getLoans com sucesso")
    public void getLoansShouldReturnSuccess() {
        int taxes;
        String type;

        HttpEntity<LoanRequest> httpEntity = new HttpEntity<>(loanRequest);

        ResponseEntity<LoanResponse> response = this.testRestTemplate
                .exchange("/v1/loan", HttpMethod.POST, httpEntity, LoanResponse.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response);
        assertNotNull(Objects.requireNonNull(response.getBody()).loans());
        assertNotNull(response.getBody().customer());
        assertInstanceOf(LoanResponse.class, response.getBody());
        assertInstanceOf(String.class, response.getBody().customer());
        assertInstanceOf(List.class, response.getBody().loans());

        response.getBody().loans().forEach(loanModel -> {
            assertNotNull(loanModel.getType());
            assertInstanceOf(String.class, loanModel.getType());
            assertInstanceOf(Integer.class, loanModel.getTaxes());
        });


        var consignedList = response.getBody().loans().get(0);
        taxes = 2;
        type = "consigned";
        assertEquals(taxes, consignedList.getTaxes());
        assertEquals(type, consignedList.getType());

        var guaranteeList = response.getBody().loans().get(1);
        taxes = 3;
        type = "guarantee";
        assertEquals(taxes, guaranteeList.getTaxes());
        assertEquals(type, guaranteeList.getType());

        var personalList = response.getBody().loans().get(2);
        taxes = 4;
        type = "personal";
        assertEquals(taxes, personalList.getTaxes());
        assertEquals(type, personalList.getType());
    }
}