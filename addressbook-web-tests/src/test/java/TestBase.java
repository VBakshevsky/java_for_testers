import model.GroupData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
    protected static WebDriver driver;

    protected static void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected static void removeUser() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(2576, 1426));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }

    }

    protected boolean isElementPresent(By Locator) {
        try {
            driver.findElement(Locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void createGroup(GroupData group) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected boolean isThereARecord() {
        return isElementPresent(By.name("selected[]"));
    }

    protected void createUser(UserData user) {
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(user.firstname());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(user.middlename());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(user.lastname());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys(user.nickname());
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys(user.title());
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys(user.company());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(user.address());
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys(user.home());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(user.mobile());
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys(user.work());
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys(user.fax());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(user.email());
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys(user.email2());
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys(user.email3());
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys(user.homepage());
        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            dropdown.findElement(By.xpath(".//option[. = '" + user.bday() + "']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath(".//option[. = '" + user.bmonth() + "']")).click();
        }
        driver.findElement(By.name("byear")).sendKeys(user.byear());
        {
            WebElement dropdown = driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath(".//option[. = '" + user.aday() + "']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath(".//option[. = '" + user.amonth() + "']")).click();
        }
        driver.findElement(By.name("ayear")).sendKeys(user.ayear());
        driver.findElement(By.xpath("(//input[@name='submit'])")).click();
        driver.findElement(By.linkText("home")).click();
    }
}
