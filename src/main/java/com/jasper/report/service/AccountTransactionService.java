package com.jasper.report.service;

import com.jasper.report.dto.AccountTransaction;
import com.jasper.report.dto.AccountTransactionSummary;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountTransactionService {

    private static List<AccountTransactionSummary> accountTransactionSummaryList= new ArrayList<>();

    static {
        List<AccountTransaction> collect1 = Stream.of(getAccountTransaction("100", 1, "100", 1, "Intraday Liquidity Facility"),
                        getAccountTransaction("100", 1, "100", 1, "Account Transfer"))
                .collect(Collectors.toList());
        AccountTransactionSummary accountTransactionSummary1=  AccountTransactionSummary.builder()
                .prositionInformation("Opening Balance")
                .posInfoNetAmount(new BigDecimal("100"))
                .accountTransactionList(collect1)
                .build();

        List<AccountTransaction> collect = Stream.of(getAccountTransaction("100", 1, "100", 1, "Earmark(Net Sett)"),
                getAccountTransaction("100", 1, "100", 1, "Earmark(SSS Multilateral Sett)"),
                getAccountTransaction("100", 1, "100", 1, "Canceled by System"),
                getAccountTransaction("100", 1, "100", 1, "Canceled by Participent"),
                getAccountTransaction("100", 1, "100", 1, "Rejected"),
                getAccountTransaction("100", 1, "100", 1, "Feature Value Transaction Sent Today"))
                .collect(Collectors.toList());
        AccountTransactionSummary accountTransactionSummary=  AccountTransactionSummary.builder()
                .prositionInformation("Projected Balance")
                .posInfoNetAmount(new BigDecimal("100"))
                .accountTransactionList(collect)
                .build();

        accountTransactionSummaryList.add(accountTransactionSummary1);
        accountTransactionSummaryList.add(accountTransactionSummary);

    }

    private static AccountTransaction getAccountTransaction(String creditAmt,long creditCount,String debitAmt,long debitCount,String transactionType) {
        return AccountTransaction.builder()
                .creditAmount(new BigDecimal(creditAmt))
                .debitAmount(new BigDecimal(debitAmt))
                .transactionType(transactionType)
                .debitCount(debitCount)
                .creditCount(creditCount)
                .netAmount(new BigDecimal(creditAmt))
                .build();
    }

    public String generateReport() {
        try {
            String reportPath = "D:\\proctise\\jasper\\springboot-jasper-report\\src\\main\\resources";
  //          transactionDetailsList.sort(Comparator.comparing(TransactionDetails::getTitle));
            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\account_position_with_list.jrxml");
            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(accountTransactionSummaryList);
            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CollectionBeanParam", jrBeanCollectionDataSource);
            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
 //           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\account_position_with_list.pdf");
            System.out.println("Done");
            return "Report successfully generated @path= " + reportPath;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
