package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Company
    implements Comparable<Company>, Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final Set<Period> periods;
    private final String website;
    private LocalDate lastDate;

    public Company(String name, String website) {
        this.name = name;
        periods = new TreeSet<>();
        this.website = website;
        lastDate = LocalDate.MIN;
    }

    public String getName() {
        return name;
    }

    public List<Period> getPeriods() {
        return new ArrayList<>(periods);
    }

    public String getWebsite() {
        return website;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void addPeriod(Period period) {
        periods.add(period);
        if (lastDate.isBefore(period.getEnd())) {
            lastDate = period.getEnd();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        String companyName = ((Company) o).getName();
        return name.equals(companyName);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name + '\'' +
            periods +
            ", \nwebsite='" + website + '\'' +
            '}';
    }

    @Override
    public int compareTo(Company company) {
        return -lastDate.compareTo(company.getLastDate());
    }
}
