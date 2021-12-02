package com.jasper.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
    private String title;
    private String transactionType;
    private int incomingCount;
    private int incomingSettlementAmount;
    private int outgoingCount;
    private int outgoingSettlementAmount;
    private int netAmount;
}
