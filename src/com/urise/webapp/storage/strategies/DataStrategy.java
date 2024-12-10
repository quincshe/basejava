package com.urise.webapp.storage.strategies;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
            System.out.println(sectionMap.size());
            for (Map.Entry<SectionType, Section> entry : sectionMap.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                writeSection(dos,entry.getKey(),entry.getValue() );
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
        switch (sectionType){
            case PERSONAL:
            case OBJECTIVE:
                dos.writeUTF(((TextSection)section).getDescription());
                break;
            case ACHIEVEMENT:
                break;
            case QUALIFICATIONS:
                break;
            case EXPERIENCE:
                break;
            case EDUCATION:
                break;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType){
            case PERSONAL:
            case OBJECTIVE:
                TextSection section = new TextSection();
                section.setDescription(dis.readUTF());
               return section;
            case ACHIEVEMENT:
                break;
            case QUALIFICATIONS:
                break;
            case EXPERIENCE:
                break;
            case EDUCATION:
                break;
        }
        return null;
    }
}
