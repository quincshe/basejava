package com.urise.webapp.storage;

import com.urise.webapp.storage.strategies.JsonStrategy;

public class JsonFileStorageTest extends AbstractStorageTest {

    public JsonFileStorageTest() {
        super(new FileStorage("/home/quincshe/JAVAOPS/basejava/storages", new JsonStrategy()));
    }
}