package br.com.bank.app.controller.dto;

import br.com.bank.app.model.LoanModel;

import java.util.List;

public record LoanResponse (String customer, List<LoanModel> loans) {
}
