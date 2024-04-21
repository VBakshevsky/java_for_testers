package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {
    public static List<UserData> userProvider() throws IOException {
        var result = new ArrayList<UserData>();
//        for (var firstname : List.of("", "first name")) {
//            for (var middlename : List.of("", "middle name")) {
//                for (var lastname : List.of("", "last name")) {
//                    result.add(new UserData()
//                            .withFirstName(firstname)
//                            .withMiddleName(middlename)
//                            .withLastName(lastname));
//
//                }
//            }
//        }

        for (int i = 0; i < 5; i++) {
            result.add(new UserData()
                    .withInitials(CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10)));
            //.withMainInformation(CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMail(), CommonFunctions.randomMobileNumber(), CommonFunctions.randomFile("src/test/resources/images"))

        }

        var json = "";
        try (var reader = new FileReader("users.json");
             var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }
        //var json = Files.readString(Paths.get("users.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<UserData>>() {
        });
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    public void canCreateMultipleUsers(UserData user) {
        var oldUsers = app.users().getListUsers();
        app.users().createUser(user);
        var newUsers = app.users().getListUsers();
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsers.sort(compareById);

        var expectedList = new ArrayList<>(oldUsers);
        expectedList.add(user.withId(newUsers.get(newUsers.size() - 1).id()).withAllInformation("", "", "", "", "", "", "", "", "", "", "", "", "", "-", "-", "", "-", "-", "", ""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newUsers, expectedList);
    }

    public static List<UserData> negativeUerProvider() {
        var result = new ArrayList<UserData>(List.of(
                new UserData("", "first name'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "-", "-", "", "-", "-", "", "")));
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


