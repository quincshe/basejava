package com.urise.webapp.storage;

import com.urise.webapp.storage.strategies.XmlStrategy;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGES_PATH, new XmlStrategy()));
    }
}