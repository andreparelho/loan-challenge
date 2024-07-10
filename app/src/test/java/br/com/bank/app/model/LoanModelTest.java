package br.com.bank.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class LoanModelTest {

    @ParameterizedTest
    @DisplayName("Deve criar e validar um objeto para cada tipo de Loan")
    @CsvSource({"consigned, 2", "guarantee, 3", "personal, 4"})
    public void testCreateLoanModelShouldReturnModel(String type, int taxes){
        LoanModel loanModel = LoanModel.builder().build();
        loanModel.setType(type);
        loanModel.setTaxes(taxes);

        assertNotNull(loanModel);
        assertEquals(type, loanModel.getType());
        assertEquals(taxes, loanModel.getTaxes());
        assertInstanceOf(LoanModel.class, loanModel);
        assertInstanceOf(String.class, loanModel.getType());
        assertInstanceOf(Integer.class, loanModel.getTaxes());
    }
}