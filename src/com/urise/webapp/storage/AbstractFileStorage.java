package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(
                directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            doRemove(file);
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            if (file.createNewFile()) {
                doWrite(r, file);
            }
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        Resume result;
        try {
            result = doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }

        return result;
    }

    @Override
    protected void doDelete(File file) {
        doRemove(file);
    }

    @Override
    protected List<Resume> getCopyList() {
        List<Resume> result = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            result.add(doGet(file));
        }
        return result;
    }

    private void doRemove(File file) {
        if (!file.delete()) {
            throw new StorageException("IO error", file.getName());
        }
    }
}
