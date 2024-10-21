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
        return storage.indexOf(r);
    }

    @Override
    protected void resaveResume(Resume r) {
        storage.set(getIndex(r), r);
    }

    @Override
    protected void addResume(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(getIndex(new Resume(uuid)));
    }

    @Override
    protected void removeResume(String uuid) {
        storage.remove(getIndex(new Resume(uuid)));
    }


}
