package manager;

import model.GroupData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createUser(UserData user) {
        initUserCreation();
        fillUserForm(user);
        submitUserCreation();
        returnToHomePage();
    }

    public void removeUser(UserData user) {
        selectUser(user);
        removeSelectedUsers();
        returnToHomePage();
    }

    public void modifyUser(UserData user,UserData modifiedUser) {
        //selectUser(user);
        initUserModification(user);
        fillUserForm(modifiedUser);
        submitUserModification();
        returnToHomePage();
    }

    public void removeAllUsers() {
        selectAllElements();
        removeSelectedUsers();
    }

    private void removeSelectedUsers() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectUser(UserData user) {
        click(By.cssSelector(String.format("input[value='%s']", user.id())));
    }

    private void submitUserCreation() {
        click(By.name("submit"));
    }

    private void initUserCreation() {
        click(By.linkText("add new"));
    }


    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void submitUserModification() {
        click(By.name("update"));
    }

    private void fillUserForm(UserData user) {
        type(By.name("firstname"), user.firstname());
        type(By.name("middlename"), user.middlename());
        type(By.name("lastname"), user.lastname());
        type(By.name("nickname"), user.nickname());
        type(By.name("title"), user.title());
        type(By.name("company"), user.company());
        type(By.name("address"), user.address());
        type(By.name("home"), user.home());
        type(By.name("mobile"), user.mobile());
        type(By.name("work"), user.work());
        type(By.name("fax"), user.fax());
        type(By.name("email"), user.email());
        type(By.name("email2"), user.email2());
        type(By.name("email3"), user.email3());
        type(By.name("homepage"), user.homepage());
        dropDownList(By.name("bday"), user.bday());
        dropDownList(By.name("bmonth"), user.bmonth());
        type(By.name("byear"), user.byear());
        dropDownList(By.name("aday"), user.aday());
        dropDownList(By.name("amonth"), user.amonth());
        type(By.name("ayear"), user.ayear());
    }

    private void initUserModification(UserData user) {
        click(By.cssSelector(String.format("input[value='%s']", user.id())));
        click(By.xpath("//img[@alt='Edit']"));
    }

    //public boolean isUserPresent() {
    //return manager.isElementPresent(By.name("selected[]"));
    //}

    public int getCountUsers() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<UserData> getListUsers() {
        var users = new ArrayList<UserData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (var tr : trs) {
            var lastname = tr.findElement(By.xpath(".//td[2]")).getText();
            var firstname = tr.findElement(By.xpath(".//td[3]")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            users.add(new UserData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return users;
    }
}
