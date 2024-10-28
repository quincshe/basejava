package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid,""));
    }

    @Override
    protected void insertResume(int index, Resume r) {
        int indexSet = -index - 1;
        int numOffset = size - indexSet;
        System.arraycopy(storage, indexSet, storage, indexSet + 1, numOffset);
        storage[indexSet] = r;

    }

    @Override
    protected void extractResume(int index) {
        int numOffset = size - index - 1;
        System.arraycopy(storage, index + 1, storage, index, numOffset);

    }

}
