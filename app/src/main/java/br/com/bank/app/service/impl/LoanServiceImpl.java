package br.com.bank.app.service.impl;

import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.controller.dto.LoanResponse;
import br.com.bank.app.factory.LoanFactory;
import br.com.bank.app.model.LoanModel;
import br.com.bank.app.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoanServiceImpl implements LoanService {
    private final List<LoanFactory> factoryList;

    public LoanServiceImpl(List<LoanFactory> factoryList) {
        this.factoryList = factoryList;
    }

    @Override
    public LoanResponse getLoans(LoanRequest loanRequest){
        Map<String, LoanModel> loanMap = new HashMap<>();

        for (LoanFactory loanFactory : factoryList){

            boolean loanOptional = loanFactory.isAvailableLoan(loanRequest.customerDTO().getIncome(), loanRequest.customerDTO().getAge(), loanRequest.customerDTO().getLocation());
            if (loanOptional){
                loanMap.put(loanFactory.getLoanModel().getType(), loanFactory.getLoanModel());
            }
        }

        List<LoanModel> loanModelList = loanMap.values().stream().toList();
        return new LoanResponse(loanRequest.customerDTO().getName(), loanModelList);
    }
}
