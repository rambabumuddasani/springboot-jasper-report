package com.jasper.report.service;

import com.jasper.report.dto.Employee;
import com.jasper.report.dto.TransactionDetails;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionReportService {

    private List<TransactionDetails> transactionDetailsList = Arrays.asList(
            new TransactionDetails("Transactions Settled Today","Allotment",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Delivery ersus Payment(DvP)",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Free of Payment",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","House Transfers",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Interbank Repo(RPC)",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Interbank Repo(RPO)",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Intraday Liquidity Faility(ILC)",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Intraday Liquidity Facility(ILO)",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Redemption",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Standing Facility(SFC)",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","Standing Facility(SFO)",1,2,3,4,5),
            new TransactionDetails("Transactions Settled Today","When Issued",1,2,3,4,5),

            new TransactionDetails("Queued - Insuffiient Secuirties","",1,2,3,4,5),
            new TransactionDetails("Waiting For Payments","",1,2,3,4,5),
            new TransactionDetails("Transation Waiting for Settlement Date/Time","",1,2,3,4,5),
            new TransactionDetails("Unmatched","",1,2,3,4,5),
            new TransactionDetails("Cancelled","",1,2,3,4,5)
    );

    public String generateReport() {
        try {
            String reportPath = "D:\\proctise\\jasper\\springboot-jasper-report\\src\\main\\resources";
  //          transactionDetailsList.sort(Comparator.comparing(TransactionDetails::getTitle));
            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\transaction_groupby.jrxml");
            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(transactionDetailsList);
            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "ram");
            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Transaction-Rpt.pdf");
            System.out.println("Done");
            return "Report successfully generated @path= " + reportPath;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
