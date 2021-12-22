package com.jasper.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTransactionSummary {

    private String prositionInformation;
    private BigDecimal posInfoNetAmount;
    private List<AccountTransaction> accountTransactionList;

}
