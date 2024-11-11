package com.urise.webapp.model;

public abstract class Section {

    private final String name;

    protected Section(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
