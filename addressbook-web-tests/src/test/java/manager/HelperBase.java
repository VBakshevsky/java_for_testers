package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void dropDownList(By locator, String text) {
        WebElement dropdown = manager.driver.findElement(locator);
        dropdown.findElement(By.xpath(".//option[. = '" + text + "']")).click();
    }

    //protected final WebElement list(By locator) {
      //return manager.driver.findElement(locator);
    //}

    //protected void dropDownList1(ApplicationManager manager, String list, String user) {
        //WebElement dropdown = manager.driver.findElement(By.name(list));
        //dropdown.findElement(By.xpath(".//option[. = '" + user + "']")).click();
    //}
}
