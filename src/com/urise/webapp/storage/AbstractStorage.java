package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SearchKey> implements Storage {

    private static final Comparator<Resume> STORAGE_COMPARATOR = Comparator.comparing(
        Resume::getFullName).thenComparing(Resume::getUuid);

    @Override
    public void update(Resume r) {
        SearchKey searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        SearchKey searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SearchKey searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        SearchKey searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = getCopyList();
        result.sort(STORAGE_COMPARATOR);
        return result;
    }

    protected abstract List<Resume> getCopyList();

    protected abstract SearchKey getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, SearchKey searchKey);

    protected abstract void doSave(Resume r, SearchKey searchKey);

    protected abstract Resume doGet(SearchKey searchKey);

    protected abstract void doDelete(SearchKey searchKey);

    protected abstract boolean isExist(SearchKey searchKey);

    protected SearchKey getNotExistingSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected SearchKey getExistingSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
