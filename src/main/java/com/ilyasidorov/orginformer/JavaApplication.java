package com.ilyasidorov.orginformer;

import com.ilyasidorov.orginformer.model.Company;
import com.ilyasidorov.orginformer.service.CompanyService;
import java.util.ArrayList;
import java.util.List;

public class JavaApplication {

    private static CompanyService service = new CompanyService();

    public static void main(String[] args) {
        List<Company> companies = new ArrayList<>();

        try {
            companies = service.getCompanyList("src/main/resources/companiesInfo.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

        service.displayShortNameAndFoundationDate(companies, "dd/MM/yyyy");
        service.displayExpiredSecurities(companies);
        service.displayCompaniesFoundedAfterDate(companies, "02.02.2010", "dd.MM.yyyy");
        service.displayCompaniesFoundedAfterDate(companies, "02.02,06", "dd.MM,yy");
        service.displayCompaniesFoundedAfterDate(companies, "01/01/2020", "dd/MM/yyyy");
        service.displayCompaniesFoundedAfterDate(companies, "01/01/18", "dd/MM/yy");
        service.displaySecuritiesByCurrency(companies, "RUB");
        service.displaySecuritiesByCurrency(companies, "USD");



    }
}
