package com.urise.webapp.storage;

import com.urise.webapp.storage.strategies.ObjectStrategy;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage("/home/quincshe/JAVAOPS/basejava/storages", new ObjectStrategy()));
    }
}