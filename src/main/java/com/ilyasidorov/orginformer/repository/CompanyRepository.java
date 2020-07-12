package com.ilyasidorov.orginformer.repository;

import com.ilyasidorov.orginformer.model.Company;

import java.io.IOException;
import java.util.List;

public interface CompanyRepository {

    List<Company> getCompanyList(String jsonFilePath) throws IOException;
    void displayShortNameAndFoundationDate(List<Company> companies, String foundationDatePattern);
    void displayExpiredSecurities(List<Company> companies);
    Company findCompanyById(int id, List<Company> companies);
    void displayCompaniesFoundedAfterDate(List<Company> companies, String date, String datePattern);
    void displaySecuritiesByCurrency(List<Company> companies, String currency);
}
