package br.com.bank.app.factory;

import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.model.LoanModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanEngine {
    private final List<LoanFactory> factoryList;

    public LoanEngine(List<LoanFactory> factoryList) {
        this.factoryList = factoryList;
    }

    public List<LoanModel> getLoanList(LoanRequest loanRequest){
        List<LoanModel> loanList = new ArrayList<>();

        factoryList.forEach(loanFactory -> {
            boolean loanOptional = loanFactory.isAvailableLoan(
                    loanRequest.customerDTO().getIncome(),
                    loanRequest.customerDTO().getAge(),
                    loanRequest.customerDTO().getLocation());

            if (loanOptional){
                loanList.add(loanFactory.getLoanModel());
            }
        });

       return loanList;
    }
}
