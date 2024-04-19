package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class UserRemovalTests extends TestBase {

    @Test
    public void canDeleteUser() {
        if (app.users().getCountUsers() == 0) {
            app.users().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
        }
        var oldUsers = app.users().getListUsers();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        app.users().removeUser(oldUsers.get(index));
        var newUsers = app.users().getListUsers();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.remove(index);
        Assertions.assertEquals(newUsers, expectedList);
    }

    @Test
    void canRemoveAllUsersAtOnce() {
        if (app.users().getCountUsers() == 0) {
            app.users().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001", ""));
        }
        app.users().removeAllUsers();
        Assertions.assertEquals(0, app.users().getCountUsers());
    }
}
