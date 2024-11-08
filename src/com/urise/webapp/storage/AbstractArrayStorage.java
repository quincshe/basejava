package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> getCopyList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage[ searchKey] = r;
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", r.getUuid());
        } else {
            insertResume(searchKey, r);
            size++;
        }
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doDelete(Integer searchKey) {
        extractResume((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return (int) searchKey >= 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(int index, Resume r);

    protected abstract void extractResume(int index);
}
