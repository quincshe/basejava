package com.urise.webapp.storage;

import com.urise.webapp.storage.strategies.ObjectStrategy;

public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage("/home/quincshe/JAVAOPS/basejava/storages", new ObjectStrategy()));
    }
}