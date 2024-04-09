import model.UserData;
import org.junit.jupiter.api.Test;

public class UserRemovalTests extends TestBase {

    @Test
    public void canDeleteUser() {
        if (!isThereARecord()) {
            createUser(new UserData("first name", "middle name", "Last name", "Nickname", "Title", "Company", "Address", "Home", "+791712332111", "Work", "Fax", "test@mail.ru", "test2@mail.ru", "test3@mail.ru", "homepage", "20", "March", "2000", "May", "15", "2001"));
        }
        removeUser();
    }

}
