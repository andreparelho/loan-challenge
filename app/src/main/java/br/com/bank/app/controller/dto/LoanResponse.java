package br.com.bank.app.controller.dto;

import br.com.bank.app.model.LoanModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record LoanResponse (@JsonProperty("customer") String customer, @JsonProperty("loans") List<LoanModel> loans) {
}
