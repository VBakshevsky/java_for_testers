package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.UserData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
                    .withMainInformation(CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), "", CommonFunctions.randomMobileNumber(), "", CommonFunctions.randomFile("src/test/resources/images"), CommonFunctions.randomMail()));
        }
        readingAFileFromXmlUsers(result);
        return result;
    }

    public static Stream<UserData> randomUser() {
        Supplier<UserData> randomUser = () -> new UserData()
                .withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomFile("src/test/resources/images"), CommonFunctions.randomMail());
        return Stream.generate(randomUser).limit(1);
        //.withAllInformationWithoutFullName("", "", "", "", "", "", "", "", "", "", "", "", "", "5", "May", "", "10", "March", "", ""));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    public void canCreateUsers(UserData user) {
        var oldUsers = app.hbm().getUsersList();
        app.users().createUser(user);
        var newUsers = app.hbm().getUsersList();
//        Comparator<UserData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newUsers.sort(compareById);
//        var maxId = newUsers.get(newUsers.size() - 1).id();
        var extraUsers = newUsers.stream().filter(u -> !oldUsers.contains(u)).toList();
        var newId = extraUsers.get(0).id();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.add(user.withId(newId).withPhoto(""));
//        expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(newUsers), Set.copyOf(expectedList));
    }


    @Test
    public void canCreateUsersInGroup() {
        var user = new UserData().withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomFile("src/test/resources/images"), CommonFunctions.randomMail());
        CreateAGroupIfThereIsNone();
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getUsersInGroup(group);
        app.users().createUser(user, group);
        var newRelated = app.hbm().getUsersInGroup(group);
//        Comparator<UserData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newRelated.sort(compareById);
//        var maxId = newRelated.get(newRelated.size() - 1).id();
        var extraUsers = newRelated.stream().filter(u -> !oldRelated.contains(u)).toList();
        var newId = extraUsers.get(0).id();
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(user.withId(newId).withPhoto(""));
//        expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(newRelated), Set.copyOf(expectedList));
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


