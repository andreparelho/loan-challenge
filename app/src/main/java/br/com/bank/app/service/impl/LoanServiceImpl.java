package br.com.bank.app.service.impl;

import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.controller.dto.LoanResponse;
import br.com.bank.app.factory.LoanEngine;
import br.com.bank.app.factory.LoanFactory;
import br.com.bank.app.model.LoanModel;
import br.com.bank.app.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanEngine loanEngine;

    public LoanServiceImpl(LoanEngine loanEngine) {
        this.loanEngine = loanEngine;
    }

    @Override
    public LoanResponse getLoans(LoanRequest loanRequest){
        List<LoanModel> loans = this.loanEngine.getLoanList(loanRequest);
        return new LoanResponse(loanRequest.customerDTO().getName(), loans);
    }
}
