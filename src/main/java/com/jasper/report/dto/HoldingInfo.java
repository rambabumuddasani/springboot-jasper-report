package com.jasper.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingInfo {
    private String holdingInformation;
    private int deliverCount;
    private int deliverNominalAmount;
    private int receiveCount;
    private int receiveNominalAmount;
}
