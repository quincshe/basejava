package com.urise.webapp.storage;

import com.urise.webapp.storage.startegies.XmlStrategy;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage("/home/quincshe/JAVAOPS/basejava/storages", new XmlStrategy()));
    }
}