package com.urise.webapp.storage.startegies;

import com.urise.webapp.model.*;
import com.urise.webapp.util.XmlParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class XmlStrategy implements Strategy {

    private final XmlParser parser;

    public XmlStrategy() {
        parser = new XmlParser(Resume.class, TextSection.class, ListSection.class,
            CompanySection.class, Company.class, Period.class);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            parser.marshall(r, writer);
        }

    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return parser.unmarshall(reader);
        }
    }
}
