package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.manager.hbm.UserRecord;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;


public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(UserRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convertGroup(record));
        }
        return result;
    }

    private static GroupData convertGroup(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convertGroup(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroup(groupData));
            session.getTransaction().commit();
        });
    }

    static List<UserData> convertListUsers(List<UserRecord> records) {
        List<UserData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convertUsers(record));
        }
        return result;
    }

    private static UserData convertUsers(UserRecord record) {
        return new UserData().withAllInformation("" + record.id, record.firstname, record.middlename, record.lastname, record.nickname, record.title, record.company, record.address, record.home, record.mobile, record.work, record.fax, record.email, record.email2, record.email3, record.homepage, record.bday, record.bmonth, record.byear, record.aday, record.amonth, record.ayear);
    }

    private static UserRecord convertUsers(UserData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new UserRecord(Integer.parseInt(id), data.firstname(), data.middlename(), data.lastname(), data.nickname(), data.title(), data.company(), data.address(), data.home(), data.mobile(), data.work(), data.fax(), data.email(), data.email2(), data.email3(), data.homepage(), data.bday(), data.bmonth(), data.byear(), data.aday(), data.amonth(), data.ayear(), data.photo());
    }

    public List<UserData> getUsersList() {
        return convertListUsers(sessionFactory.fromSession(session -> {
            return session.createQuery("from UserRecord", UserRecord.class).list();
        }));
    }

    public long getUserCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from UserRecord", Long.class).getSingleResult();
        });
    }

    public void createUser(UserData userData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertUsers(userData));
            session.getTransaction().commit();
        });
    }

    public List<UserData> getUsersInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertListUsers(session.get(GroupRecord.class, group.id()).users);
        });
    }

    public boolean isUsersInGroup(GroupData group, UserData user) {
        var usersInGroup = getUsersInGroup(group);
        return usersInGroup.contains(user);
    }
}
