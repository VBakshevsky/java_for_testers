package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class UserModificationTests extends TestBase {

    @Test
    void canModifyUser() {
        if (app.users().getCountUsers() == 0) {
            app.users().createUser(new UserData("", "first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "15", "May", "2001"));
        }
        var oldUsers = app.users().getListUsers();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        var testData = new UserData().withInitials("first name modified", "middle name modified", "Last name modified");
        app.users().modifyUser(oldUsers.get(index), testData);
        var newUsers = app.users().getListUsers();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.set(index,testData.withId(oldUsers.get(index).id()));
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsers.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newUsers, expectedList);
    }
}
