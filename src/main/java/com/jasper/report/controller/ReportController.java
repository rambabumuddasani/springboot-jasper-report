package com.jasper.report.controller;

import com.jasper.report.service.EmployeeReportService;
import com.jasper.report.service.JasperMultipleDataSourcesService;
import com.jasper.report.service.TransactionReportService;
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
}
