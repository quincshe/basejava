package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(int index, Resume r) {
        storage[size] = r;
    }

    @Override
    protected void extractResume(int index) {
        storage[index] = storage[size - 1];
    }
}
