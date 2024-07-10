package br.com.bank.app.controller.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CustomerDTOTest {

    @Test
    @DisplayName("Deve criar e validar um Customer DTO")
    public void testCreateCustomerDTOShouldReturnCorrectDTO(){
        CustomerDTO customerDTO = new CustomerDTO("name", "123.456.789-10", 20, "SP", BigDecimal.valueOf(7500));
        customerDTO.setName("name");
        customerDTO.setCpf("123.456.789-10");
        customerDTO.setAge(20);
        customerDTO.setLocation("SP");
        customerDTO.setIncome(BigDecimal.valueOf(7500));

        assertNotNull(customerDTO);
        assertInstanceOf(CustomerDTO.class, customerDTO);
        assertInstanceOf(String.class, customerDTO.getName());
        assertInstanceOf(String.class, customerDTO.getCpf());
        assertInstanceOf(Integer.class, customerDTO.getAge());
        assertInstanceOf(String.class, customerDTO.getLocation());
        assertInstanceOf(BigDecimal.class, customerDTO.getIncome());
    }

}