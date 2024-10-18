package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage {

    private final List<Resume> storage;

    public ListStorage() {
        this.storage = new ArrayList<>();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        int index = storage.indexOf(r);
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage.set(index, r);
    }

    @Override
    public void save(Resume r) {
        int index = storage.indexOf(r);
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        storage.add(r);
    }

    @Override
    public Resume get(String uuid) {
        int index = storage.indexOf(new Resume(uuid));
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
        int index = storage.indexOf(new Resume(uuid));
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        storage.remove(index);

    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

}
