package br.com.bank.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoanRequest (@JsonProperty("customer") CustomerDTO customerDTO) {
}
