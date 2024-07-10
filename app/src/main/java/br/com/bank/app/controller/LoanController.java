package br.com.bank.app.controller;

import br.com.bank.app.controller.dto.LoanRequest;
import br.com.bank.app.controller.dto.LoanResponse;
import br.com.bank.app.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/loan")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanResponse> getLoans(@RequestBody LoanRequest loanRequest) {
        var response = this.loanService.getLoans(loanRequest);
        return ResponseEntity.ok(response);
    }
}
