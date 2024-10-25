package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {

    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    protected static final String FULLNAME_1 = "Petrov";
    private static final String FULLNAME_2 = "Ivanov";
    private static final String FULLNAME_3 = "Ivanov";
    protected static final String FULLNAME_4 = "Sidorov";
    public static final Resume RESUME_1 = new Resume(UUID_1, FULLNAME_1);
    public static final Resume RESUME_2 = new Resume(UUID_2, FULLNAME_2);
    public static final Resume RESUME_3 = new Resume(UUID_3, FULLNAME_3);
    public static final Resume RESUME_4 = new Resume(UUID_4, FULLNAME_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume expect = storage.get(UUID_3);
        storage.update(expect);
        Assert.assertEquals(expect, storage.get(UUID_3));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        assertSize(2);
        storage.get(UUID_3);
    }

    @Test
    public void getAllSorted() {
        List<Resume> allResume = storage.getAllSorted();
        Assert.assertEquals(3, allResume.size());
        List<Resume> expected = Arrays.asList(RESUME_2, RESUME_3, RESUME_1);
        Assert.assertEquals(expected, allResume);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveDoubleSave() {
        storage.save(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(new Resume().getUuid());
    }

    protected void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    protected void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}
