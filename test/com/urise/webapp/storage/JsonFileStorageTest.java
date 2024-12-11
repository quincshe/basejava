package com.urise.webapp.storage;

import com.urise.webapp.storage.strategies.JsonStrategy;

public class JsonFileStorageTest extends AbstractStorageTest {

    public JsonFileStorageTest() {
        super(new FileStorage(STORAGES_PATH, new JsonStrategy()));
    }
}