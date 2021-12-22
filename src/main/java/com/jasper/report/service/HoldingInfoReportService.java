package com.jasper.report.service;

import com.jasper.report.dto.HoldingInfo;
import com.jasper.report.dto.TransactionDetails;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HoldingInfoReportService {

    private List<HoldingInfo> holdingInfoList = Arrays.asList(
            new HoldingInfo("Opening Nominal Amount",1,2,3,4),
            new HoldingInfo("Net Changes",1,2,3,4),
            new HoldingInfo("Current Nominal Amount",1,2,3,4),
            new HoldingInfo("Earmarked",1,2,3,4),
            new HoldingInfo("Available Nominal Amount",1,2,3,4),
            new HoldingInfo("Queued due to Insufficient Funds",1,2,3,4),
            new HoldingInfo("Transaction Waiting for Settlement Date/Time",1,2,3,4),
            new HoldingInfo("Projected Nominal Amount",1,2,3,4)
    );

    public String generateReport() {
        try {
            String reportPath = "D:\\proctise\\jasper\\springboot-jasper-report\\src\\main\\resources";
  //          transactionDetailsList.sort(Comparator.comparing(TransactionDetails::getTitle));
            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\holdingInfo.jrxml");
            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(holdingInfoList);
            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CollectionBeanParam", jrBeanCollectionDataSource);
            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
 //           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\holdingInfo- Rpt.pdf");
            System.out.println("Done");
            return "Report successfully generated @path= " + reportPath;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
