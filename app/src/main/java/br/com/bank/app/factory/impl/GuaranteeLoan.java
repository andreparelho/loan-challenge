package br.com.bank.app.factory.impl;

import br.com.bank.app.factory.LoanFactory;
import br.com.bank.app.model.LoanModel;
import br.com.bank.app.validator.LoanValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class GuaranteeLoan implements LoanFactory {
    private static final String TYPE = "guarantee";
    private final List<LoanValidator> loanValidatorList;

    public GuaranteeLoan(List<LoanValidator> loanValidatorList) {
        this.loanValidatorList = loanValidatorList;
    }

    @Override
    public LoanModel getLoanModel() {
        return LoanModel
                .builder()
                .type(TYPE)
                .taxes(3)
                .build();
    }

    @Override
    public boolean isAvailableLoan(BigDecimal income, int age, String location) {
        Optional<LoanValidator> optional = this.loanValidatorList.stream().filter(loan -> loan.validate(income, age, location)).findFirst();
        return optional.isPresent();
    }
}
