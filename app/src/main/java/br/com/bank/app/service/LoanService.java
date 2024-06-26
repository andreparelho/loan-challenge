package br.com.bank.app.service;

import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.controller.dto.LoanResponse;

public interface LoanService {
    LoanResponse getLoans(LoanRequest loanRequest);
}
