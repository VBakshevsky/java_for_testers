package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.manager.UserHelper;
import ru.stqa.addressbook.model.UserData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData().withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), "+791711122233", "+791733322211", "+791722233311", CommonFunctions.randomFile("src/test/resources/images"), CommonFunctions.randomMail()));
        }
        app.users().returnToHomePage();
        var users = app.hbm().getUsersList();
        var user = users.get(0);
        var phones = app.users().getPhones(user);
        var expected = Stream.of(user.home(), user.mobile(), user.work())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,phones);
    }

    @Test
    void testAddress() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData().withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), "+791711122233", "+791733322211", "+791722233311", CommonFunctions.randomFile("src/test/resources/images"), CommonFunctions.randomMail()));
        }
        app.users().returnToHomePage();
        var users = app.hbm().getUsersList();
        var user = users.get(0);
        var address = app.users().getAddress(user);
        var expected = Stream.of(user.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,address);
    }

    @Test
    void testEmails() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData().withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), "+791711122233", "+791733322211", "+791722233311", CommonFunctions.randomFile("src/test/resources/images"), CommonFunctions.randomMail()));
        }
        app.users().returnToHomePage();
        var users = app.hbm().getUsersList();
        var user = users.get(0);
        var email = app.users().getEmails(user);
        var expected = Stream.of(user.email(),user.email2(),user.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,email);
    }
}
