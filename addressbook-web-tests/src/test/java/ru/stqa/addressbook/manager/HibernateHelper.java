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
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return  result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    public List<GroupData> getGroupList(){
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    static List<UserData> convertListUsers(List<UserRecord> records) {
        List<UserData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convertUsers(record));
        }
        return  result;
    }

    private static UserData convertUsers(UserRecord record) {
        return new UserData().withAllInformation("" + record.id, record.firstname, record.middlename, record.lastname, record.nickname, record.title, record.company, record.address, record.home, record.mobile, record.work, record.fax, record.email, record.email2, record.email3, record.homepage, record.bday, record.bmonth, record.byear, record.aday, record.amonth, record.ayear);
    }

    public List<UserData> getDbListUsers(){
        return convertListUsers(sessionFactory.fromSession(session -> {
            return session.createQuery("from UserRecord", UserRecord.class).list();
        }));
    }
}
