package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private List<String> descriptionList;

    public ListSection() {
    }

    public ListSection(String name) {
        super(name);
        descriptionList = new ArrayList<>();
    }

    public List<String> getSectionList() {
        return descriptionList;
    }

    public void setDescriptionList(List<String> descriptionList) {
        this.descriptionList = descriptionList;
    }

    public void addDescription(String description) {
        descriptionList.add(description);
    }

    public void removeLast() {
        descriptionList.remove(descriptionList.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListSection that = (ListSection) o;
        return Objects.equals(descriptionList, that.descriptionList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(descriptionList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        descriptionList.forEach((value)->  builder
            .append(" - ")
            .append(value)
            .append("\n"));
        return builder.toString();
    }
}
