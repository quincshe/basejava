package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;


public abstract class AbstractArrayStorageTest extends AbstractStorageTest {


    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("UUID_" + i));
            }
        } catch (StorageException expected) {
            Assert.fail("Overflow ahead of time!!!");
        }
        storage.save(new Resume("UUID_" + AbstractArrayStorage.STORAGE_LIMIT));
    }

}