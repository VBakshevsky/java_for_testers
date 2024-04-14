package tests;

import model.UserData;
import org.junit.jupiter.api.Test;

public class UserModificationTests extends TestBase {

    @Test
    void canModifyUser() {
        if (app.users().getCountUsers() == 0) {
            app.users().createUser(new UserData("first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "May", "15", "2001"));
        }
        app.users().modifyUser(new UserData().withInitials("first name modified", "middle name modified", "Last name modified"));
    }
}
