package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class UserModificationTests extends TestBase {

    @Test
    void canModifyUser() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
        }
        var oldUsers = app.hbm().getUsersList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        var testData = new UserData().withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(5), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomFile("src/test/resources/images"));
        app.users().modifyUser(oldUsers.get(index), testData);
        var newUsers = app.hbm().getUsersList();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.set(index, testData.withId(oldUsers.get(index).id()).withPhoto(""));
//        Comparator<UserData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newUsers.sort(compareById);
//        expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(newUsers), Set.copyOf(expectedList));
    }

    @Test
    void canAddUserInGroup() {
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
        if (!expectedUserListInGroup.contains(user)) {
            expectedUserListInGroup.add(user);
        } else {
            app.users().removeUserFromGroup(user, group);
        }
        app.users().addUserToGroup(user, group);
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