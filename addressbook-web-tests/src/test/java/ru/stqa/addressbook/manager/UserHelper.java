package ru.stqa.addressbook.manager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    @Step
    public void createUser(UserData user) {
        initUserCreation();
        fillUserForm(user);
        submitUserCreation();
        returnToHomePage();
    }

    @Step
    public void createUser(UserData user, GroupData group) {
        initUserCreation();
        fillUserForm(user);
        selectGroup(group);
        submitUserCreation();
        returnToHomePage();
    }

    @Step
    public void addUserToGroup(UserData user, GroupData group) {
        returnToHomePage();
        selectGroupFromAdd(group);
        selectUser(user);
        addTo();
        returnToHomePage();
    }

    @Step
    public void removeUser(UserData user) {
        returnToHomePage();
        selectUser(user);
        removeSelectedUsers();
        returnToHomePage();
    }

    @Step
    public void modifyUser(UserData user, UserData modifiedUser) {
        returnToHomePage();
        initUserModification(user);
        fillUserForm(modifiedUser);
        submitUserModification();
        returnToHomePage();
    }

    @Step
    public void removeAllUsers() {
        returnToHomePage();
        selectAllUsers();
        removeSelectedUsers();
    }

    @Step
    public void removeUserFromGroup(UserData user, GroupData group) {
        returnToHomePage();
        selectGroupFilter(group);
        selectUser(user);
        removeSelectedUserFromGroup();
        returnToHomePage();
        returnToAllUsers();
    }

    private void selectGroupFilter(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void returnToAllUsers() {
        click(By.xpath("//*[@id=\"right\"]/select/option[2]"));
    }

    private void addTo() {
        click(By.xpath("//input[@value=\'Add to\']"));
    }

    private void removeSelectedUserFromGroup() {
        click(By.name("remove"));
    }

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


    public void returnToHomePage() {
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
        attach(By.name("photo"), user.photo());
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
        returnToHomePage();
        var trs = manager.driver.findElements(By.cssSelector("tr[name=\"entry\"]"));
        return trs.stream()
                .map(tr -> {
                    var cells = tr.findElements(By.tagName("td"));
                    var lastname = cells.get(1).getText();
                    var firstname = cells.get(2).getText();
                    //var lastname = tr.findElement(By.xpath(".//td[2]")).getText();
                    //var firstname = tr.findElement(By.xpath(".//td[3]")).getText();
                    var checkbox = tr.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                    return new UserData().withId(id).withFirstName(firstname).withLastName(lastname);
                })
                .collect(Collectors.toList());
        //        var users = new ArrayList<UserData>();
//        for (var tr : trs) {
//            var cells = tr.findElements(By.tagName("td"));
//            var lastname = cells.get(1).getText();
//            var firstname = cells.get(2).getText();
//            //var lastname = tr.findElement(By.xpath(".//td[2]")).getText();
//            //var firstname = tr.findElement(By.xpath(".//td[3]")).getText();
//            var checkbox = tr.findElement(By.name("selected[]"));
//            var id = checkbox.getAttribute("value");
//            users.add(new UserData().withId(id).withFirstName(firstname).withLastName(lastname));
//        }
//        return users;
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectGroupFromAdd(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }


    public String getPhones(UserData user) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", user.id()))).getText();
    }

    public String getPhonesEmailsAndAddress(UserData user) {
        returnToHomePage();
        var address = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", user.id()))).getText();
        var email = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", user.id()))).getText();
        var phone = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", user.id()))).getText();
        return address + "\n" + email + "\n" + phone + "\n";
    }

    public String getEmails(UserData user) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", user.id()))).getText();
    }

    public String getAddress(UserData user) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", user.id()))).getText();
    }

    public Map<String, String> getPhones() {
        returnToHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        returnToHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }

    public Map<String, String> getAddress() {
        returnToHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("value");
            var addresses = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, addresses);
        }
        return result;
    }

    public Object getPhonesEmailsAndAddressFromEditForm(UserData user) {
        returnToHomePage();
        initUserModification(user);
        var address = manager.driver.findElement(By.name("address")).getAttribute("value");
        var email = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");
        var emails = Stream.of(email, email2, email3)
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        var home = manager.driver.findElement(By.name("home")).getAttribute("value");
        var mobile = manager.driver.findElement(By.name("mobile")).getAttribute("value");
        var work = manager.driver.findElement(By.name("work")).getAttribute("value");
        var phone2 = manager.driver.findElement(By.name("phone2")).getAttribute("value");
        var phones = Stream.of(home, mobile, work, phone2)
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        return address + "\n" + emails + "\n" + phones + "\n";
    }
}


//    protected void selectAllUsers() {
//        var checkboxes = manager.driver.findElements(By.name("selected[]"));
//        for (var checkbox : checkboxes) {
//            checkbox.click();
//        }
//    }