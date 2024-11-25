package com.urise.webapp.storage;

import com.urise.webapp.storage.startegies.ObjectStrategy;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage("/home/quincshe/JAVAOPS/basejava/storages", new ObjectStrategy()));
    }
}