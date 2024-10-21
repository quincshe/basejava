package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int getIndex(Resume r) {
        return getIndex(r.getUuid());
    }

    @Override
    protected void resaveResume(Resume r) {
        storage[getIndex(r)] = r;
    }

    @Override
    protected void addResume(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", r.getUuid());
        } else {
            insertResume(getIndex(r), r);
            size++;
        }
    }

    @Override
    protected Resume getResumeByIndex(int index) {
        return storage[index];
    }

    @Override
    protected void removeResume(int index) {
        extractResume(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(int index, Resume r);

    protected abstract void extractResume(int index);
}
