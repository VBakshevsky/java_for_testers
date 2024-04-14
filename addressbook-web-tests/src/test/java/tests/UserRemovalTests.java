package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRemovalTests extends TestBase {

    @Test
    public void canDeleteUser() {
        if (app.users().getCountUsers() == 0) {
            app.users().createUser(new UserData("first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "May", "15", "2001"));
        }
        int userCount = app.users().getCountUsers();
        app.users().removeUser();
        int NewUserCount = app.users().getCountUsers();
        Assertions.assertEquals(userCount - 1,NewUserCount);
    }

    @Test
    void canRemoveAllUsersAtOnce() {
        if (app.users().getCountUsers() == 0) {
            app.users().createUser(new UserData("first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "May", "15", "2001"));
        }
        app.users().removeAllUsers();
        Assertions.assertEquals(0, app.users().getCountUsers());
    }
}
