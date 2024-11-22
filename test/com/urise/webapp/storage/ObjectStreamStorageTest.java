package com.urise.webapp.storage;

import java.io.File;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(new File("/home/quincshe/JAVAOPS/basejava/storages")));
    }
}