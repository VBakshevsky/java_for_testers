package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class UserModificationTests extends TestBase {

    @Test
    void canModifyUser() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
        }
        var oldUsers = app.hbm().getUsersList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        var testData = new UserData().withMainInformation("first name modified", "middle name modified", "Last name modified", CommonFunctions.randomString(5), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomFile("src/test/resources/images"));
        app.users().modifyUser(oldUsers.get(index), testData);
        var newUsers = app.hbm().getUsersList();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.set(index,testData.withId(oldUsers.get(index).id()).withPhoto(""));
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsers.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newUsers, expectedList);
    }

    @Test
    void canAddUserInGroup(){
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList();
        var oldUsers = app.hbm().getUsersList();
        var rnd = new Random();
        var indexGroup = rnd.nextInt(group.size());
        var index = rnd.nextInt(oldUsers.size());
        var oldRelated = app.hbm().getContactsInGroup(group.get(indexGroup));
        app.users().addUserToGroup(oldUsers.get(index), group.get(indexGroup));
        var newRelated = app.hbm().getContactsInGroup(group.get(indexGroup));
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        if (expectedList.contains(oldUsers.get(index))) {
            expectedList.sort(compareById);
            //expectedList.add(oldUsers.get(index).withId(newRelated.get(index).id()));
        } else {
            expectedList.add(oldUsers.get(index).withId(newRelated.get(newRelated.size() - 1).id()).withPhoto(""));
        }
        Assertions.assertEquals(newRelated, expectedList);



    }
}


//void canAddUserInGroup(){
//    if (app.hbm().getUserCount() == 0) {
//        app.hbm().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
//    }
//    if (app.hbm().getGroupCount() == 0) {
//        app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
//    }
//    var group = app.hbm().getGroupList();
//    var oldUsers = app.hbm().getUsersList();
//    var rnd = new Random();
//    var indexGroup = rnd.nextInt(group.size());
//    var index = rnd.nextInt(oldUsers.size());
//    app.users().addUserToGroup(oldUsers.get(index), group.get(indexGroup));
//    var newUsers = app.hbm().getUsersList();
//    var expectedList = new ArrayList<>(oldUsers);
//    expectedList.set(index,oldUsers.get(index).withId(oldUsers.get(index).id()).withPhoto(""));
//    Comparator<UserData> compareById = (o1, o2) -> {
//        return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//    };
//    newUsers.sort(compareById);
//    expectedList.sort(compareById);
//    Assertions.assertEquals(newUsers, expectedList);