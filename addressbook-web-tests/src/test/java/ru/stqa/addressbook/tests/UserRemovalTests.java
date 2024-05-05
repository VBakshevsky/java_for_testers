package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class UserRemovalTests extends TestBase {

    @Test
    public void canDeleteUser() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
        }
        var oldUsers = app.hbm().getUsersList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        app.users().removeUser(oldUsers.get(index));
        var newUsers = app.hbm().getUsersList();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.remove(index);
        Assertions.assertEquals(newUsers, expectedList);
    }

    @Test
    void canRemoveAllUsersAtOnce() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
        }
        app.users().removeAllUsers();
        Assertions.assertEquals(0, app.hbm().getUserCount());
    }

    @Test
    public void canRemoveUserFromGroup() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var groups = app.hbm().getGroupList();
        var oldUsers = app.hbm().getUsersList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        var indexGroup = rnd.nextInt(groups.size());
        var user = oldUsers.get(index);
        var group = groups.get(indexGroup);
        var oldRelated = app.hbm().getUsersInGroup(group);
        var expectedUserListInGroup = new ArrayList<>(oldRelated);
        expectedUserListInGroup.remove(user);
        if (!app.hbm().isUsersInGroup(group, user)) {
            app.users().addUserToGroup(user, group);
        }
        app.users().removeUserFromGroup(user, group);
        var newRelated = app.hbm().getUsersInGroup(group);
//        Comparator<UserData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
        var expectedList = new ArrayList<>(newRelated);
//        expectedUserListInGroup.sort(compareById);
//        expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(expectedUserListInGroup), Set.copyOf(expectedList));
    }
}
