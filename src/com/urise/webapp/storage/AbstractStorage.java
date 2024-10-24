package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object searchKey = getIndex(r);
        getNotExistingSearchKey(searchKey, r.getUuid());
        resaveResume(r);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getIndex(r);
        getExistingSearchKey(searchKey, r.getUuid());
        addResume(r);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getIndex(new Resume(uuid));
        getNotExistingSearchKey(searchKey, uuid);
        return getResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getIndex(new Resume(uuid));
        getNotExistingSearchKey(searchKey, uuid);
        removeResume(uuid);
    }

    protected abstract int getIndex(Resume r);

    protected abstract void resaveResume(Resume r);

    protected abstract void addResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void removeResume(String uuid);

    private void getExistingSearchKey(Object searchKey, String uuid) {
        if ((int) searchKey >= 0) {
            throw new ExistStorageException(uuid);
        }
    }

    private void getNotExistingSearchKey(Object searchKey, String uuid) {
        if ((int) searchKey < 0) {
            throw new NotExistStorageException(uuid);
        }
    }
}
