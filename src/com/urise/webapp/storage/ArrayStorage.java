package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (checkAvailability(index)) {
            System.out.println(
                "\nSave ERROR: resume with uuid \"" + r.getUuid() + "\" is already there");
        } else if (size == storage.length) {
            System.out.println("\nERROR: resume array is full");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (checkAvailability(index)) {
            storage[index] = r;
        } else {
            System.out.println(
                "\nUpdate ERROR: resume with uuid \"" + r.getUuid() + "\" is missing");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (checkAvailability(index)) {
            return storage[index];
        }
        System.out.println("\nGet ERROR: resume with uuid \"" + uuid + "\" is missing");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (checkAvailability(index)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("\nDelete ERROR: resume with uuid \"" + uuid + "\" is missing");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        int i;
        for (i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                break;
            }
        }
        return i;
    }

    private boolean checkAvailability(int index) {
        return index != size;
    }
}
