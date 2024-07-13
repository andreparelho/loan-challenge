package br.com.bank.app.service.impl;

import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.controller.dto.LoanResponse;
import br.com.bank.app.exception.request.NameIsBlankRequestException;
import br.com.bank.app.factory.LoanEngine;
import br.com.bank.app.model.LoanModel;
import br.com.bank.app.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.*;

import static br.com.bank.app.util.LoanConstants.*;


@Service
public class LoanServiceImpl implements LoanService {
    private final LoanEngine loanEngine;

    public LoanServiceImpl(LoanEngine loanEngine) {
        this.loanEngine = loanEngine;
    }

    @Override
    public LoanResponse getLoans(LoanRequest loanRequest){
        if (loanRequest.customerDTO().getName().isBlank() || loanRequest.customerDTO().getName() == null){
            throw new NameIsBlankRequestException(NAME_NOT_BLANK_MSG);
        }

        List<LoanModel> loans = this.loanEngine.getLoanList(loanRequest);
        return new LoanResponse(loanRequest.customerDTO().getName(), loans);
    }
}
