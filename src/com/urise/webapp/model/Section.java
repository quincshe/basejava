package com.urise.webapp.model;

import java.io.Serializable;

public abstract class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    protected Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
