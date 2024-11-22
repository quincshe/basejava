package com.urise.webapp.storage;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage("/home/quincshe/JAVAOPS/basejava/storages"));
    }
}