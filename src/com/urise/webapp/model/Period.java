package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period implements Comparable<Period>{

    private final LocalDate begin;
    private final LocalDate end;
    private final String position;
    private final String description;

    public Period(LocalDate begin, LocalDate end, String position, String description) {
        this.begin = begin;
        this.end = end;
        this.position = position;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }

    public LocalDate getEnd() {
        return end;
    }

    public LocalDate getBegin() {
        return begin;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Period period = (Period) o;
        return Objects.equals(begin, period.begin) && Objects.equals(end,
            period.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }

    @Override
    public String toString() {
        return "\n" +
            begin + "/"+
            end +
            ", position='" + position + '\'' +
            ", description='" + description + '\'';
    }

    @Override
    public int compareTo(Period period) {
        int compare =-end.compareTo(period.end);
        return compare != 0 ? compare : begin.compareTo(period.begin);
    }
}
