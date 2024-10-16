package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.*;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_3));
        storage.save(new Resume(UUID_2));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertNotEquals(3, storage.size());
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume expect = storage.get(UUID_3);
        storage.update(expect);
        Assert.assertEquals(expect, storage.get(UUID_3));
    }

    @Test
    public void save() {
        Resume expect = new Resume();
        String expectUuid = expect.getUuid();
        storage.save(expect);
        Assert.assertEquals(expect, storage.get(expectUuid));
    }

    @Test
    public void get() {
        Resume expect = new Resume();
        String expectUuid = expect.getUuid();
        storage.save(expect);
        Assert.assertEquals(expect, storage.get(expectUuid));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_3);
    }

    @Test
    public void getAll() {
        Resume[] allResume = storage.getAll();
        Assert.assertEquals(3, allResume.length);
        Assert.assertTrue(isExist(allResume, new Resume(UUID_1)));
        Assert.assertTrue(isExist(allResume, new Resume(UUID_2)));
        Assert.assertTrue(isExist(allResume, new Resume(UUID_3)));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveDoubleSave() {
        storage.save(new Resume(UUID_2));
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

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(new Resume().getUuid());
    }

    private boolean isExist(Resume[] array, Resume r) {
        for (Resume resume : array) {
            if (r.equals(resume)) {
                return true;
            }
        }
        return false;
    }
}