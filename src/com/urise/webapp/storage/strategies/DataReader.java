package com.urise.webapp.storage.strategies;

import java.io.IOException;

@FunctionalInterface
public interface DataReader {

    void read() throws IOException;
}
