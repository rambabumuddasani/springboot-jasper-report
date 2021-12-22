package com.jasper.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTransaction {

    private String transactionType;
    private long debitCount;
    private BigDecimal debitAmount;
    private long creditCount;
    private BigDecimal creditAmount;
    private BigDecimal netAmount;

}

