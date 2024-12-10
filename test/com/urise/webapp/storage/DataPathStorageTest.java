package com.urise.webapp.storage;

import com.urise.webapp.storage.strategies.DataStrategy;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage("/home/quincshe/JAVAOPS/basejava/storages", new DataStrategy()));
    }
}