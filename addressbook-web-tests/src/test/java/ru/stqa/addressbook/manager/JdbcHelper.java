package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {

    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List<UserData> getDbListUsers() {
        var users = new ArrayList<UserData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT id, firstname, middlename, lastname, nickname, title, company, address, home, mobile, work, fax, email, email2, email3, homepage, bday, bmonth, byear, aday, amonth, ayear FROM `addressbook` ORDER BY `home` DESC")) {
            while (result.next()) {
                users.add(new UserData()
                        .withAllInformation(result.getString("id"), result.getString("firstname"), result.getString("middlename"),result.getString("lastname"), result.getString("nickname"), result.getString("title"), result.getString("company"), result.getString("address"), result.getString("home"), result.getString("mobile"), result.getString("work"), result.getString("fax"), result.getString("email"), result.getString("email2"), result.getString("email3"), result.getString("homepage"), result.getString("bday"), result.getString("bmonth"), result.getString("byear"), result.getString("aday"), result.getString("amonth"), result.getString("ayear")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
