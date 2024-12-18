package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {

    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid5");
        Resume r6 = new Resume("uuid6");
        Resume r7 = new Resume("uuid7");
        Resume r8 = new Resume("uuid8");
        Resume r9 = new Resume("uuid9");

        //Check save()
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r9);
        ARRAY_STORAGE.save(r8);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r7);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r6);
        printAll();

        //Check double save
//        ARRAY_STORAGE.save(r3);

        //Check get()
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();

        //Check delete()
        ARRAY_STORAGE.delete(r1.getUuid());
//        ARRAY_STORAGE.delete(r1.getUuid());
        ARRAY_STORAGE.delete(r9.getUuid());
        printAll();

//        //Check update()
//        ARRAY_STORAGE.update(r6);
//        printAll();

//        ARRAY_STORAGE.update(r1);

        //Check clear()
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
