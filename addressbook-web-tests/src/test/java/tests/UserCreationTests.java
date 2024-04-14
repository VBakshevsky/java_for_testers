package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserCreationTests extends TestBase {

    @Test
    public void canCreateUser() {
        int userCount = app.users().getCountUsers();
        app.users().createUser(new UserData("first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", randomDay(), randomMonth(), "2000", randomDay(), randomMonth(), "2001"));
        int NewUserCount = app.users().getCountUsers();
        Assertions.assertEquals(userCount + 1,NewUserCount);
    }

    @Test
    public void canCreateMultipleUsers() {
        int n = 5;
        int userCount = app.users().getCountUsers();
        for (int i = 0; i < n; i++) {
            app.users().createUser(new UserData("first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", randomDay(), randomMonth(), "2000", randomDay(), randomMonth(), "2001"));
        }
        int NewUserCount = app.users().getCountUsers();
        Assertions.assertEquals(userCount + n,NewUserCount);
    }

    @Test
    public void canCreateUserWithEmptyName() {
        app.users().createUser(new UserData());
    }

    @Test
    public void canCreateUserWithInitials() {
        app.users().createUser(new UserData().withInitials("first name", "middle name","last name"));
    }

    @Test
    public void canCreateUserWithDate() {
        app.users().createUser(new UserData().withDate("1", "March","1996","15", "May","2000"));
    }

    @Test
    public void canCreateUserWithMainInformation() {
        app.users().createUser(new UserData().withMainInformation("first name", "middle name","last name","address","test@mail.ru","test2@mail.ru","test3@mail.ru","+791712332111"));
    }
}
