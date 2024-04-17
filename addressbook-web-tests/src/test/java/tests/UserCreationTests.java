package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class UserCreationTests extends TestBase {
    public static List<UserData> userProvider() {
        var result = new ArrayList<UserData>();
        for (var firstname : List.of("", "first name")) {
            for (var middlename : List.of("", "middle name")) {
                for (var lastname : List.of("", "last name")) {
                    result.add(new UserData(firstname, middlename, lastname, "", "", "", "", "", "", "", "", "", "", "", "", "", "-", "", "", "-", ""));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new UserData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomMobileNumber(), randomString(i * 10), randomString(i * 10), randomMail(), randomMail(), randomMail(), randomString(i * 10), randomDay(), randomMonth(), randomYear(), randomDay(), randomMonth(), randomYear()));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    public void canCreateMultipleUsers(UserData user) {
        int userCount = app.users().getCountUsers();
        app.users().createUser(user);
        int NewUserCount = app.users().getCountUsers();
        Assertions.assertEquals(userCount + 1, NewUserCount);
    }

    public static List<UserData> negativeUerProvider() {
        var result = new ArrayList<UserData>(List.of(
                new UserData("first name'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "-", "-", "", "-", "-", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeUerProvider")
    public void canNotCreateUser(UserData user) {
        int userCount = app.users().getCountUsers();
        app.users().createUser(user);
        int NewUserCount = app.users().getCountUsers();
        Assertions.assertEquals(userCount, NewUserCount);
    }

}
