package tests;

import model.UserData;
import org.junit.jupiter.api.Test;

public class UserCreationTests extends TestBase {

    @Test
    public void canCreateUser() {
        app.users().createUser(new UserData("first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "May", "15", "2001"));
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
    public void canCreateUserWithMainInformation() {
        app.users().createUser(new UserData().withMainInformation("first name", "middle name","last name","address","test@mail.ru","test2@mail.ru","test3@mail.ru","+791712332111"));
    }
}
