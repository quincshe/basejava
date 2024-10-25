package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapFullNameStorageTest extends AbstractStorageTest{

    public MapFullNameStorageTest() {
        super(new MapFullNameStorage());
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_4);
    }

    @Test
    public void update() {
        Resume expect = storage.get(FULLNAME_1);
        storage.update(expect);
        Assert.assertEquals(expect, storage.get(FULLNAME_1));
    }

    @Test
    public void getAllSorted() {
        List<Resume> allResume = storage.getAllSorted();
        Assert.assertEquals(3, allResume.size());
        List<Resume> expected = Arrays.asList(RESUME_2, RESUME_1, RESUME_4);
        Assert.assertEquals(expected, allResume);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_4);
    }

    @Test
    public void save() {
        Resume resume_5 = new Resume();
        resume_5.setFullName("Pupkin");
        storage.save(resume_5);
        assertGet(resume_5);
        assertSize(4);
    }

    protected void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getFullName()));
    }
}