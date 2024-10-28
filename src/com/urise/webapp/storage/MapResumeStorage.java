package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private final Map<Resume, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected List<Resume> getNotSortedList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return new Resume(uuid,"");
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        doSave(r, searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put((Resume)searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Resume)searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((Resume)searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((Resume)searchKey);
    }


}
