package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void saveResume(Resume r) {
        int index = -Arrays.binarySearch(storage, 0, size, r) - 1;
        for (int i = size; i > index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[index] = new Resume(r.getUuid());

    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size-1] = null;
    }

}
