package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
    ArrayStorageTest.class,
    ListStorageTest.class,
    MapUuidStorageTest.class,
    SortedArrayStorageTest.class,
    MapResumeStorageTest.class,
    ObjectFileStorageTest.class,
    ObjectPathStorageTest.class,
    XmlPathStorageTest.class,
    JsonFileStorageTest.class,
    DataPathStorageTest.class
})
public class AllStorageTest {

}
