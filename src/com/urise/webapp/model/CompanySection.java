package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {
    private final List<Company> companyList;

    public CompanySection(String name) {
        super(name);
        companyList = new ArrayList<>();
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void addPeriod(String companyName, String website, Period period) {
        Company company = new Company(companyName, website);
        if (companyList.contains(company)) {
            for (Company c : companyList) {
                if (c.equals(company)) {
                    c.addPeriod(period);
                    break;
                }
            }

        } else {
            company.addPeriod(period);
            companyList.add(company);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompanySection that = (CompanySection) o;
        return Objects.equals(getName(),
            that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        companyList.forEach((value)->  builder
            .append(" - ")
            .append(value)
            .append("\n"));
        return builder.toString();
    }
}
