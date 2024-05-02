package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {
    public static List<UserData> userProvider() throws IOException {
        var result = new ArrayList<UserData>();
        for (var firstname : List.of("", "first name")) {
            for (var middlename : List.of("", "middle name")) {
                for (var lastname : List.of("", "last name")) {
                    result.add(new UserData()
                            .withFirstName(firstname)
                            .withMiddleName(middlename)
                            .withLastName(lastname));

                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new UserData()
                    .withMainInformation(CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomFile("src/test/resources/images")));
        }
        readingAFileFromXmlUsers(result);
        return result;
    }

    public static List<UserData> singleRandomUser() {
        return List.of(new UserData()
                .withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomFile("src/test/resources/images")));
        //.withAllInformationWithoutFullName("", "", "", "", "", "", "", "", "", "", "", "", "", "5", "May", "", "10", "March", "", ""));
    }

    @ParameterizedTest
    @MethodSource("singleRandomUser")
    public void canCreateUsers(UserData user) {
        var oldUsers = app.hbm().getUsersList();
        app.users().createUser(user);
        var newUsers = app.hbm().getUsersList();
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsers.sort(compareById);
        var maxId = newUsers.get(newUsers.size() - 1).id();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.add(user.withId(maxId).withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newUsers, expectedList);
    }


    @Test
    public void canCreateUsersInGroup() {
        var user = new UserData().withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getUsersInGroup(group);
        app.users().createUser(user, group);
        var newRelated = app.hbm().getUsersInGroup(group);
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var maxId = newRelated.get(newRelated.size() - 1).id();
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(user.withId(maxId).withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }

    public static List<UserData> negativeUerProvider() {
        var result = new ArrayList<UserData>(List.of(
                new UserData("", "first name'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "0", "-", "", "0", "-", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeUerProvider")
    public void canNotCreateUser(UserData user) {
        var oldUsers = app.users().getListUsers();
        app.users().createUser(user);
        var newUsers = app.users().getListUsers();
        Assertions.assertEquals(newUsers, oldUsers);
    }

}


