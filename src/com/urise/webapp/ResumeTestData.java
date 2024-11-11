package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import java.time.LocalDate;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume gregorResume = new Resume("Григорий Кислин");
        gregorResume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        gregorResume.setContact(ContactType.SKYPE, " skype:grigory.kislin");
        gregorResume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        gregorResume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        gregorResume.setContact(ContactType.GITGUB, "https://github.com/gkislin");
        gregorResume.setContact(ContactType.STACKOWERFLOW,
            "https://stackoverflow.com/users/548473");
        gregorResume.setContact(ContactType.HOMEPAGE,
            "http://gkislin.ru/");
        gregorResume.setObjective("Ведущий стажировок и корпоративного обучения по Java Web и "
            + "Enterprise технологиям.");
        gregorResume.setPersonal("Аналитический склад ума, сильная логика, креативность, "
            + "инициативность. Пурист кода и архитектуры.");
        gregorResume.setAchievement(
            "Организация команды и успешная реализация Java проектов для сторонних заказчиков: "
                + "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга "
                + "показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, "
                + "многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        gregorResume.setAchievement(
            "С 2013 года: разработка проектов \"Разработка Web приложения\","
                + "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). "
                + "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн "
                + "стажировок и ведение проектов. Более 3500 выпускников.");
        gregorResume.setAchievement("Реализация двухфакторной аутентификации для онлайн платформы "
            + "управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, "
            + "Jira, Zendesk.");
        gregorResume.setAchievement("Налаживание процесса разработки и непрерывной интеграции ERP "
            + "системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения "
            + "управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO "
            + "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        gregorResume.setQualifications("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, "
            + "WebLogic, WSO2");
        gregorResume.setQualifications(
            "Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        gregorResume.setQualifications(
            "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, "
                + "SQLite, MS SQL, HSQLDB");
        gregorResume.setQualifications(
            "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        gregorResume.setExperience("Java Online Projects", "http://javaops.ru/",
            LocalDate.of(2019, 10, 1), LocalDate.of(2020, 10, 1),
            "Автор проекта.",
            "Создание, организация и проведение Java онлайн проектов и стажировок.");
        gregorResume.setExperience("Java Online Projects", "http://javaops.ru/",
            LocalDate.of(2013, 10, 1), LocalDate.now(),
            "Автор проекта.",
            "Создание, организация и проведение Java онлайн проектов и стажировок.");
        gregorResume.setExperience("Wrike", "https://www.wrike.com/",
            LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1),
            "Старший разработчик (backend)",
            "Проектирование и разработка онлайн платформы управления проектами Wrike "
                + "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). \n"
                + "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        gregorResume.setExperience("RIT Center", "",
            LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1),
            "Java архитектор",
            "Организация процесса разработки системы ERP для разных окружений: "
                + "релизная политика, версионирование, ведение CI (Jenkins), миграция базы "
                + "(кастомизация Flyway),\n конфигурирование системы (pgBoucer, Nginx), AAA via SSO."
                + "Архитектура БД и серверной части системы. Разработка интергационных сервисов: "
                + "CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf,"
                + "doc,\n html). Интеграция Alfresco JLAN для online редактирование из браузера "
                + "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring "
                + "security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python\n scripting, "
                + "Unix shell remote scripting via ssh tunnels, PL/Python");
        gregorResume.setEducation("Заочная физико-техническая школа при МФТИ",
            "https://mipt.ru/", LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), "", "Закончил с отличием");
        gregorResume.setEducation("Санкт-Петербургский национальный исследовательский "
                + "университет информационных технологий, механики и оптики",
            "http://www.ifmo.ru/", LocalDate.of(1987, 9, 1),
            LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)", "");
        gregorResume.setEducation("Санкт-Петербургский национальный исследовательский "
                + "университет информационных технологий, механики и оптики",
            "http://www.ifmo.ru/", LocalDate.of(1993, 9, 1),
            LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)", "");
        System.out.println(gregorResume);


    }

}
