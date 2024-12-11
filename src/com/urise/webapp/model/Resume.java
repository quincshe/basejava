package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Initial resume class
 */
@XmlRootElement
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 1L;

    // Unique identifier
    private String uuid;

    private String fullName;

    private Map<ContactType, String> contacts = new LinkedHashMap<>();

    private Map<SectionType, Section> sections = new LinkedHashMap<>();

    public Resume() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Resume(String fullName) {
        this();
        this.fullName = fullName;

    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public void setSections(
        Map<SectionType, Section> sections) {
        this.sections = sections;
    }

    public void setContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void setSection(SectionType sectionType, Section section) {
        sections.put(sectionType, section);
    }

    public void setObjective(String objective) {
        setTextSection(SectionType.OBJECTIVE, objective);
    }

    public void setPersonal(String personal) {
        setTextSection(SectionType.PERSONAL, personal);
    }

    public void setAchievement(String achievement) {
        setListSection(SectionType.ACHIEVEMENT, achievement);
    }

    public void setQualifications(String qualification) {
        setListSection(SectionType.QUALIFICATIONS, qualification);
    }

    public void setExperience(String companyName, String website,
        LocalDate begin, LocalDate end, String position, String description) {
        setCompanySection(SectionType.EXPERIENCE, companyName, website, begin, end, position,
            description);
    }

    public void setEducation(String companyName, String website,
        LocalDate begin, LocalDate end, String position, String description) {
        setCompanySection(SectionType.EDUCATION, companyName, website, begin, end, position,
            description);
    }

    private void setTextSection(SectionType sectionType, String description) {
        TextSection section = new TextSection(sectionType.getTitle());
        section.setDescription(description);
        sections.put(sectionType, section);
    }

    private void setListSection(SectionType sectionType, String description) {
        if (!sections.containsKey(sectionType)) {
            sections.put(sectionType, new ListSection(sectionType.getTitle()));
        }
        ListSection section = (ListSection) sections.get(sectionType);
        section.addDescription(description);
    }

    private void setCompanySection(SectionType sectionType, String companyName, String website,
        LocalDate begin, LocalDate end, String position, String description) {
        if (!sections.containsKey(sectionType)) {
            sections.put(sectionType, new CompanySection(sectionType.getTitle()));
        }
        CompanySection section = (CompanySection) sections.get(sectionType);
        section.addPeriod(companyName, website, new Period(begin, end, position, description));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName,
            resume.fullName) && Objects.equals(contacts, resume.contacts)
            && Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    private String printContacts() {
        StringBuilder builder = new StringBuilder();
        contacts.forEach(
            (key, value) -> builder
                .append(key.getTitle())
                .append(": ")
                .append(value)
                .append("\n"));
        return builder.toString();
    }

    private String printSections() {
        StringBuilder builder = new StringBuilder();
        sections.forEach((key, value) -> builder
            .append(value.getName())
            .append(":\n")
            .append(value)
        );
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Resume\n" +
            "uuid='" + uuid + '\'' + "\n" +
            "ИМЯ:\n" + fullName + "\n" +
            "Контакты:\n" + printContacts() +
            "\n" + printSections();
    }

    @Override
    public int compareTo(Resume resume) {
        int compare = fullName.compareTo(resume.fullName);
        return compare != 0 ? compare : uuid.compareTo(resume.uuid);
    }


}
