package com.jasper.report.controller;

import com.jasper.report.service.*;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class ReportController {

	private EmployeeReportService employeeReportService;
	private JasperMultipleDataSourcesService jasperMultipleDataSourcesService;
	private TransactionReportService transactionReportService;
	private HoldingInfoReportService holdingInfoReportService;
	private AccountTransactionService accountTransactionService;

	@GetMapping("employee/report")
	public String empReport() {
		return employeeReportService.generateReport();
	}

	@GetMapping("multi_ds_report")
	public String empMultipleDataSourceReport() throws JRException {
		return jasperMultipleDataSourcesService.generateMultipleDataSourceReport();
	}

	@GetMapping("transaction_report")
	public String transactionDataSourceReport() throws JRException {
		return transactionReportService.generateReport();
	}

	@GetMapping("holdingInfo_report")
	public String generateHoldingInfoReport() throws JRException {
		return holdingInfoReportService.generateReport();
	}

	@GetMapping("account_position")
	public String generateAccountPosition() throws JRException {
		return accountTransactionService.generateReport();
	}

}
