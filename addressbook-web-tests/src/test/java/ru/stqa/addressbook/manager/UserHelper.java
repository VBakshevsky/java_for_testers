package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.UserData;
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
        returnToHomePage();
        selectUser(user);
        removeSelectedUsers();
        returnToHomePage();
    }

    public void modifyUser(UserData user,UserData modifiedUser) {
        returnToHomePage();
        initUserModification(user);
        fillUserForm(modifiedUser);
        submitUserModification();
        returnToHomePage();
    }

    public void removeAllUsers() {
        returnToHomePage();
        selectAllUsers();
        removeSelectedUsers();
    }

//    protected void selectAllUsers() {
//        var checkboxes = manager.driver.findElements(By.name("selected[]"));
//        for (var checkbox : checkboxes) {
//            checkbox.click();
//        }
//    }

    private void removeSelectedUsers() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }
    private void selectAllUsers() {
        click(By.xpath("//input[@id=\'MassCB\']"));
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
        attach(By.name("photo"),user.photo());
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
        dropDownListDays(By.name("bday"), user.bday());
        dropDownListMonths(By.name("bmonth"), user.bmonth());
        type(By.name("byear"), user.byear());
        dropDownListDays(By.name("aday"), user.aday());
        dropDownListMonths(By.name("amonth"), user.amonth());
        type(By.name("ayear"), user.ayear());
    }

    private void initUserModification(UserData user) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", user.id())));
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<UserData> getListUsers() {
        var users = new ArrayList<UserData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (var tr : trs) {
            var cells = tr.findElements(By.tagName("td"));
            var lastname = cells.get(1).getText();
            var firstname = cells.get(2).getText();
            //var lastname = tr.findElement(By.xpath(".//td[2]")).getText();
            //var firstname = tr.findElement(By.xpath(".//td[3]")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            users.add(new UserData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return users;
    }
}
