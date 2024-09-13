/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; ++i) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            ++size;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; ++i) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int i = 0;
        int removedPosition = size;
        for (; i < size; ++i) {
            if (storage[i].uuid.equals(uuid)) {
                removedPosition = i;
                size--;
            }
        }
        for (int k = removedPosition; k < size; ++k) {
            if (k < storage.length - 1) {
                storage[k] = storage[k + 1];
            } else {
                storage[k] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage, 0, result, 0, size);
        return result;
    }

    int size() {
        return size;
    }
}
