package br.com.bank.app.factory;

import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.model.LoanModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoanEngine {
    private final List<LoanFactory> factoryList;

    public LoanEngine(List<LoanFactory> factoryList) {
        this.factoryList = factoryList;
    }

    public List<LoanModel> getLoanList(LoanRequest loanRequest){
        Map<String, LoanModel> loanMap = new HashMap<>();

        for (LoanFactory loanFactory : factoryList){

            boolean loanOptional = loanFactory.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());
            if (loanOptional){
                loanMap.put(loanFactory.getLoanModel().getType(), loanFactory.getLoanModel());
            }
        }

       return loanMap.values().stream().toList();
    }
}
