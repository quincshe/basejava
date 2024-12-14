package com.urise.webapp.storage.strategies;

import com.urise.webapp.model.Company;
import com.urise.webapp.model.CompanySection;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.ListSection;
import com.urise.webapp.model.Period;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Collection;

public class DataStrategy implements Strategy {

    private DataOutputStream dos;
    private DataInputStream dis;
    private Resume resume;

    public DataStrategy() {
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(os)) {
            dos = dataOutputStream;
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeWithException(resume.getContacts().entrySet(), (entry) -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeWithException(resume.getSections().entrySet(), (entry) -> {
                dos.writeUTF(entry.getKey().name());
                writeSection(entry.getKey(), entry.getValue());
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(is)) {
            dis = dataInputStream;
            resume = new Resume(dis.readUTF(), dis.readUTF());
            readWithException(
                () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(() -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(sectionType));
            });
            return resume;
        }
    }

    private <T> void writeWithException(Collection<T> collection, DataWriter<T> writer)
        throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
        }
    }

    private void readWithException(DataReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private void writeSection(SectionType sectionType, Section section) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                dos.writeUTF(((TextSection) section).getDescription());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                writeWithException(((ListSection) section).getSectionList(),
                    (description) -> dos.writeUTF(description));
                break;
            case EXPERIENCE:
            case EDUCATION:
                CompanySection companySection = (CompanySection) section;
                dos.writeInt(companySection.size());
                for (Company company : (companySection).getCompanyList()) {
                    writeCompany(company);
                }
                break;
        }
    }

    private Section readSection(SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                TextSection textSection = new TextSection(sectionType.getTitle());
                textSection.setDescription(dis.readUTF());
                return textSection;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                ListSection listSection = new ListSection(sectionType.getTitle());
                readWithException(() -> listSection.addDescription(dis.readUTF()));
                return listSection;
            case EXPERIENCE:
            case EDUCATION:
                CompanySection companySection = new CompanySection(sectionType.getTitle());
                readWithException(() -> readCompany(companySection));
                return companySection;
        }
        return null;
    }

    private void writeCompany(Company company) throws IOException {
        for (Period period : company.getPeriods()) {
            dos.writeUTF(company.getName());
            dos.writeUTF(company.getWebsite());
            writePeriod(period);
        }
    }

    private void readCompany(CompanySection companySection) throws IOException {
        companySection.addPeriod(dis.readUTF(), dis.readUTF(), readPeriod());
    }

    private void writePeriod(Period period) throws IOException {
        writeDate(period.getBegin());
        writeDate(period.getEnd());
        dos.writeUTF(period.getPosition());
        dos.writeUTF(period.getDescription());
    }

    private Period readPeriod() throws IOException {
        return new Period(readDate(), readDate(), dis.readUTF(), dis.readUTF());
    }

    private void writeDate(LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonthValue());
        dos.writeInt(date.getDayOfMonth());
    }

    private LocalDate readDate() throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }
}
