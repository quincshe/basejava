package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < storage.length && !checkAvailability(r.getUuid())) {
            storage[size] = r;
            size++;

        } else if (size == storage.length) {
            System.out.println("\nERROR: resume array is full");
        } else {
            System.out.println(
                "\nSave ERROR: resume with uuid \"" + r.getUuid() + "\" is already there");
        }
    }

    public void update(Resume r) {
        if (checkAvailability(r.getUuid())) {
            storage[getPosition(r.getUuid())] = r;
//            System.out.println("resume with uuid \"" + r.getUuid() + "\" is update");
        } else {
            System.out.println(
                "\nUpdate ERROR: resume with uuid \"" + r.getUuid() + "\" is missing");
        }
    }

    public Resume get(String uuid) {
        if (checkAvailability(uuid)) {
            return storage[getPosition(uuid)];
        }
        System.out.println("\nGet ERROR: resume with uuid \"" + uuid + "\" is missing");
        return null;
    }

    public void delete(String uuid) {
        if (checkAvailability(uuid)) {
            storage[getPosition(uuid)] = storage[size - 1];
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

    private int getPosition(String uuid) {
        int i;
        for (i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                break;
            }
        }
        return i;
    }

    private boolean checkAvailability(String uuid) {
        return getPosition(uuid) != size;
    }
}
