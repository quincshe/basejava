package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private String fullName;

    private final Map<ContactType, String> contacts = new LinkedHashMap<>();

    private final Map<SectionType, Section> sections = new LinkedHashMap<>();

    public Resume() {
        this.uuid = UUID.randomUUID().toString();
//        for (ContactType c:ContactType.values()){
//            contacts.put(c, "");
//        }
//        sections.put(SectionType.OBJECTIVE, new TextSection(SectionType.OBJECTIVE.getTitle()));
//        sections.put(SectionType.PERSONAL, new TextSection(SectionType.PERSONAL.getTitle()));
//        sections.put(SectionType.ACHIEVEMENT, new ListSection(SectionType.ACHIEVEMENT.getTitle()));
//        sections.put(SectionType.QUALIFICATIONS, new ListSection(SectionType.QUALIFICATIONS.getTitle()));
//        sections.put(SectionType.EXPERIENCE, new CompanySection(SectionType.EXPERIENCE.getTitle()));
//        sections.put(SectionType.EDUCATION, new CompanySection(SectionType.EDUCATION.getTitle()));
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

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
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

    private void setCompanySection(SectionType sectionType, String companyName, String website,
        LocalDate begin, LocalDate end, String position, String description) {
        if (!sections.containsKey(sectionType)) {
            sections.put(sectionType, new CompanySection(sectionType.getTitle()));
        }
        CompanySection section = (CompanySection) sections.get(sectionType);
        section.addPeriod(companyName, website, new Period(begin, end, position, description));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    private String printContacts() {
        StringBuilder builder = new StringBuilder();
        contacts.forEach(
            (key, value) -> builder
                .append(key)
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
