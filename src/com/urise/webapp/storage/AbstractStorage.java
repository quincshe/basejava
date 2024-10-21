package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        int index = getIndex(r);
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        resaveResume(r);
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r);
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        addResume(r);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(new Resume(uuid));
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResumeByIndex(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(new Resume(uuid));
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        removeResume(index);
    }

    protected abstract int getIndex(Resume r);

    protected abstract void resaveResume(Resume r);

    protected abstract void addResume(Resume r);

    protected abstract Resume getResumeByIndex(int index);

    protected abstract void removeResume(int index);
}
