package com.ilyasidorov.orginformer.service;

import com.ilyasidorov.orginformer.model.Company;
import com.ilyasidorov.orginformer.model.Security;
import com.ilyasidorov.orginformer.repository.CompanyRepository;
import com.ilyasidorov.orginformer.util.JsonParser;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyService implements CompanyRepository {

    @Override
    public List<Company> getCompanyList(String jsonFilePath) throws IOException {
        return JsonParser.parseToList(jsonFilePath, Company.class);
    }

    @Override
    public Company findCompanyById(int id, List<Company> companies) {
        return companies.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public void displayShortNameAndFoundationDate(List<Company> companies, String foundationDatePattern) {
        for (Company company : companies) {
            System.out.println("Краткое название: " + company.getName() + " - Дата основания: " +
                    company.getFoundationDate().format(DateTimeFormatter.ofPattern(foundationDatePattern)));
        }
    }

    @Override
    public void displayExpiredSecurities(List<Company> companies) {
        List<Security> securities = companies.stream()
                .map(Company::getSecurities)
                .flatMap(Collection::stream)
                .filter(s -> s.getExpiryDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());

        System.out.println("Список просроченных ценных бумаг: ");
        for (Security security : securities) {
            System.out.println("Ценная бумага: код " + security.getCode() + ", дата истечения: " + security.getExpiryDate() + ", организация-владелец: " + findCompanyById(security.getOwnerId(), companies).getFullName());
        }
        System.out.println("Всего просрочено ценных бумаг: " + securities.size());

    }

    @Override
    public void displayCompaniesFoundedAfterDate(List<Company> companies, String date, String datePattern) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(datePattern));
        System.out.println("Компании, основанные после " + localDate + ":");
        companies.stream()
                .filter(c -> c.getFoundationDate().isAfter(localDate))
                .forEach(c -> System.out.println(c.getName() + ", дата основания: " + c.getFoundationDate()));
        System.out.println();
    }

    @Override
    public void displaySecuritiesByCurrency(List<Company> companies, String security) {
        System.out.println("Ценные бумаги в " + security + " : ");
        companies.stream()
                .map(Company::getSecurities)
                .flatMap(Collection::stream)
                .filter(s -> s.getCurrency().name().equals(security))
                .forEach(s -> System.out.println(s.getCode()));

    }
}
