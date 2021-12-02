package com.jasper.report.service;

import com.jasper.report.dto.Car;
import com.jasper.report.dto.Country;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JasperMultipleDataSourcesService {

    public String generateMultipleDataSourceReport() throws JRException {

        String xmlFile = "src/main/resources/multipledatasources.jrxml";
        //String xmlFile = "D:\\proctise\\jasper\\springboot-jasper-report\\src\\main\\resources\\multipledatasources.jrxml";
        JasperReport jasperReport = JasperCompileManager.compileReport(xmlFile);
        List<Car> cars = new ArrayList<>();

        cars.add(new Car(1L, "Audi", 52642));
        cars.add(new Car(2L, "Mercedes", 57127));
        cars.add(new Car(3L, "Skoda", 9000));
        cars.add(new Car(4L, "Volvo", 29000));
        cars.add(new Car(5L, "Bentley", 350000));
        cars.add(new Car(6L, "Citroen", 21000));
        cars.add(new Car(7L, "Hummer", 41400));
        cars.add(new Car(8L, "Volkswagen", 21600));

        List<Country> countries = new ArrayList<Country>();

        countries.add(new Country(1L, "China", 1382050000));
        countries.add(new Country(2L, "India", 1313210000));
        countries.add(new Country(3L, "USA", 324666000));
        countries.add(new Country(4L, "Indonesia", 260581000));
        countries.add(new Country(5L, "Brazil", 207221000));
        countries.add(new Country(6L, "Pakistan", 196626000));
        countries.add(new Country(7L, "Nigeria", 186988000));
        countries.add(new Country(8L, "Bangladesh", 162099000));

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("datasource1", cars);
        params.put("datasource2", countries);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                params, new JREmptyDataSource());

        JasperExportManager.exportReportToPdfFile(jasperPrint,
                "src/main/resources/multipledatasources.pdf");
        return "Report Created sucussfully";
    }
}
