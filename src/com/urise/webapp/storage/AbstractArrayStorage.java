package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (isExisting(index)) {
            storage[index] = r;
        } else {
            System.out.println(
                "\nUpdate ERROR: resume with uuid \"" + r.getUuid() + "\" is missing");
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (isExisting(index)) {
            System.out.println(
                "\nSave ERROR: resume with uuid \"" + r.getUuid() + "\" is already there");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("\nERROR: resume array is full");
        } else {
            saveResume(r);
            size++;
        }
    }


    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExisting(index)) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not exist");
        return null;

    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExisting(index)) {
            deleteResume(uuid);
            size--;
        } else {
            System.out.println("\nDelete ERROR: resume with uuid \"" + uuid + "\" is missing");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isExisting(int index) {
        return index >= 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(String uuid);
}
