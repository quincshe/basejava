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
import java.util.List;
import java.util.Map;

public class DataStrategy implements Strategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contactMap = r.getContacts();
            dos.writeInt(contactMap.size());
            for (Map.Entry<ContactType, String> entry : contactMap.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sectionMap = r.getSections();
            dos.writeInt(sectionMap.size());
            for (Map.Entry<SectionType, Section> entry : sectionMap.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                writeSection(dos, entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
            }
            return resume;
        }
    }

    private void writeSection(DataOutputStream dos, SectionType sectionType, Section section)
        throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                dos.writeUTF(((TextSection) section).getDescription());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                List<String> descriptionList = ((ListSection) section).getSectionList();
                dos.writeInt(descriptionList.size());
                for (String description : descriptionList) {
                    dos.writeUTF(description);
                }
                break;
            case EXPERIENCE:
            case EDUCATION:
                CompanySection companySection = (CompanySection) section;
                int ggg = companySection.size();
                System.out.println(ggg);
                dos.writeInt(ggg);
                for (Company company : (companySection).getCompanyList()) {
                    writeCompany(dos, company);
                }
                break;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        int size;
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                TextSection textSection = new TextSection(sectionType.getTitle());
                textSection.setDescription(dis.readUTF());
                return textSection;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                ListSection listSection = new ListSection(sectionType.getTitle());
                size = dis.readInt();
                for (int i = 0; i < size; i++) {
                    listSection.addDescription(dis.readUTF());
                }
                return listSection;
            case EXPERIENCE:
            case EDUCATION:
                CompanySection companySection = new CompanySection(sectionType.getTitle());
                size = dis.readInt();
                for (int i=0; i< size;i++){
                    readCompany(dis,companySection);
                }
                return companySection;
        }
        return null;
    }

    private void writeCompany(DataOutputStream dos, Company company) throws IOException {
        for (Period period : company.getPeriods()) {
            dos.writeUTF(company.getName());
            dos.writeUTF(company.getWebsite());
            writePeriod(dos, period);
        }
    }

    private void readCompany(DataInputStream dis, CompanySection companySection)
        throws IOException {
        companySection.addPeriod(dis.readUTF(), dis.readUTF(), readPeriod(dis));
    }

    private void writePeriod(DataOutputStream dos, Period period) throws IOException {
        writeDate(dos, period.getBegin());
        writeDate(dos, period.getEnd());
        dos.writeUTF(period.getPosition());
        dos.writeUTF(period.getDescription());
    }

    private Period readPeriod(DataInputStream dis) throws IOException {
        return new Period(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF());
    }

    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonthValue());
        dos.writeInt(date.getDayOfMonth());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }
}
