package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {
    public static List<UserData> userProvider() {
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
                    .withInitials(randomString(i * 10), randomString(i * 10), randomString(i * 10))
                    .withMainInformation(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomMail(), randomMail(), randomMail(), randomMobileNumber(),"src/test/resources/images/avatar.png"));

        }
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
        expectedList.add(user.withId(newUsers.get(newUsers.size() - 1).id()).withAllInformation("", "", "", "", "", "", "", "", "", "", "", "", "", "-", "-", "", "-", "-", "",""));
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
