package com.urise.webapp.storage.strategies;

import java.io.IOException;

public interface DataWriter<T> {

    void write(T t) throws IOException;
}
