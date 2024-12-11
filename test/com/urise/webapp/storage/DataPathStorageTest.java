package com.urise.webapp.storage;

import com.urise.webapp.storage.strategies.DataStrategy;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGES_PATH, new DataStrategy()));
    }
}