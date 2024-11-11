package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Company {

    private final String name;
    private final Set<Period> periods;
    private final String website;

    public Company(String name, String website) {
        this.name = name;
        periods = new TreeSet<>();
        this.website = website;
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

    public void addPeriod(Period period) {
        periods.add(period);

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
        return "Company: " + name + '\'' +
            periods +
            ", \nwebsite='" + website + '\'' +
            '}';
    }
}
