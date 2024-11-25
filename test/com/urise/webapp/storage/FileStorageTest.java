package com.urise.webapp.storage;

import com.urise.webapp.storage.startegies.ObjectStrategy;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage("/home/quincshe/JAVAOPS/basejava/storages", new ObjectStrategy()));
    }
}