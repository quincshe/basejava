package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class CompanySection extends Section {

    private Set<Company> companySet;

    public CompanySection() {
    }

    public CompanySection(String name) {
        super(name);
        companySet = new TreeSet<>();
    }

    public List<Company> getCompanyList() {
        return new ArrayList<>(companySet);
    }

    public void setCompanySet(Set<Company> companySet) {
        this.companySet = companySet;
    }

    public void addPeriod(String companyName, String website, Period period) {
        for (Company c : companySet) {
            if (c.getName().equals(companyName)) {
                c.addPeriod(period);
                return;
            }
        }
        Company company = new Company(companyName, website);
        company.addPeriod(period);
        companySet.add(company);
    }

    public int size(){
        int count = 0;
        for (Company company:companySet){
            count+= company.getPeriods().size();
        }
        return count;
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
        companySet.forEach((value) -> builder
            .append(" - ")
            .append(value)
            .append("\n"));
        return builder.toString();
    }
}
