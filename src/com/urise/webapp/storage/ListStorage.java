package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(Resume r) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).equals(r)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Resume r) {
        storage.set(getIndex(r), r);
    }

    @Override
    protected void doSave(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(String uuid) {
        return storage.get(getIndex(new Resume(uuid)));
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(getIndex(new Resume(uuid)));
    }


}
